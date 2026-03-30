package com.github.queueflow;

import com.github.javafaker.Faker;
import com.github.queueflow.dto.User;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;


public class ExistingUserTest {
    public static User user;

    @BeforeTest
    public static void createUser() {
        ExistingUserTest.user = new User(
                new Faker(new Random()).number().digits(9),
                new Faker(new Random()).name().firstName()
        );
    }

    @Test
    public static void getExistingUserByTelegramIdTest() {

    }

    @Test
    public static void getNonexistingUserByTelegramId() {

    }

    @Test
    public static void createExistingUserTokenTest() {

    }

    @Test
    public static void createNonexistingUserTokenTest() {

    }


    @Test
    public static void createExistingValidUserTest() {

    }
}
