package com.exmple.tests;

import com.example.extensions.listeners.AllureLoggingListener;
import com.example.extensions.listeners.TestLoggingListener;
import com.example.extensions.parameter_extension.RandomUser;
import com.example.extensions.parameter_extension.RandomUserParameterExtension;
import com.example.extensions.parameter_extension.RandomUserParameterResolver;
import com.example.models.Locales;
import com.example.models.User;
import com.exmple.TestBase;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.UnsupportedEncodingException;

import static com.example.assertions.Conditions.*;
import static com.example.models.Locales.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@Tag("users")
@Execution(ExecutionMode.SAME_THREAD)

@Feature("User Service Tests")
@ExtendWith({AllureLoggingListener.class, TestLoggingListener.class})
// Register RandomUser parameter resolver to class: @ExtendWith(RandomUserParameterResolver.class) to add parameters:
//   1)
//      @BeforeAll
//      setUp(User user) {
//      }
//   2)
//      @Test
//      shouldRegisterUser(User user) {
//      }
//@ExtendWith(RandomUserParameterResolver.class)

// Register RandomUser parameter extension with @ExtendWith(RandomUserParameterExtension.class) to add parameters:
//   1)
//      @BeforeAll
//      setUp(@RandomUser(locale = Locale.RUSSIA) User user) {
//      }
//   2)
//      @Test
//      shouldRegisterUser(@RandomUser(locale = Locale.RUSSIA) User user) {}
//@ExtendWith(RandomUserParameterResolver.class)
@ExtendWith(RandomUserParameterExtension.class)
public class UserServiceTests extends TestBase {

    public static User fakeUser;
    @RegisterExtension public static User myUser = User.generate().withLocale(Locale.UKRAINE);


    @BeforeAll
    static void setUp(@RandomUser(locale = Locale.UKRAINE) User user){
        fakeUser = user;
        userService.registerUser(admin);
    }

    @AfterAll
    static void tearDown(){
        userService.deleteUserWithName(myUser.username);
    }



    @Test
    @Story("negative")
    @DisplayName("User should login")
    void userShouldLogin() {
        userService.login().shouldHave(statusCode(401))
                .response.extract().headers()
                .hasHeaderWithName("Content-Type=" + userService.defaultContentType);
    }


    @Test
    @Story("positive")
    @DisplayName("User should loin with creds")
    void userShouldLoginWithCredentials() throws UnsupportedEncodingException {
        userService.loginWith(admin)
                .shouldHave(statusCode(200))
                .shouldHave(body(containsString("Cookie is set")));
    }


    @Test
    @Story("positive")
    @DisplayName("Get all users in the system")
    void shouldGetAllUsers(){
        userService.getUsers()
                .shouldHave(statusCode(200))
                .shouldHave(header("Content-Type=" + userService.defaultContentType));
    }


    @Test
    @Disabled
    @DisplayName("Get Specific customer")
    void shouldGetSpecificUser(){
       userService.getUser(admin.getId())
                .shouldHave(statusCode(200))
                .shouldHave(header("Content-Type=" + userService.defaultContentType))
                .shouldHave(body("customer.username", equalTo(admin.getUsername())));

    }


    @Test
    @Story("positive")
    @DisplayName("Register customer")
    void shouldRegisterUser(){
        userService.registerUser(myUser)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(userService.defaultContentType))
                .shouldHave(body("id", notNullValue()))
                .shouldHave(body("id", containsString("0")))
                .shouldHave(body(matchesJsonSchemaInClasspath("json/register.json")));
    }

    @ExtendWith(RandomUserParameterExtension.class)
    @Test
    @Story("positive")
    @DisplayName("Delete Customer")
    void shouldDeleteUser(@RandomUser(locale = Locale.RUSSIA) User user){
        String userId = userService.registerUser(user).response.extract().body().jsonPath().get("id");
        userService.deleteUser(userId)
                .shouldHave(statusCode(200))
                .shouldHave(bodyContains("{\\\"status\\\":true}"))
                .shouldHave(header("Content-Type=" + userService.defaultContentType));
    }




}
