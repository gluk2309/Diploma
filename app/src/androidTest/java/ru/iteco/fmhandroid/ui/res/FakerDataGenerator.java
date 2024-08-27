package ru.iteco.fmhandroid.ui.res;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerDataGenerator {
    private Faker faker = new Faker(new Locale("ru"));

    public String generateFakeTitle() {
        return faker.lorem().word() + " " + faker.lorem().word();
    }

    public String generateFakeEditTitle() {
        return faker.lorem().word();
    }

    public String generateFakeDescription() {
        return faker.lorem().word() + " "
                + faker.lorem().word() + " "
                + faker.lorem().word();
    }
}