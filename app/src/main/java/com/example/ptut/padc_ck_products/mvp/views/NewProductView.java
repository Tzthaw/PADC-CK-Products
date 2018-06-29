package com.example.ptut.padc_ck_products.mvp.views;

import com.example.ptut.padc_ck_products.mvp.views.base.BaseView;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;

public interface NewProductView extends BaseView{
    void launchProductDetail(int id);
}
