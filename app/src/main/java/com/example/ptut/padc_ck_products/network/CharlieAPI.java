package com.example.ptut.padc_ck_products.network;

import com.example.ptut.padc_ck_products.network.reponses.GetDataResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface CharlieAPI {

    @FormUrlEncoded
    @POST("ck/getNewProducts.php")
    Observable<GetDataResponse> loadNewProductList(
            @Field("access_token") String accessToken, @Field("page") int pageIndex);
}
