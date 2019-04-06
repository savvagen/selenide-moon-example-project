package com.example.assertions.conditions;


import com.example.assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class ContentTypeCondition implements Condition {

    private String contentType;


    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().contentType(contentType);
    }


    @Override
    public String toString(){
        return "Content Type " + contentType;
    }

}
