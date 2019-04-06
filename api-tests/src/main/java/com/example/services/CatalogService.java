package com.example.services;

import com.example.WebService;
import com.example.assertions.AssertableResponse;
import com.example.models.Card;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogService extends WebService {


    public CatalogService(String basePath) {
        super(basePath);
    }


    public AssertableResponse getProducts(){
        return new AssertableResponse(requestSpec.when().get("/catalogue").then());
    }

    public AssertableResponse getProduct(String productId){
        return new AssertableResponse(requestSpec.when().get("/catalogue/" + productId).then());
    }

    public AssertableResponse getProductsCount(){
        return new AssertableResponse(requestSpec.when().get("/catalogue/size").then());
    }

    public AssertableResponse getTags(){
        return new AssertableResponse(requestSpec.when().get("/tags").then());
    }

}
