package ru.iteco.fmhandroid.ui.test;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
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
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.MissionPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.SidebarPage;

@RunWith(AllureAndroidJUnit4.class)
public class AdditionalTest {


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Before
    @DisplayName("Настройка теста")
    @Description("Выполняется перед каждым тестом")
    public void setUp() {
        Utils.autoAuthorization();
    }

    @After
    @DisplayName("Закрыть приложение")
    @Description("Выполняется после завершения теста")
    public void tearDown() {
        Utils.logOut();
    }


    @Test
    @Story("С001")
    @DisplayName("Отображение тематических цитат во вкладке с цитатами")
    public void displayingThematicQuotesInQuotesTabTest() {
        MainPage.clickButtonMission();
        MissionPage.clickButtonMissionItem();
        MissionPage.checkItemMission();
    }

    @Test
    @Story("L001")
    @DisplayName("Отображение информации о приложении")
    public void displayingApplicationInformationTest() {
        SidebarPage.clickButtonMainMenu();
        SidebarPage.clickMenuAbout();
        AboutPage.checkTextVersion();

    }

    @Test
    @Story("L004")
    @DisplayName("Переход во вкладку новости с  главной страницы")
    public void creationNewsInNewCategoryTest() {
        MainPage.clickAllNews();
        NewsPage.newsPageVisible();
    }


}
