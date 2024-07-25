package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppActivityTest2 {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void appActivityTest() throws InterruptedException {

            // Проверка существования идентификаторов
            onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
            onView(withId(R.id.password_text_input_layout)).check(matches(isDisplayed()));

            // Ввод текста
            onView(withId(R.id.login_text_input_layout)).perform(replaceText("login2"), closeSoftKeyboard());
            onView(withId(R.id.password_text_input_layout)).perform(replaceText("password2"), closeSoftKeyboard());

            // Клик на кнопку
            onView(withId(R.id.enter_button)).perform(click());

            Thread.sleep(10000);
            // Проверка отображения элемента
            onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()));

    }
}