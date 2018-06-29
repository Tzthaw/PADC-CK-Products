package com.example.ptut.padc_ck_products.activities.base;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by aung on 12/2/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements Observer<String> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onChanged(@Nullable String s) {
        displayErrorMessage(s);
    }

    public void displayErrorMessage(String s){

    }
}
