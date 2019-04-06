package com.example.services;

import com.example.WebService;
import com.example.assertions.AssertableResponse;
import com.example.models.Address;
import com.example.models.Card;
import com.example.models.User;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j // The same as: Logger log = LoggerFactory.getLogger(this.getClass());
public class UserService extends WebService {

    public UserService(String basePath) {
        super(basePath);
    }

    public AssertableResponse login(){
        return new AssertableResponse(requestSpec.when().get("/login").then());
    }

    public AssertableResponse loginWith(User user) throws UnsupportedEncodingException {
        return new AssertableResponse(requestSpec.header("Authorization", "Basic " + Base64.getEncoder().encodeToString((user.username + ":" + user.password).getBytes("utf-8")))
                .when().get("/login").then());
    }

    public AssertableResponse getUsers(){
        log.info("get customers.");
        return new AssertableResponse(requestSpec.when().get("customers").then());
    }

    public AssertableResponse getUser(String id){
        return new AssertableResponse(requestSpec.when().get("customers/" + id).then());
    }

    public AssertableResponse registerUser(User user){
        log.info("register user {}", user);
        return new AssertableResponse(requestSpec.when().body(user).post("register").then());
    }

    public AssertableResponse deleteUser(String id){
        return new AssertableResponse(requestSpec.when().delete("customers/" + id).then());
    }

    public void deleteUserWithName(String userName){
        List<Map> customers = getUsers().response.extract().body()
                .jsonPath()
                .param("userName", userName)
                .get("_embedded.customer.findAll { customer -> customer.username = userName }");
        customers.forEach((customer)-> deleteUser(customer.get("id").toString()));
    }

    public void deleteAllUsers(){
        List<Map> customers = getUsers().response.extract().body()
                .jsonPath().get("_embedded.customer.findAll()");
        customers.forEach((customer)-> deleteUser(customer.get("id").toString()));
    }

    public void deleteAllUsersBesides(String username){
        List<Map> customers = getUsers().response.extract().body()
                .jsonPath().param("userName", username)
                .get("_embedded.customer.findAll { customer -> customer.username != userName }");
        customers.forEach((customer)-> deleteUser(customer.get("id").toString()));
    }




}
