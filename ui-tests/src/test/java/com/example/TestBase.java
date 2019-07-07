package com.example;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.pages.MainPage;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;


public class TestBase {

    public static MainPage mainPage;

    @BeforeAll
    static void setUp(){
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());

        Configuration.baseUrl = "http://104.154.163.189";
        //Configuration.browserSize="1376x768";
        RestAssured.baseURI = "http://104.154.163.189";

        //setWebDriver(new WebdriverProvider().createDriver(new DesiredCapabilities()));
        //getWebDriver().manage().window().setSize(new Dimension(1920, 1080));

        mainPage = new MainPage();
    }


    @AfterAll
    static void tearDown(){
        SelenideLogger.removeListener("allureSelenide");
    }



}
