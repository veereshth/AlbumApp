package com.example.veereshth.myapplication.presenters;

import android.content.Context;


import com.example.veereshth.myapplication.models.AlbumResponse;
import com.example.veereshth.myapplication.screens.MainScreen;
import com.example.veereshth.myapplication.utils.network.RestApiImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AlbumPresenter {

    RestApiImpl restApi;
    private MainScreen view;
    private Disposable disposable;

    @Inject
    Context context;

    public void setView(MainScreen view) {
        this.view = view;
    }
    @Inject
    public AlbumPresenter(RestApiImpl restApi) {
        this.restApi = restApi;
        this.view = view;
    }

    public void getAlubmList() {

        restApi.getAlubmList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AlbumResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<AlbumResponse> value) {
                        view.onAlubmResponse(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void disposableAll() {
        disposable(disposable);
    }

    private void disposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

}
