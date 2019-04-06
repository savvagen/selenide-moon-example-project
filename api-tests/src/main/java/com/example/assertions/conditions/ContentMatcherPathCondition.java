package com.example.assertions.conditions;

import com.example.assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class ContentMatcherPathCondition implements Condition {

    private String key;
    private Matcher matcher;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().content(key, matcher);
    }

    @Override
    public String toString(){
        return "Content in " + key + " should match: " + matcher;
    }
}
