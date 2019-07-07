package com.exmple;

import com.example.models.User;
import com.example.services.CardService;
import com.example.services.CatalogService;
import com.example.services.UserService;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Locale;

import static com.example.assertions.Conditions.*;



public class TestBase {

    public static UserService userService;
    public static CardService cardService;
    public static CatalogService catalogService;

    public static User admin;

    public static Faker fakeGen;
    public static Gson gson;

    @BeforeAll
    static void setUpTests(){
        RestAssured.baseURI = "http://104.154.163.189/";
        userService = new UserService("/");
        cardService = new CardService("/cards");
        catalogService = new CatalogService("/");


        gson = new Gson();

        fakeGen = new Faker(new Locale("en-US"));

        admin = new User().setUsername("Eve_Berger")
                .setPassword("eve")
                .setId("57a98d98e4b00679b4a830af");
    }

    @AfterAll
    static void tearDownTests(){
    }





}
