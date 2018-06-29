package com.example.ptut.padc_ck_products.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ptut.padc_ck_products.persistence.daos.NewProductDao;
import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;

@Database(entities = NewProductVO.class,version =1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase appDatabase;
    public abstract NewProductDao newProductDao();

    public static AppDatabase getInMemoryDatabase(Context context){
        if(appDatabase==null){
            appDatabase= Room.inMemoryDatabaseBuilder(context,AppDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public void destroyInDatabase(){
        appDatabase=null;
    }
}
