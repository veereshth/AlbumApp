package com.example.veereshth.myapplication.utils.network;

import android.accounts.NetworkErrorException;
import android.content.Context;


import com.example.veereshth.myapplication.models.AlbumResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RestApiImpl {
    private Context context;

    RestApi restApi;

    @Inject
    public RestApiImpl(Context context, RestApi restApi) {
        this.context = context;
        this.restApi = restApi;
    }
    public Observable<List<AlbumResponse>> getAlubmList() {
            return restApi.getALbumList();
    }

}
