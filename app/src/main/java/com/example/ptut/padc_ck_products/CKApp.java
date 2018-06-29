package com.example.ptut.padc_ck_products;

import android.app.Application;

import com.example.ptut.padc_ck_products.models.NewProductModel;

public class CKApp extends Application {

    public static final String LOG_TAG = "CKApp";

    @Override
    public void onCreate() {
        super.onCreate();
        NewProductModel.initNewsModel(getApplicationContext());
    }
}
