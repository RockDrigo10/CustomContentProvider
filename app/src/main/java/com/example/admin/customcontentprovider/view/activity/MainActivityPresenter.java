package com.example.admin.customcontentprovider.view.activity;

import android.util.Log;

import com.example.admin.customcontentprovider.model.Cars;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Admin on 8/23/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private static final String TAG = "MainActivityPresenter";
    MainActivityContract.View view;
    FirebaseDatabase database;
    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void init() {
        database = FirebaseDatabase.getInstance();
    }
    DatabaseReference carReference;
    @Override
    public void pushToDb(Cars car) {
        carReference =  database.getReference("cars");
        carReference.child(car.getModel()).setValue(car);
        Log.d(TAG, "pushToDb: " );
        view.onDataSaved(true);
    }

    @Override
    public void getCars() {

    }
   }
