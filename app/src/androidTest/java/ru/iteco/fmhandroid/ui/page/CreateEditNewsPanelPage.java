package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.help.Utils;

public class CreateEditNewsPanelPage {

    private static final int ID_NEWS_ITEM_CATEGORY_TEXT_AUTO_COMPLETE_TEXT_VIEW = R.id.news_item_category_text_auto_complete_text_view;
    private static final int ID_NEWS_ITEM_TITLE_TEXT_INPUT_EDIT_TEXT = R.id.news_item_title_text_input_edit_text;
    private static final int ID_NEWS_ITEM_PUBLISH_DATE_TEXT_INPUT_EDIT_TEXT = R.id.news_item_publish_date_text_input_edit_text;
    private static final int ID_BUTTON1 = android.R.id.button1;
    private static final int ID_NEWS_ITEM_PUBLISH_TIME_TEXT_INPUT_EDIT_TEXT = R.id.news_item_publish_time_text_input_edit_text;
    private static final int ID_NEWS_ITEM_DESCRIPTION_TEXT_INPUT_EDIT_TEXT = R.id.news_item_description_text_input_edit_text;
    private static final int ID_SAVE_BUTTON = R.id.save_button;
    private static final int ID_CANCEL_BUTTON = R.id.cancel_button;
    private static final int ID_SWITCHER = R.id.switcher;
    private static final int ID_MESSAGE = android.R.id.message;

    private static final ViewInteraction categories = onView(withId(ID_NEWS_ITEM_CATEGORY_TEXT_AUTO_COMPLETE_TEXT_VIEW));
    private static final ViewInteraction heading = onView(withId(ID_NEWS_ITEM_TITLE_TEXT_INPUT_EDIT_TEXT));
    private static final ViewInteraction publicationDate = onView(withId(ID_NEWS_ITEM_PUBLISH_DATE_TEXT_INPUT_EDIT_TEXT));
    private static final ViewInteraction buttonOk = onView(withId(ID_BUTTON1));
    private static final ViewInteraction publicationTime = onView(withId(ID_NEWS_ITEM_PUBLISH_TIME_TEXT_INPUT_EDIT_TEXT));
    private static final ViewInteraction description = onView(withId(ID_NEWS_ITEM_DESCRIPTION_TEXT_INPUT_EDIT_TEXT));
    private static final ViewInteraction buttonSave = onView(withId(ID_SAVE_BUTTON));
    private static final ViewInteraction buttonCancel = onView(withId(ID_CANCEL_BUTTON));
    private static final ViewInteraction switcher = onView(withId(ID_SWITCHER));
    private static final ViewInteraction cancellationMessage = onView(withId(ID_MESSAGE));


    public static void checkTextTitle(String title) {
        Allure.step("Проверить текст уведомления");
        cancellationMessage.check(matches(withText(title)));
    }


    public static void selectCategories() {
        Allure.step("Выбрать категорию");
        categories.check(matches(isDisplayed())).perform(click());
    }

    public static void selectHeading(String fakeTitle) {
        Allure.step("Выбрать заголовок");
        heading.check(matches(isDisplayed()))
                .perform(click(), clearText(), replaceText(fakeTitle), closeSoftKeyboard());
    }

    public static void selectPublicationDate() {
        Allure.step("Нажать на дату");
        publicationDate.check(matches(isDisplayed())).perform(click());
    }

    public static void clickOk() {
        Allure.step("Нажать ОК");
        buttonOk.check(matches(isDisplayed())).perform(click());
    }

    public static void selectPublicationTime() {
        Allure.step("Нажать на время");
        publicationTime.check(matches(isDisplayed())).perform(click());
    }

    public static void selectDescription(String fakeDescription) {
        Allure.step("Ввести описание");
        description.check(matches(isDisplayed()))
                .perform(click(), clearText(), replaceText(fakeDescription), closeSoftKeyboard());
    }

    public static void clickButtonSave() {
        Allure.step("Нажать сохранить");
        buttonSave.check(matches(isDisplayed())).perform(click());
    }

    public static void clickButtonCancel() {
        Allure.step("Нажать отмена");
        buttonCancel.check(matches(isDisplayed())).perform(click());
    }


    public static void clickSwitcher() {
        Allure.step("Нажать кнопку Активна/Не активна");
        switcher.check(matches(isDisplayed())).perform(click());
    }


    public static String getPublicationDate() {
        Allure.step("Получить дату публикации");
        return Utils.getText(publicationDate);
    }

}
