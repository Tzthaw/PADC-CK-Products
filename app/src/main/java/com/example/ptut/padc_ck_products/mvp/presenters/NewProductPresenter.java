package com.example.ptut.padc_ck_products.mvp.presenters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.ptut.padc_ck_products.delegates.NewProductDelegate;
import com.example.ptut.padc_ck_products.models.NewProductModel;
import com.example.ptut.padc_ck_products.mvp.presenters.base.BasePresenter;
import com.example.ptut.padc_ck_products.mvp.views.NewProductView;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;

import java.util.List;

public class NewProductPresenter extends BasePresenter<NewProductView> implements NewProductDelegate {
    private MutableLiveData<List<NewProductVO>> mNewProductLD;

    @Override
    public void initPresenter(NewProductView view) {
        super.initPresenter(view);
        mNewProductLD = new MutableLiveData<>();
        NewProductModel.getInstance().startLoadingNewProducts(mNewProductLD, mErrorLD);
    }

    public LiveData<List<NewProductVO>> getNewProductsLD() {
        return mNewProductLD;
    }

    @Override
    public void onTapProductItem(NewProductVO newProductVO) {
        mView.launchProductDetail(newProductVO.getProductId());
    }
}
