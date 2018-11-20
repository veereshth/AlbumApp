package com.example.veereshth.myapplication.screens;

import android.content.Context;

public interface LoadDataView {
    void showLoading();
    void hideLoading();
    void showRetry();
    void hideRetry();
    void showError(String msg);
    Context context();
}
