package com.example;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public abstract class WebService {

    public String basePath;
    public RequestSpecification requestSpec;
    public final String defaultContentType = "application/json; charset=utf-8";

    public WebService(String basePath){
        this.requestSpec = RestAssured.given().relaxedHTTPSValidation()
                .contentType(defaultContentType)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .basePath(basePath);
    }



}
