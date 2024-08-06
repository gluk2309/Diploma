package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class FilterPage {
    private static final int ID_NEWS_ITEM_CATEGORY_TEXT_AUTO_COMPLETE_TEXT_VIEW = R.id.news_item_category_text_auto_complete_text_view;
    private static final int ID_NEWS_ITEM_PUBLISH_DATE_START_TEXT_INPUT_LAYOUT = R.id.news_item_publish_date_start_text_input_layout;
    private static final int ID_FILTER_BUTTON = R.id.filter_button;

    private static final ViewInteraction categoryField = onView(withId(ID_NEWS_ITEM_CATEGORY_TEXT_AUTO_COMPLETE_TEXT_VIEW));
    private static final ViewInteraction startTimeField = onView(withId(ID_NEWS_ITEM_PUBLISH_DATE_START_TEXT_INPUT_LAYOUT));
    private static final ViewInteraction buttonFilterShowResult = onView(withId(ID_FILTER_BUTTON));


    public static void selectCategory() {
        Allure.step("Выбрать категорию");
        categoryField.check(matches(isDisplayed())).perform(click());
        startTimeField.perform(click());
    }

    public static void clickButtonFilterShowResult() {
        Allure.step("Нажать кнопку показать результат");
        buttonFilterShowResult.check(matches(isDisplayed())).perform(click());

    }


}
