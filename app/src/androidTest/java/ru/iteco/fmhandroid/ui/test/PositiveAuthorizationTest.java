package ru.iteco.fmhandroid.ui.test;


import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_AUTHORIZATION;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.help.Utils;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;


@RunWith(AllureAndroidJUnit4.class)
public class PositiveAuthorizationTest {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    Utils utils = new Utils();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    @DisplayName("Настройка теста")
    @Description("Выполняется перед каждым тестом")
    public void setUp() {
        try {
            authorizationPage.verifyRegistrationPage();
        } catch (Exception e) {
            utils.logOut();
        }

    }


    @Test
    @Story("A001")
    @DisplayName("Успешная авторизация")
    public void successfulAuthorizationTest() {
        authorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        mainPage.authorizationImageButtonVisible();
    }

    @Test
    @Story("A002")
    @DisplayName("Успешная авторизация и выход из учётной записи")
    public void successfulLoginAndLogOutTest() {
        authorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        mainPage.clickAuthorizationImageButton();
        mainPage.clickLogOutButton();
        utils.checkText(TEXT_AUTHORIZATION);
    }


}