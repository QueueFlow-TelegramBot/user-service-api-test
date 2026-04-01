package com.github.queueflow;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NewUserTest extends BaseTest {
    @BeforeMethod
    public static void beforeTest() {
        makeUser();
    }

    @Test
    public static void createNewValidUserTest() {
        given()
                .spec(REQUEST_SPEC)
                .body(user)
        .when()
                .post("/user")
        .then()
                .statusCode(201);
    }
}
