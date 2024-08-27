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

public class NewsPage {

    private final int ID_EDIT_NEWS_MATERIAL_BUTTON = R.id.edit_news_material_button;
    private final int ID_FILTER_NEWS_MATERIAL_BUTTON = R.id.filter_news_material_button;
    private final int ID_NEWS_LIST_RECYCLER_VIEW = R.id.news_list_recycler_view;
    private final int ID_NEWS_ITEM_TITLE_TEXT_VIEW = R.id.news_item_title_text_view;
    private final int ID_NEWS_ITEM_MATERIAL_CARD_VIEW = R.id.news_item_material_card_view;
    private final int ID_VIEW_NEWS_ITEM_IMAGE_VIEW = R.id.view_news_item_image_view;

    private final ViewInteraction buttonControlPanel = onView(withId(ID_EDIT_NEWS_MATERIAL_BUTTON));
    private final ViewInteraction buttonFilterOnNewsPage = onView(withId(ID_FILTER_NEWS_MATERIAL_BUTTON));
    private final ViewInteraction recyclerView = onView(withId(ID_NEWS_LIST_RECYCLER_VIEW));


    private ViewInteraction textTitle(String title) {
        return onView(allOf(withId(ID_NEWS_ITEM_TITLE_TEXT_VIEW),
                withText(title)));
    }

    private ViewInteraction buttonToExpandNewsCard(String fakeTitle) {
        return onView(allOf(withId(ID_VIEW_NEWS_ITEM_IMAGE_VIEW),
                withParent(withParent(allOf(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW),
                        withChild(withChild(withText(fakeTitle))))))));
    }


    public void clickShortcutControlPanel() {
        Allure.step("Нажать кнопку панели управления новостями");
        buttonControlPanel.check(matches(isDisplayed())).perform(click());
    }

    public void newsPageVisible() {
        Allure.step("Кнопка панели управления новостями отображается");
        buttonControlPanel.check(matches(isDisplayed()));
    }

    public void clickButtonFilter() {
        Allure.step("Нажать фильтр");
        buttonFilterOnNewsPage.check(matches(isDisplayed())).perform(click());

    }

    public void checkTextTitle(String title) {
        Allure.step("Проверить текст заголовка");
        textTitle(title).check(matches(withText(title)));
    }

    public void scrollNewsPage(String fakeTitle) {
        Allure.step("Скролить новости до задоного текста");
        recyclerView.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(fakeTitle))))
                .check(matches(isDisplayed()));
    }

    public void clickButtonToExpandNewsCard(String fakeTitle) {
        Allure.step("Нажать кнопку Скрыть/Раскрыть новость");
        buttonToExpandNewsCard(fakeTitle).check(matches(isDisplayed())).perform(click());
    }

}
