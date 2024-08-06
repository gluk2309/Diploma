package ru.iteco.fmhandroid.ui.test;


import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.EMPTY_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.EMPTY_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.ERROR_EMPTY_LOGIN_AND_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.ERROR_SOMETHING_WENT_WRONG;
import static ru.iteco.fmhandroid.ui.res.Constants.INVALID_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.INVALID_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.SQL_INJECTION_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.SQL_INJECTION_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.UPPERCASE_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.UPPERCASE_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.XSS_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.XSS_PASSWORD;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.help.Utils;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;


@RunWith(AllureAndroidJUnit4.class)
public class NegativeAuthorizationTest {


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    @DisplayName("Настройка теста")
    @Description("Выполняется перед каждым тестом")
    public void setUp() {
        AuthorizationPage.verifyRegistrationPage();
    }


    @Test
    @Story("A003")
    @DisplayName("Авторизация с неверным логином")
    public void authorizationInCorrectLoginTest() {
        AuthorizationPage.enterLoginAndPassword(INVALID_LOGIN, DEFAULT_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);
    }

    @Test
    @Story("A004")
    @DisplayName("Авторизация с неверным паролем")
    public void authorizationInCorrectPasswordTest() {
        AuthorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, INVALID_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);

    }

    @Test
    @Story("A005")
    @DisplayName("Авторизация с незаполнеными полями Логин и Пароль")
    public void authorizationWithBlankLoginAndPasswordFieldsTest() {
        AuthorizationPage.enterLoginAndPassword(EMPTY_LOGIN, EMPTY_PASSWORD);
        Utils.waitForErrorText(ERROR_EMPTY_LOGIN_AND_PASSWORD);
    }

    @Test
    @Story("A006")
    @DisplayName("Авторизация с использованием Caps в поле Логин")
    public void authorizationWithCapsInLoginFieldTest() {
        AuthorizationPage.enterLoginAndPassword(UPPERCASE_LOGIN, DEFAULT_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);
    }

    @Test
    @Story("A007")
    @DisplayName("Авторизация с использованием Caps в поле Пароль")
    public void authorizationWithCapsInPasswordFieldTest() {
        AuthorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, UPPERCASE_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);
    }

    @Test
    @Story("A008")
    @DisplayName("Авторизация с использованием SQL инъекций в поле Логин")
    public void authorizationWithSqlInjectionsInLoginFieldTest() {
        AuthorizationPage.enterLoginAndPassword(SQL_INJECTION_LOGIN, DEFAULT_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);

    }

    @Test
    @Story("A009")
    @DisplayName("Авторизация с использованием SQL инъекций в поле Пароль")
    public void authorizationWithSqlInjectionsInPasswordFieldTest() {
        AuthorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, SQL_INJECTION_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);
    }

    @Test
    @Story("A010")
    @DisplayName("Авторизация с использованием XSS инъекций в поле Логин")
    public void authorizationWithXssInjectionsInLoginFieldTest() {
        AuthorizationPage.enterLoginAndPassword(XSS_LOGIN, DEFAULT_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);
    }

    @Test
    @Story("A011")
    @DisplayName("Авторизация с использованием XSS инъекций в поле Пароль")
    public void authorizationWithXssInjectionsInPasswordFieldTest() {
        AuthorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, XSS_PASSWORD);
        Utils.waitForErrorText(ERROR_SOMETHING_WENT_WRONG);
    }


}