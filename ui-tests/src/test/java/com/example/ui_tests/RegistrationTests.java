package com.example.ui_tests;

import com.example.TestBase;
import com.example.extensions.dependency_management.WebServiceParameterResolver;
import com.example.extensions.parameter_extension.RandomUser;
import com.example.extensions.parameter_extension.RandomUserParameterExtension;
import com.example.models.User;
import com.example.pages.forms.LoginModal;
import com.example.pages.forms.RegisterModal;
import com.example.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.example.models.Locales.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

//@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(RandomUserParameterExtension.class)
@ExtendWith(WebServiceParameterResolver.class)
public class RegistrationTests extends TestBase {

    public static User randomUser;


    @BeforeEach
    void setUpTests(@RandomUser(locale = Locale.UNITED_STATES) User user){
        randomUser = user;
    }

    @AfterEach
    public void tearDownTest(){
        if (mainPage.logoutButton.is(visible)){
            mainPage.logout();
        }
    }

    @AfterAll
    static void tearDownTests(UserService userService) {
        userService.deleteUserWithName(randomUser.username);
    }






    @Test
    void registerCustomer(){
        mainPage.open().registerButton.click();
        mainPage.registerWith(randomUser);
        RegisterModal.successMsg.shouldBe(visible).shouldHave(text("Registration and Login successful."));
        mainPage.userButton.shouldBe(visible).shouldHave(text("Logged in as " + randomUser.firstName +" " + randomUser.lastName));
        mainPage.logoutButton.shouldBe(visible);
    }


    @Test
    void shouldOpenRegistrationFormWithRegisterButton(){
        mainPage.open().registerButton.click();
        mainPage.registerModal.shouldBe(visible);
        RegisterModal.title.shouldBe(visible).shouldHave(text("Register"));
    }

    @Test
    void shouldNotLoginWithInvalidCredentials(){
        mainPage.open().registerButton.click();
        mainPage.registerWith(new User().setUsername("").setPassword("").setEmail("test"));
        LoginModal.errorMsg.shouldBe(visible).shouldHave(text("There was a problem with your registration: Internal Server Error"));
        mainPage.logoutButton.shouldNotBe(visible);
    }


}
