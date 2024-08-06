package ru.iteco.fmhandroid.ui.res;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerDataGenerator {
    private static Faker faker = new Faker(new Locale("ru"));

    public static String generateFakeTitle() {
        return faker.lorem().word() + " " + faker.lorem().word();
    }

    public static String generateFakeEditTitle() {
        return faker.lorem().word();
    }

    public static String generateFakeDescription() {
        return faker.lorem().word() + " "
                + faker.lorem().word() + " "
                + faker.lorem().word();
    }
}