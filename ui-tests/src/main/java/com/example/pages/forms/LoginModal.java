package com.example.pages.forms;

import com.codeborne.selenide.SelenideElement;
import com.example.pages.MainPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class LoginModal {

    private SelenideElement usernameField = $("#username-modal"),
                    passwordField = $("#password-modal"),
                    loginButton = $("button[onclick='return login()'");

    public static SelenideElement closeButton = $("button.close"),
                                  title = $(".modal-title"),
                                  errorMsg = $("#login-message div[class*='danger']"),
                                  sucessMsg = $("#login-message div[class*='success']"),
                                  loginModal = $("#login-modal .modal-content");


    public MainPage login(String username, String password){
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
        return new MainPage();
    }



}
