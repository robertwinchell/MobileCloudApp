package com.intellio.tesa.core;

import java.util.Date;
import java.util.List;

import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ProductService {

    @GET(Constants.Http.URL_PRODUCTS_FRAG)
    List<Product> getProducts();
    @DELETE(Constants.Http.URL_PRODUCTS_DELETE_FRAG)
    boolean delete(@Path("name") String name);
    @POST(Constants.Http.URL_PRODUCTS_FRAG)
    @FormUrlEncoded
    String add(@Field("name") String name,
                @Field("datePurchase") Date datePurchase,
                @Field("dateWarranty") Date dateWarranty,
                @Field("price") Double price,
                @Field("problemDescription") String problemDescription);
    @PUT(Constants.Http.URL_PRODUCTS_UPDATE_FRAG)
    @FormUrlEncoded
    String update(@Path("id") String id, @Field("name") String name,
                   @Field("datePurchase") Date datePurchase,
                   @Field("dateWarranty") Date dateWarranty,
                   @Field("price") Double price,
                   @Field("problemDescription") String problemDescription);
}
