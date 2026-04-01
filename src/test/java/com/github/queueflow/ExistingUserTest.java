package com.github.queueflow;

import com.github.javafaker.Faker;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class ExistingUserTest extends BaseTest {
    @BeforeMethod
    public static void createUser() {
        makeUser();

        given()
                .spec(REQUEST_SPEC)
                .body(user)
        .when()
                .post("/user")
        .then()
                .statusCode(201);
    }

    @Test
    public static void getExistingUserByTelegramIdTest() {
        given()
                .spec(REQUEST_SPEC)
                .pathParam("telegram_id", user.telegramId)
        .when()
                .get("/user/{telegram_id}")
        .then()
                .statusCode(200)
                .body("telegram_id", equalTo(user.telegramId))
                .body("display_name", equalTo(user.displayName));
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
                .queryParam("telegram_id", user.telegramId)
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
                .body(user)
        .when()
                .post("/user")
        .then()
                .statusCode(409);
    }
}
