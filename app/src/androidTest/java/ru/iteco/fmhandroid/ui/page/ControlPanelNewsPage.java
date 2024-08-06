package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
public class ControlPanelNewsPage {

    private static final int ID_ADD_NEWS_IMAGE_VIEW = R.id.add_news_image_view;
    private static final int ID_NEWS_LIST_RECYCLER_VIEW = R.id.news_list_recycler_view;
    private static final int ID_FILTER_NEWS_MATERIAL_BUTTON = R.id.filter_news_material_button;
    private static final int ID_BUTTON1 = android.R.id.button1;

    private static final int ID_NEWS_ITEM_TITLE_TEXT_VIEW = R.id.news_item_title_text_view;
    private static final int ID_EDIT_NEWS_ITEM_IMAGE_VIEW = R.id.edit_news_item_image_view;
    private static final int ID_DELETE_NEWS_ITEM_IMAGE_VIEW = R.id.delete_news_item_image_view;
    private static final int ID_VIEW_NEWS_ITEM_IMAGE_VIEW = R.id.view_news_item_image_view;
    private static final int ID_NEWS_ITEM_PUBLISHED_TEXT_VIEW = R.id.news_item_published_text_view;
    private static final int ID_NEWS_ITEM_MATERIAL_CARD_VIEW = R.id.news_item_material_card_view;
    private static final int ID_NEWS_ITEM_CREATE_DATA_TEXT_VIEW = R.id.news_item_create_date_text_view;
    private static final int ID_NEWS_ITEM_PUBLICATION_DATA_TEXT_VIEW = R.id.news_item_publication_date_text_view;

    private static final ViewInteraction buttonAddNews = onView(withId(ID_ADD_NEWS_IMAGE_VIEW));
    private static final ViewInteraction recyclerView = onView(withId(ID_NEWS_LIST_RECYCLER_VIEW));
    private static final ViewInteraction buttonFilterOnNewsControlPanel = onView(withId(ID_FILTER_NEWS_MATERIAL_BUTTON));
    private static final ViewInteraction buttonOk = onView(withId(ID_BUTTON1));


    private static ViewInteraction textTitle(String title) {
        return onView(allOf(withId(ID_NEWS_ITEM_TITLE_TEXT_VIEW),
                withText(title)));
    }

    private static ViewInteraction buttonEditNews(String fakeTitle) {
        return onView(allOf(withId(ID_EDIT_NEWS_ITEM_IMAGE_VIEW),
                withParent(withParent(allOf(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW),
                        withChild(withChild(withText(fakeTitle))))))));
    }

    private static ViewInteraction status(String fakeTitle) {
        return onView(allOf(withId(ID_NEWS_ITEM_PUBLISHED_TEXT_VIEW),
                withParent(withParent(allOf(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW),
                        withChild(withChild(withText(fakeTitle))))))));
    }

    private static ViewInteraction delete(String fakeTitle) {
        return onView(allOf(withId(ID_DELETE_NEWS_ITEM_IMAGE_VIEW),
                withParent(withParent(allOf(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW),
                        withChild(withChild(withText(fakeTitle))))))));
    }

    private static ViewInteraction buttonToExpandNewsCard(String fakeTitle) {
        return onView(allOf(withId(ID_VIEW_NEWS_ITEM_IMAGE_VIEW),
                withParent(withParent(allOf(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW),
                        withChild(withChild(withText(fakeTitle))))))));
    }

    private static ViewInteraction createDate(String fakeTitle) {
        return onView(allOf(withId(ID_NEWS_ITEM_CREATE_DATA_TEXT_VIEW),
                withParent(withParent(allOf(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW),
                        withChild(withChild(withText(fakeTitle))))))));
    }

    private static ViewInteraction publicationDate(String fakeTitle) {
        return onView(allOf(withId(ID_NEWS_ITEM_PUBLICATION_DATA_TEXT_VIEW),
                withParent(withParent(allOf(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW),
                        withChild(withChild(withText(fakeTitle))))))));
    }


    public static void checkCreateDate(String fakeTitle, String date) {
        Allure.step("Проверить дату создания");
        createDate(fakeTitle).check(matches(withText(date)));
    }

    public static void checkPublicationDate(String fakeTitle, String date) {
        Allure.step("Проверить дату публикации");
        publicationDate(fakeTitle).check(matches(withText(date)));
    }


    public static void clickButtonAddNews() {
        Allure.step("Нажать кнопку добавить новость");
        buttonAddNews.check(matches(isDisplayed())).perform(click());
    }

    public static void checkTextTitle(String title) {
        Allure.step("Проверить отображение заголовка");
        textTitle(title).check(matches(withText(title)));
    }

    public static void controlPanelVisible() {
        Allure.step("Проверить видимость панели Новости");
        buttonAddNews.check(matches(isDisplayed()));
    }

    public static void clickButtonFilter() {
        Allure.step("Нажать на Фильтр");
        buttonFilterOnNewsControlPanel.check(matches(isDisplayed())).perform(click());

    }

    public static void scrollNewsPage(String fakeTitle) {
        Allure.step("Скролить Панель управления новостями до заданого текста");
        recyclerView.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(fakeTitle))))
                .check(matches(isDisplayed()));
    }

    public static void clickButtonEdit(String fakeTitle) {
        Allure.step("Нажать изменить Новость");
        buttonEditNews(fakeTitle).check(matches(isDisplayed())).perform(click());
    }

    public static void checkStatus(String fakeTitle) {
        Allure.step("Проверить статус");
        status(fakeTitle).check(matches(isDisplayed()));
    }

    public static void clickDelete(String fakeTitle) {
        Allure.step("Нажать удалить");
        delete(fakeTitle).check(matches(isDisplayed())).perform(click());
    }

    public static void clickOk() {
        Allure.step("Нажать ОК");
        buttonOk.check(matches(isDisplayed())).perform(click());
    }

    public static void clickButtonToExpandNewsCard(String fakeTitle) {
        Allure.step("Нажать раскрыть");
        buttonToExpandNewsCard(fakeTitle).check(matches(isDisplayed())).perform(click());
    }


    public static void checkNewsDeleted(String text) {
        Allure.step("Проверка что новость удалена");
        onView(withText(text)).check(doesNotExist());
    }

}

