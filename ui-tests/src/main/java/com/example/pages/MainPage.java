package com.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.models.User;
import com.example.pages.forms.LoginModal;
import com.example.pages.forms.RegisterModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class MainPage extends WebPage {

    public MainPage() {
        url = "/";
        title = "test";
    }

    public SelenideElement loginButton = $("#login > a"),
                                  registerButton = $("#register"),
                                  logoutButton = $(byText("Logout")),
                                  loginModal = $("#login-modal .modal-content"),
                                  registerModal = $("#login-modal .modal-content"),
                                  userButton = $("#howdy a");

    @Step
    public MainPage open(){
        return Selenide.open(url, this.getClass());
    }

    @Step
    public MainPage loginWith(User user){
       return new LoginModal().login(user.username, user.password);
    }

    @Step
    public MainPage registerWith(User user){
        return new RegisterModal().register(user.username, user.firstName, user.lastName, user.email, user.password);
    }

    @Step
    public MainPage logout(){
        logoutButton.shouldBe(visible).click();
        loginButton.shouldBe(visible);
        return this;
    }


}
