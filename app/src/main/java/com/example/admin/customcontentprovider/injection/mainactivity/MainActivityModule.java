package com.example.admin.customcontentprovider.injection.mainactivity;

import com.example.admin.customcontentprovider.view.activity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin on 8/23/2017.
 */
@Module
public class MainActivityModule {
    @Provides
    MainActivityPresenter providerMainActivityPresenter(){
        return  new MainActivityPresenter();
    }
}
