package com.intellio.tesa.core;

import java.util.List;

import retrofit.http.DELETE;
import retrofit.http.GET;

public interface ProductService {

    @GET(Constants.Http.URL_PRODUCTS_FRAG)
    List<Product> getProducts();
    @DELETE(Constants.Http.URL_BASE)
    void delete();
}
