package com.example.veereshth.myapplication.utils.network;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.veereshth.myapplication.utils.AppConstants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    private static boolean connected = true;
    private static boolean isConnected() {
        return connected;
    }

    @Provides
    @Singleton
    RestApi provideRetrofit(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "offlineCache");
        long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MB

        Cache cache = new Cache(httpCacheDirectory,SIZE_OF_CACHE);

        OkHttpClient.Builder clientBuilder =
                new OkHttpClient.Builder().cache(new Cache(new File("/tmp/http"), 10 * 1024 * 1024));

        clientBuilder.addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppConstants.BASE_URL)
                .client(clientBuilder.build())
                .build();

        return retrofit.create(RestApi.class);
    }



    private final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 5000)
                        .build();
            } else {
                return originalResponse;
            }
        }
    };

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (isConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);

            System.out.println("network: " + response.networkResponse());
            System.out.println("cache: " + response.cacheResponse());

            return response;
        }
    };

}
