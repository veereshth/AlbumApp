package com.example.veereshth.myapplication.utils.network;


import com.example.veereshth.myapplication.models.AlbumResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RestApi {
    @GET("albums")
    public Observable<List<AlbumResponse>> getALbumList();

}
