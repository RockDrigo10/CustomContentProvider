package com.example.admin.customcontentprovider.view.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.customcontentprovider.R;
import com.example.admin.customcontentprovider.injection.mainactivity.DaggerMainActivityComponent;
import com.example.admin.customcontentprovider.model.Cars;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivity";
    @Inject
    MainActivityPresenter presenter;

    EditText etModel, etBrand, etDescription, etColor, etYear;
    //DataBaseHelper dataBase;
    ArrayList<Cars> carsList =  new ArrayList<>();
    RecyclerView rvCars;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    CarAdapter carAdapter;
    private static final String PROVIDER_NAME = "androidcontentproviderdemo.androidcontentprovider.cars";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/cars");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        etModel = (EditText) findViewById(R.id.etModel);
        etBrand = (EditText) findViewById(R.id.etBrand);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etColor = (EditText) findViewById(R.id.etColor);
        etYear = (EditText) findViewById(R.id.etYear);
        setupDagger();
        presenter.init();
        presenter.attachView(this);
        rvCars = (RecyclerView) findViewById(R.id.rvCars);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        itemAnimator = new DefaultItemAnimator();
        rvCars.setLayoutManager(layoutManager);
        rvCars.setItemAnimator(itemAnimator);
        refreshValuesFromContentProvider();

        carAdapter = new CarAdapter(carsList);
        rvCars.setAdapter(carAdapter);
        carAdapter.notifyDataSetChanged();
    }

    private void refreshValuesFromContentProvider() {
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String MODEL = cursor.getString(cursor.getColumnIndex("Model"));
                    String BRAND = cursor.getString(cursor.getColumnIndex("Brand"));
                    String YEAR = cursor.getString(cursor.getColumnIndex("Year"));
                    String DESCRIPTION = cursor.getString(cursor.getColumnIndex("Description"));
                    String COLOR = cursor.getString(cursor.getColumnIndex("Color"));
                    carsList.add(new Cars(MODEL, BRAND, Integer.parseInt(YEAR), COLOR,DESCRIPTION));
                }
            }
        }
    }

    private void setupDagger() {
        DaggerMainActivityComponent.create().inject(this);
    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void updateCarList(List<Cars> carList) {

    }

    @Override
    public void onDataSaved(boolean isSaved) {
        Log.d(TAG, "onDataSaved: " + isSaved);
    }

    public void saveCar(View view) {
        try {
//             Using pojo to firebase
//           if(etModel.getText().equals("")||
//                    etBrand.getText().equals("")||
//                    etYear.getText().equals("")||
//                    etColor.getText().equals("")||
//                    etDescription.getText().equals("")) {
//                Toast.makeText(this, "Insert all field", Toast.LENGTH_LONG).show();
//            }
//            else{
//                Cars car = new Cars(etModel.getText().toString()
//                    , etBrand.getText().toString()
//                    , Integer.parseInt(etYear.getText().toString())
//                    , etColor.getText().toString()
//                    , etDescription.getText().toString());
//            presenter.pushToDb(car);
//            cleanData();}
            ///////////using pojo to SQLte
//            Cars car = new Cars("-1",etModel.getText().toString()
//                    , etBrand.getText().toString()
//                    , Integer.parseInt(etYear.getText().toString())
//                    , etColor.getText().toString()
//                    , etDescription.getText().toString());
//            dataBase.saveNewContact(car);
//            Toast.makeText(this, "Contact Saved .....", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Car inserted into the Data Base!", Toast.LENGTH_LONG).show();
            if (etModel.getText().equals("") || etBrand.getText().equals("") || etYear.getText().equals("") ||
                    etColor.getText().equals("") || etDescription.getText().equals("")) {
                Toast.makeText(this, "Insert all field", Toast.LENGTH_LONG).show();
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put("Model", ((EditText) findViewById(R.id.etModel)).getText().toString());
                contentValues.put("Brand", ((EditText) findViewById(R.id.etBrand)).getText().toString());
                contentValues.put("Year", ((EditText) findViewById(R.id.etYear)).getText().toString());
                contentValues.put("Color", ((EditText) findViewById(R.id.etColor)).getText().toString());
                contentValues.put("Description", ((EditText) findViewById(R.id.etDescription)).getText().toString());
                Uri uri = getContentResolver().insert(CONTENT_URI, contentValues);
                Toast.makeText(getBaseContext(),"Car inserted successfully ", Toast.LENGTH_LONG).show();
                carsList.add(new Cars(
                        etModel.getText().toString(),
                        etDescription.getText().toString(),
                        Integer.parseInt(etYear.getText().toString()),
                        etColor.getText().toString(),
                        etBrand.getText().toString()
                ));
                carAdapter.notifyItemInserted(carsList.size() - 1);
                cleanData();
            }
        } catch (Exception e) {
            Toast.makeText(this, " Car was not inserted " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void cleanData() {
        etYear.setText("");
        etModel.setText("");
        etDescription.setText("");
        etColor.setText("");
        etBrand.setText("");
    }

    public void stateListDrawable(View view) {
        Toast.makeText(this, "Clicked........", Toast.LENGTH_SHORT).show();

    }
}
