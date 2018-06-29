package com.example.ptut.padc_ck_products.persistence.daos.base;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface BaseDao<T> {
    @Insert
    long insert(T data);

    @Insert
    long[] insertAll(List<T> datas);

    @Update
    void update(T data);

    @Delete
    void delete(T data);

    @Delete
    void deleteAll(List<T> datas);
}
