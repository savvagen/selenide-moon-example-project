package com.example.services;

import com.example.WebService;
import com.example.assertions.AssertableResponse;
import com.example.models.Address;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AddressService extends WebService {

    public AddressService(String basePath) {
        super(basePath);
    }

    public AssertableResponse getAddresses() {
        return new AssertableResponse(requestSpec.when().get("/addresses").then());
    }

    public AssertableResponse getAddress(String id) {
        return new AssertableResponse(requestSpec.when().get("/addresses/" + id).then());
    }

    public AssertableResponse addAddress(Address address) {
        return new AssertableResponse(requestSpec.when().body(address).post("/addresses").then());
    }

    public AssertableResponse deleteAddress(String id) {
        return new AssertableResponse(requestSpec.when().delete("/addresses/" + id).then());
    }


}
