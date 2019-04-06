package com.example.assertions.conditions;

import com.example.assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class BodyMatcherPathCondition implements Condition {

    private String path;
    private Matcher matcher;


    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().body(path, matcher);
    }


    @Override
    public String toString() {
        return "Response body field \'" + path +"\' should match the condition: " + matcher;
    }
}
