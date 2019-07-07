package com.example.pages.forms;

import com.codeborne.selenide.SelenideElement;
import com.example.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class RegisterModal {

    private SelenideElement usernameField = $("#register-username-modal"),
                            firstNameField = $("#register-first-modal"),
                            lastNameField = $("#register-last-modal"),
                            emailFiled = $("#register-email-modal"),
                            passwordFiled = $("#register-password-modal"),
                            registerButton = $("button[onclick*='register()']");

    public static SelenideElement title = $(".modal-title"),
                                    closeButton = $("button.close"),
                                    successMsg = $("#registration-message > *[class*='success']"),
                                    errorMsg = $("#registration-message > *[class*='danger']");

    public MainPage register(String username, String firstName, String lastName, String email, String password){
        usernameField.setValue(username);
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        emailFiled.setValue(email);
        passwordFiled.setValue(password);
        registerButton.click();
        return new MainPage();
    }


}
