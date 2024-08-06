package ru.iteco.fmhandroid.ui.page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.res.Constants.ALL_NEWS;
import static ru.iteco.fmhandroid.ui.res.Constants.EXIT;
import static ru.iteco.fmhandroid.ui.res.Constants.OUR_MISSION;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.help.Utils;


public class MainPage {

    private static final int ID_AUTHORIZATION_IMAGE_BUTTON = R.id.authorization_image_button;
    private static final int ID_ANDROID_TITLE = android.R.id.title;
    private static final int ID_EXPAND_MATERIAL_BUTTON = R.id.expand_material_button;
    private static final int ID_ALL_NEWS_TEXT_VIEW = R.id.all_news_text_view;
    private static final int ID_NEWS_LIST_RECYCLER_VIEW = R.id.news_list_recycler_view;
    private static final int ID_NEWS_ITEM_DESCRIPTION_TEXT_VIEW = R.id.news_item_description_text_view;
    private static final int ID_NEWS_ITEM_MATERIAL_CARD_VIEW = R.id.news_item_material_card_view;
    private static final int ID_ABOUT_BACK_IMAGE_BUTTON = R.id.about_back_image_button;

    private static final ViewInteraction backAboutPage = onView(withId(ID_ABOUT_BACK_IMAGE_BUTTON));
    private static final ViewInteraction authorizationImageButton = onView(withId(ID_AUTHORIZATION_IMAGE_BUTTON));
    private static final ViewInteraction logOutButton = onView(allOf(withId(ID_ANDROID_TITLE), withText(EXIT)));
    private static final ViewInteraction materialButton = onView(withId(ID_EXPAND_MATERIAL_BUTTON));
    private static final ViewInteraction textAllNews = onView(withText(ALL_NEWS));
    private static final ViewInteraction allNews = onView(withId(ID_ALL_NEWS_TEXT_VIEW));
    private static final ViewInteraction buttonMission = onView(withContentDescription(OUR_MISSION));
    private static final ViewInteraction buttonToExpandNewsCard = onView(withId(ID_NEWS_LIST_RECYCLER_VIEW));
    private static final ViewInteraction itemDescriptionText = onView(
            allOf(withId(ID_NEWS_ITEM_DESCRIPTION_TEXT_VIEW),
                    withParent(withParent(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW))),
                    isDisplayed()));


    public static void clickBackAboutPage() {
        Allure.step("Нажать кнопку назад на странице о приложении");
        backAboutPage.check(matches(isDisplayed())).perform(click());
    }


    public static void authorizationImageButtonVisible() {
        Allure.step("Кнопка авторизированого пользователя отображается");
        Utils.waitFor(ID_AUTHORIZATION_IMAGE_BUTTON);
        authorizationImageButton.check(matches(isDisplayed()));
    }

    public static void clickAuthorizationImageButton() {
        Allure.step("Нажать профиль пользователя");
        Utils.waitFor(ID_AUTHORIZATION_IMAGE_BUTTON);
        authorizationImageButton.check(matches(isDisplayed())).perform(click());
    }


    public static void clickLogOutButton() {
        Allure.step("Нажать кнопку выйти");
        Utils.waitFor(ID_ANDROID_TITLE);
        logOutButton.perform(click());
    }

    public static void clickMaterialButton() {
        Allure.step("Нажать кнопку Развернуть/свернуть панель новостей");
        materialButton.check(matches(isDisplayed())).perform(click());
    }

    public static void checkTextAllNewsVisible() {
        Allure.step("Проверить что текст ВСЕ НОВОСТИ отображается");
        textAllNews.check(matches(isDisplayed()));
    }

    public static void checkTextAllNewsNotVisible() {
        Allure.step("Проверить что текст ВСЕ НОВОСТИ Не отображается");
        textAllNews.check(matches(not(isDisplayed())));
    }

    public static void clickAllNews() {
        Allure.step("Нажать на ВСЕ НОВОСТИ");
        allNews.check(matches(isDisplayed())).perform(click());
    }

    public static void clickButtonMission() {
        Allure.step("Нажать кнопку тематические цитаты");
        buttonMission.check(matches(isDisplayed())).perform(click());
    }


    public static void clickButtonToExpandNewsCard() {
        Allure.step("Нажать раскрыть карточку новость");
        buttonToExpandNewsCard
                .check(matches(isDisplayed()))
                .check(matches(hasMinimumChildCount(1))) // проверка на наличие хотя бы одного элемента в списке
                .perform(actionOnItemAtPosition(0, click()));
    }

    public static void checkItemDescriptionText() {
        Allure.step("Проверить тест описания");
        itemDescriptionText.check(matches(isDisplayed()));
    }

}

