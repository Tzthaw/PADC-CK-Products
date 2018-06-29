package com.example.ptut.padc_ck_products.persistence.datas;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "newProduct")
public class NewProductVO {

    @PrimaryKey
    @ColumnInfo(name = "product-id")
    @SerializedName("product-id")
    int productId;

    @ColumnInfo(name = "product-image")
    @SerializedName("product-image")
    String productImage;

    @ColumnInfo(name = "product-title")
    @SerializedName("product-title")
    String productTitle;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
