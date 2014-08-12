
package com.intellio.tesa.core;

import java.util.Date;
import java.util.List;

import retrofit.RestAdapter;

/**
 * API service
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
    /**
     * Gets the product list
     *
     */
    public List<Product> getProducts(){return getProductService().getProducts();}
    /**
     * Deletes a product by name
     * */
    public boolean deleteProducts(String name){return getProductService().delete(name);}
    /**
     * Adds a new product
     * */
    public boolean addProduct(Product product){
         getProductService().add( product.getTitle(),product.getDate_purchase(),product.getDate_warranty(),
                product.getPrice(),product.getProblem_description());
        return true;
    }
    /**
     * Updates a product
     * */
    public boolean updateProduct(Product product){
         getProductService().update(product.getId(), product.getTitle(),product.getDate_purchase(),product.getDate_warranty(),
                product.getPrice(),product.getProblem_description());
        return true;
    };
    private RestAdapter getRestAdapter() {
        return restAdapter;
    }






    /**
     * User authentication
     * */
    public User authenticate(String email, String password) {
        return getUserService().authenticate(email, password);
    }
}