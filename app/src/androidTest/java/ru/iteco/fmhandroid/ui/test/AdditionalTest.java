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
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.MissionPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.SidebarPage;

@RunWith(AllureAndroidJUnit4.class)
public class AdditionalTest {

    AboutPage aboutPage = new AboutPage();
    MainPage mainPage = new MainPage();
    SidebarPage sidebarPage = new SidebarPage();
    MissionPage missionPage = new MissionPage();
    NewsPage newsPage = new NewsPage();
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
    @Story("С001")
    @DisplayName("Отображение тематических цитат во вкладке с цитатами")
    public void displayingThematicQuotesInQuotesTabTest() {
        mainPage.clickButtonMission();
        missionPage.clickButtonMissionItem();
        missionPage.checkItemMission();
    }

    @Test
    @Story("L001")
    @DisplayName("Отображение информации о приложении")
    public void displayingApplicationInformationTest() {
        sidebarPage.clickButtonMainMenu();
        sidebarPage.clickMenuAbout();
        aboutPage.checkTextVersion();

    }

    @Test
    @Story("L004")
    @DisplayName("Переход во вкладку новости с  главной страницы")
    public void creationNewsInNewCategoryTest() {
        mainPage.clickAllNews();
        newsPage.newsPageVisible();
    }


}
