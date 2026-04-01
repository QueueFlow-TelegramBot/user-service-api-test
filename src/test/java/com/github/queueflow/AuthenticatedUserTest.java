package com.github.queueflow;

import com.github.javafaker.Faker;
import com.github.queueflow.dto.User;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticatedUserTest extends BaseTest {
    public static String token;

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

        token = given()
                .spec(REQUEST_SPEC)
                .queryParam("telegram_id", user.telegramId)
        .when()
                .post("/user/token")
        .then()
                .statusCode(200)
                .extract().path("access_token");
    }

    @Test
    public static void updateAuthenticatedUserTest() {
        String newName = new Faker(new Random()).name().firstName();
        given()
                .spec(REQUEST_SPEC)
                .header("Authorization", "Bearer " + token)
                .body(new User(user.telegramId, newName))
        .when()
                .put("/user")
        .then()
                .statusCode(200)
                .body("display_name", equalTo(newName));
    }

    @Test
    public static void getAuthenticatedUserTest() {
        given()
                .spec(REQUEST_SPEC)
                .header("Authorization", "Bearer " + token)
        .when()
                .get("/user/me")
        .then()
                .statusCode(200);
    }

    @Test
    public static void getUnauthenticatedUserTest() {
        given()
                .spec(REQUEST_SPEC)
        .when()
                .get("/user/me")
        .then()
                .statusCode(401);
    }
}
