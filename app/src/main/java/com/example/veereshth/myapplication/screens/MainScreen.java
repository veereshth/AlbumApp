package com.example.veereshth.myapplication.screens;


import com.example.veereshth.myapplication.models.AlbumResponse;

import java.util.List;

public interface MainScreen extends LoadDataView{

    void onAlubmResponse(List<AlbumResponse> response);

}
