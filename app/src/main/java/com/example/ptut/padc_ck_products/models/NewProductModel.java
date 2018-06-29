package com.example.ptut.padc_ck_products.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.EventLog;
import android.util.Log;

import com.example.ptut.padc_ck_products.models.base.BaseModel;
import com.example.ptut.padc_ck_products.network.reponses.GetDataResponse;
import com.example.ptut.padc_ck_products.persistence.AppDatabase;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;
import com.example.ptut.padc_ck_products.share.AppConstant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewProductModel extends BaseModel{

    private int cNewProductIndex=2;
    private static NewProductModel objInstance;

    private NewProductModel(Context context) {
        super(context);
    }

    public static void initNewsModel(Context context) {
        objInstance = new NewProductModel(context);
    }

    public static NewProductModel getInstance() {
        if (objInstance == null) {
            throw new RuntimeException("NewProductModel is being invoked before initializing.");
        }
        return objInstance;
    }


    public void startLoadingNewProducts(final MutableLiveData<List<NewProductVO>> newProductListLD
            , final MutableLiveData<String> errorLD){
        theApi.loadNewProductList(AppConstant.ACCESS_TOKEN,cNewProductIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetDataResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(GetDataResponse getDataResponse) {
                        if(getDataResponse!=null && getDataResponse.getNewProductVOS().size()>0){
                            savedNewProducts(getDataResponse.getNewProductVOS());
                            newProductListLD.setValue(getDataResponse.getNewProductVOS());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLD.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
    }

    private void savedNewProducts(List<NewProductVO> newProductVOS){
        appDatabase.newProductDao().deleteAllNewProducts();
        if(newProductVOS.size()>0){
            long[] ids=appDatabase.newProductDao().insertAll(newProductVOS);
            for (int i=0;i<ids.length;i++){
                Log.e("Product",ids[i]+"");
            }

        }
    }

    public LiveData<NewProductVO> getProductByIdLD(int id){
        final MutableLiveData<NewProductVO> newProductLD=new MutableLiveData<>();
        appDatabase.newProductDao().getProductById(id).observeForever(new android.arch.lifecycle.Observer<NewProductVO>() {
            @Override
            public void onChanged(@Nullable NewProductVO newProductVO) {
                newProductLD.setValue(newProductVO);
            }
        });

        return newProductLD;
    }


}
