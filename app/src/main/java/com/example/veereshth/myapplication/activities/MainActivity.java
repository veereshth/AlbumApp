package com.example.veereshth.myapplication.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.veereshth.myapplication.AlbumListAdapter;
import com.example.veereshth.myapplication.App;
import com.example.veereshth.myapplication.R;
import com.example.veereshth.myapplication.databinding.ActivityMainBinding;
import com.example.veereshth.myapplication.models.AlbumResponse;
import com.example.veereshth.myapplication.presenters.AlbumPresenter;
import com.example.veereshth.myapplication.screens.MainScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreen {
    ActivityMainBinding binding;
    @Inject
    AlbumPresenter presenter;
    private AlbumListAdapter adapter;
    private List<AlbumResponse> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandlers(this);
        App.getAppComponent().inject(MainActivity.this);
        presenter.setView(this);

        setVerticalRecyclerView(binding.albumList,this);
        adapter = new AlbumListAdapter(this, list);

        binding.albumList.setAdapter(adapter);
        presenter.getAlubmList();

    }


    @Override
    public void onAlubmResponse(List<AlbumResponse> response) {
        list = response;
        if (list.size() > 0) {
            Collections.sort(list, new Comparator<AlbumResponse>() {
                @Override
                public int compare(final AlbumResponse object1, final AlbumResponse object2) {
                    return object1.getTitle().compareTo(object2.getTitle());
                }
            });
        }
        adapter.refreshData(list);
    }
    private void setVerticalRecyclerView(RecyclerView view, Context context) {
        view.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        view.setLayoutManager(manager);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public Context context() {
        return null;
    }
}
