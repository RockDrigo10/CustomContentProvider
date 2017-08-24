package com.example.admin.customcontentprovider.view.activity;

import com.example.admin.customcontentprovider.BasePresenter;
import com.example.admin.customcontentprovider.BaseView;
import com.example.admin.customcontentprovider.model.Cars;

import java.util.List;

/**
 * Created by Admin on 8/23/2017.
 */

public interface MainActivityContract {
    interface View extends BaseView{
        void updateCarList(List<Cars> carList);
        void onDataSaved(boolean isSaved);
    }
    interface Presenter extends BasePresenter<View>{
        void init();
        void pushToDb(Cars car);
        void getCars();
    }
}
