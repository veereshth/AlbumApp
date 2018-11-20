package com.example.veereshth.myapplication.utils.dagger;



import com.example.veereshth.myapplication.activities.MainActivity;
import com.example.veereshth.myapplication.utils.AppModule;
import com.example.veereshth.myapplication.utils.network.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, NetModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);

}
