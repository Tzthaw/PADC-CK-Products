package com.example.ptut.padc_ck_products.persistence.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.ptut.padc_ck_products.persistence.daos.base.BaseDao;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;

import java.util.List;

@Dao
public interface NewProductDao extends BaseDao<NewProductVO>{

    @Query("SELECT * FROM newProduct")
    LiveData<List<NewProductVO>> getAllNewProducts();

    @Query("DELETE FROM newProduct")
    void deleteAllNewProducts();

    @Query("SELECT * FROM newProduct WHERE `product-id` = :productId")
    LiveData<NewProductVO> getProductById(int productId);


}
