package com.example.ui_tests;

import com.example.TestBase;
import com.example.extensions.dependency_management.WebserviceExtension;
import com.example.models.User;
import com.example.pages.forms.LoginModal;
import com.example.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.codeborne.selenide.Condition.*;
import static com.example.extensions.dependency_management.WebserviceExtension.*;
import static com.example.pages.forms.LoginModal.*;

//@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(WebserviceExtension.class)
// Adding order execution for the tests
public class LoginTests extends TestBase {



    @RegisterExtension
    public static User user = new User()
            .setUsername("savva")
            .setFirstName("Savva")
            .setLastName("Genchevskiy")
            .setEmail("test@gmail.com").setPassword("hello.world");






    @BeforeAll
    public static void setUpTests(@Microservice UserService userService){
        userService.registerUser(user);
    }

    @AfterEach
    public void tearDownTest(){
        if (mainPage.logoutButton.is(visible)){
            mainPage.logout();
        }
    }

    @AfterAll
    public static void tearDownTests(@Microservice UserService userService){
        userService.deleteUserWithName(user.username);
    }



    @Test
    void positiveLogin(){
        mainPage.open().loginButton.click();
        mainPage.loginWith(user);
        sucessMsg.shouldBe(visible).shouldHave(text("Login successful."));
        mainPage.userButton.shouldBe(visible).shouldHave(text("Logged in as Savva Genchevskiy"));
        mainPage.logoutButton.shouldBe(visible);
    }

    @Test
    void shouldOpenLoginFormWithLoginButton(){
        mainPage.open().loginButton.click();
        mainPage.loginModal.shouldBe(visible);
        title.shouldBe(visible).shouldHave(text("Customer Login"));
    }

    @Test
    void shouldNotLoginWithInvalidCredentials(){
        mainPage.open().loginButton.click();
        mainPage.loginWith(new User().setUsername("test").setPassword("123"));
        errorMsg.shouldBe(visible).shouldHave(text("Invalid login credentials."));
        mainPage.logoutButton.shouldNotBe(visible);
    }


}
