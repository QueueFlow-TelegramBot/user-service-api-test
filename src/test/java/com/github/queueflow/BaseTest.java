package com.github.queueflow;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseTest {
    public static final String BASE_URI = "http://192.168.0.139:31858/";
    public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();
}
