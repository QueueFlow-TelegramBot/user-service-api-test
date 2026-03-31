package com.github.queueflow;

import com.github.javafaker.Faker;
import com.github.queueflow.dto.User;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ExistingUserTest extends BaseTest {
    public static User user;

    @BeforeTest
    public static void createUser() {
        ExistingUserTest.user = new User(
                new Faker(new Random()).number().digits(9),
                new Faker(new Random()).name().firstName()
        );

        given()
                .spec(REQUEST_SPEC)
                .body(ExistingUserTest.user)
        .when()
                .post("/user")
        .then()
                .statusCode(201);
    }

    @Test
    public static void getExistingUserByTelegramIdTest() {
        given()
                .spec(REQUEST_SPEC)
                .pathParam("telegram_id", ExistingUserTest.user.telegramId)
        .when()
                .get("/user/{telegram_id}")
        .then()
                .body("telegram_id", equalTo(ExistingUserTest.user.telegramId))
                .body("display_name", equalTo(ExistingUserTest.user.displayName))
                .statusCode(200);
    }

    @Test
    public static void getNonexistingUserByTelegramId() {
        given()
                .spec(REQUEST_SPEC)
                .pathParam("telegram_id", new Faker(new Random()).number().digits(9))
        .when()
                .get("/user/{telegram_id}")
        .then()
                .statusCode(404);
    }

    @Test
    public static void createExistingUserTokenTest() {
        given()
                .spec(REQUEST_SPEC)
                .queryParam("telegram_id", ExistingUserTest.user.telegramId)
        .when()
                .post("/user/token")
        .then()
                .statusCode(200);
    }

    @Test
    public static void createNonexistingUserTokenTest() {
        given()
                .spec(REQUEST_SPEC)
                .queryParam("telegram_id", new Faker(new Random()).number().digits(9))
        .when()
                .post("/user/token")
        .then()
                .statusCode(404);
    }


    @Test
    public static void createExistingValidUserTest() {
        given()
                .spec(REQUEST_SPEC)
                .body(ExistingUserTest.user)
        .when()
                .post("/user")
        .then()
                .statusCode(409);
    }
}
