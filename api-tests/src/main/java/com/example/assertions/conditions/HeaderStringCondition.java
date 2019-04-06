package com.example.assertions.conditions;


import com.example.assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HeaderStringCondition implements Condition {

    private String headerName;
    private String expectedValue;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().header(headerName, expectedValue);
    }


    @Override
    public String toString() {
        return "Header \"" + headerName + "\" should equal: " + expectedValue;
    }
}
