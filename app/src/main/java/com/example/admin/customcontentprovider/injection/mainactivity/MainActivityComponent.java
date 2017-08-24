package com.example.admin.customcontentprovider.injection.mainactivity;

import com.example.admin.customcontentprovider.view.activity.MainActivity;

import dagger.Component;

/**
 * Created by Admin on 8/23/2017.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
