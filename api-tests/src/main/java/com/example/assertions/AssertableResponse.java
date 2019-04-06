package com.example.assertions;

import com.example.services.UserService;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {


    public static final Logger logger = LoggerFactory.getLogger(AssertableResponse.class);


    public final ValidatableResponse response;

    @Step
    public AssertableResponse shouldHave(Condition condition){
        log.info("Check for: " + condition.toString());
        condition.check(response);
        return this;
    }

    @Step
    public AssertableResponse shouldHave(Condition... conditions){
        List<Condition> conds = Arrays.asList(conditions);
        conds.forEach(condition -> {
            logger.info("Check for: " + condition.toString());
            condition.check(response);
        });
        return this;
    }


}
