package com.github.queueflow;

import com.github.javafaker.Faker;
import com.github.queueflow.config.Environment;
import com.github.queueflow.dto.User;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigCache;

import java.util.Random;

public abstract class BaseTest {
    public static final String BASE_URI = ConfigCache.getOrCreate(Environment.class).baseUri();
    public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build()
            .filter(new AllureRestAssured());

    public static User user;

    public static void makeUser() {
        ExistingUserTest.user = new User(
                new Faker(new Random()).number().digits(9),
                new Faker(new Random()).name().firstName());
    }
}
