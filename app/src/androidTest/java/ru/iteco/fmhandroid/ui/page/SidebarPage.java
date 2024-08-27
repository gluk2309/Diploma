package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.res.Constants.ABOUT_APPLICATION;
import static ru.iteco.fmhandroid.ui.res.Constants.MENU_MAIN_TEXT;
import static ru.iteco.fmhandroid.ui.res.Constants.MENU_NEWS_TEXT;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class SidebarPage {

    private final int ID_MAIN_MENU_IMAGE_BUTTON = R.id.main_menu_image_button;
    private final int ID_ANDROID_TITLE = android.R.id.title;

    private final ViewInteraction buttonMainMenu = onView(withId(ID_MAIN_MENU_IMAGE_BUTTON));
    private final ViewInteraction menuNews = onView(allOf(withId(ID_ANDROID_TITLE), withText(MENU_NEWS_TEXT)));
    private final ViewInteraction menuMain = onView(allOf(withId(ID_ANDROID_TITLE), withText(MENU_MAIN_TEXT)));
    private final ViewInteraction menuAbout = onView(allOf(withId(ID_ANDROID_TITLE), withText(ABOUT_APPLICATION)));


    public void clickButtonMainMenu() {
        Allure.step("Нажать Меню сайдбара");
        buttonMainMenu.check(matches(isDisplayed())).perform(click());
    }

    public void clickMenuMain() {
        Allure.step("Нажать Меню Главная");
        menuMain.check(matches(isDisplayed())).perform(click());
    }

    public void clickMenuNews() {
        Allure.step("Нажать Меню Новости");
        menuNews.check(matches(isDisplayed())).perform(click());
    }

    public void clickMenuAbout() {
        Allure.step("Нажать О приложении");
        menuAbout.check(matches(isDisplayed())).perform(click());
    }


}
