package com.example.ptut.padc_ck_products.mvp.presenters;

import android.arch.lifecycle.LiveData;

import com.example.ptut.padc_ck_products.models.NewProductModel;
import com.example.ptut.padc_ck_products.mvp.presenters.base.BasePresenter;
import com.example.ptut.padc_ck_products.mvp.views.ProductDetailView;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;

public class ProductDetailPresenter extends BasePresenter<ProductDetailView> {

    @Override
    public void initPresenter(ProductDetailView mView) {
        super.initPresenter(mView);
    }

    public LiveData<NewProductVO> getProductById(int id) {
        return NewProductModel.getInstance().getProductByIdLD(id);
    }
}
