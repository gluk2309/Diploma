

package ru.iteco.fmhandroid.ui.test;


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
import ru.iteco.fmhandroid.ui.page.MainPage;

@RunWith(AllureAndroidJUnit4.class)
public class SectionHomeTest {

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
            mainPage.authorizationImageButtonVisible();
        } catch (Exception e) {
            utils.autoAuthorization();
        }
    }

    @Test
    @Story("G001")
    @DisplayName("Раскрыть список новостей")
    public void openCloseNewsListTest() {

        mainPage.clickMaterialButton();
        mainPage.checkTextAllNewsNotVisible();
        mainPage.clickMaterialButton();
        mainPage.checkTextAllNewsVisible();
    }

    @Test
    @Story("G002")
    @DisplayName("Раскрыть Новость на главной странице")
    public void openCloseNewsOnMainPageTest() {

        mainPage.checkTextAllNewsVisible();
        mainPage.clickButtonToExpandNewsCard();
        mainPage.checkItemDescriptionText();
    }


}
