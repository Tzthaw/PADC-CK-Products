package com.example.ptut.padc_ck_products.models.base;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import com.example.ptut.padc_ck_products.network.CharlieAPI;
import com.example.ptut.padc_ck_products.persistence.AppDatabase;
import com.example.ptut.padc_ck_products.share.AppConstant;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseModel {

    protected CharlieAPI theApi;
    protected AppDatabase appDatabase;

    protected BaseModel(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-5/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(CharlieAPI.class);
        appDatabase=AppDatabase.getInMemoryDatabase(context);

    }
}
