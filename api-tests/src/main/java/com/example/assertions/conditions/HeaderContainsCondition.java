package com.example.assertions.conditions;

import com.example.assertions.Condition;
import com.example.assertions.Conditions;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.hamcrest.Matcher;

@AllArgsConstructor
public class HeaderContainsCondition implements Condition {
    private String expectedHeaderName; // Example: Content-Type = "content/json"

    @Override
    public void check(ValidatableResponse response) {
        response.extract().headers().hasHeaderWithName(expectedHeaderName);
    }


    @Override
    public String toString() {
        return "Should have Header \"" + expectedHeaderName + "\"";
    }
}
