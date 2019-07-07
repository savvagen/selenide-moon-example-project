package com.example.ui_tests;

import com.example.TestBase;
import com.example.extensions.dependency_management.WebserviceExtension;
import com.example.extensions.listener_extensions.TestListenerExtension;
import com.example.extensions.listener_extensions.TestWatcherExtension;
import com.example.models.User;
import com.example.pages.forms.LoginModal;
import com.example.services.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.example.extensions.dependency_management.WebserviceExtension.*;
import static org.junit.jupiter.api.MethodOrderer.*;


@ExtendWith(WebserviceExtension.class)

@ExtendWith(TestWatcherExtension.class)

@TestMethodOrder(OrderAnnotation.class)

public class TestExtensionsExample extends TestBase {

    // https://medium.com/@BillyKorando/whats-new-in-junit-5-4-7ce6870a9caa
    @RegisterExtension
    @Order(3)
    static TestListenerExtension extensionA = new TestListenerExtension("A");
    @RegisterExtension
    @Order(2)
    static TestListenerExtension extensionB = new TestListenerExtension("B");
    @RegisterExtension
    @Order(1)
    static TestListenerExtension extensionC = new TestListenerExtension("C");


    @RegisterExtension
    public static User user = new User().setUsername("savva").setFirstName("Savva").setLastName("Genchevskiy").setEmail("test@gmail.com").setPassword("hello.world");


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
    @Order(1)
    void shouldLoginWithExistingCustomer(){
        mainPage.open().loginButton.click();
        mainPage.loginWith(user);
        LoginModal.sucessMsg.shouldBe(visible).shouldHave(text("Login successful."));
        mainPage.userButton.shouldBe(visible).shouldHave(text("Logged in as Savva Genchevskiy"));
        mainPage.logoutButton.shouldBe(visible);
    }

    @Test
    @Order(2)
    void shouldOpenLoginFormWithLoginButton(){
        mainPage.open().loginButton.click();
        mainPage.loginModal.shouldBe(visible);
        LoginModal.title.shouldBe(visible).shouldHave(text("Customer Login"));
    }

    @Test
    @Order(3)
    void shouldNotLoginWithInvalidCredentials(){
        mainPage.open().loginButton.click();
        mainPage.loginWith(new User().setUsername("test").setPassword("123"));
        LoginModal.errorMsg.shouldBe(visible).shouldHave(text("Invalid login credentials."));
        mainPage.logoutButton.shouldNotBe(visible);
    }

}
