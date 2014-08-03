
package com.intellio.tesa.core;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Bootstrap API service
 */
public class TesaService {

    private RestAdapter restAdapter;

    /**
     * Create tesa service
     * Default CTOR
     */
    public TesaService() {
    }

    /**
     * Create tesa service
     *
     * @param restAdapter The RestAdapter that allows HTTP Communication.
     */
    public TesaService(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    private UserService getUserService() {
        return getRestAdapter().create(UserService.class);
    }
    private ProductService getProductService(){
        return getRestAdapter().create(ProductService.class);
    }
    public List<Product> getProducts(){return getProductService().getProducts();}

    private RestAdapter getRestAdapter() {
        return restAdapter;
    }






    public User authenticate(String email, String password) {
        return getUserService().authenticate(email, password);
    }
}