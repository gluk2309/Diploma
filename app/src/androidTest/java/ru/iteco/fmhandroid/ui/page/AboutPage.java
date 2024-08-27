package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.res.Constants.VERSION;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {

    private final int ID_ABOUT_VERSION_TITLE_TEXT_VIEW = R.id.about_version_title_text_view;

    private final ViewInteraction textVersion = onView(allOf(withId(ID_ABOUT_VERSION_TITLE_TEXT_VIEW), withText(VERSION)));


    public void checkTextVersion() {
        Allure.step("Проверить наличие текста 'Версия'");
        textVersion.check(matches(isDisplayed()));
    }
}
