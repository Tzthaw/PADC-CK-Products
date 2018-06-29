package com.example.ptut.padc_ck_products.network.reponses;

import com.example.ptut.padc_ck_products.persistence.datas.NewProductVO;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;


public class GetDataResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int pageNo;

    @SerializedName("newProducts")
    private List<NewProductVO> newProductVOS;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<NewProductVO> getNewProductVOS() {
        if (newProductVOS == null) {
            newProductVOS = new ArrayList<>();
        }
        return newProductVOS;
    }
}
