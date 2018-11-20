package com.example.veereshth.myapplication;

import android.app.Application;

import com.example.veereshth.myapplication.utils.AppModule;
import com.example.veereshth.myapplication.utils.dagger.AppComponent;
import com.example.veereshth.myapplication.utils.dagger.DaggerAppComponent;
import com.example.veereshth.myapplication.utils.network.NetModule;


public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();


    }
    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
