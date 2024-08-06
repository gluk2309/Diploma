package ru.iteco.fmhandroid.ui.help;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.START_TIME;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.TreeIterables;

import junit.framework.AssertionFailedError;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;


public class Utils {

    private static ViewInteraction text(String text) {
        return onView(withText(text));
    }


    public static void autoAuthorization() {

        Allure.step("АвтоАвторизация");
        AuthorizationPage.verifyRegistrationPage();
        AuthorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        MainPage.authorizationImageButtonVisible();

    }


    public static void logOut() {

        Allure.step("Нажать выход");
        try {
            MainPage.clickAuthorizationImageButton();
            MainPage.clickLogOutButton();
        } catch (AssertionFailedError e) {
            MainPage.clickBackAboutPage();
            MainPage.clickAuthorizationImageButton();
            MainPage.clickLogOutButton();
        }
    }

    public static void checkText(String text) {
        Allure.step("Проверить ожидаемый текст");
        text(text).check(matches(isDisplayed()));
    }


    public static void waitForErrorText(String text) {

        Allure.step("Проверить текст ошибки");

        final int timeout = START_TIME; // таймаут в секунд
        final int interval = 100; // интервал в 100 мс

        long startTime = System.currentTimeMillis();
        boolean isElementFound = false;

        while (!isElementFound && (System.currentTimeMillis() - startTime < timeout)) {
            try {
                onView(ViewMatchers.withText(text))
                        .inRoot(RootMatchers.withDecorView(Matchers.instanceOf(View.class)))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
                isElementFound = true;
            } catch (Exception e) {
                // Игнорируем ошибку, если элемент не найден
            }
            SystemClock.sleep(interval);
        }

        if (!isElementFound) {
            throw new RuntimeException("Элемент не найден за отведенное время");
        }
    }


    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }


    public static void waitFor(int viewId) {
        onView(isRoot()).perform(waitDisplayed(viewId, START_TIME));
    }


    public static ViewAction waitDisplayed(final int viewId, int time) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <"
                        + viewId + "> has been displayed during "
                        + time + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + time;
                final Matcher<View> matchId = withId(viewId);
                final Matcher<View> matchDisplayed = isDisplayed();

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (matchId.matches(child) && matchDisplayed.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);
            }
        };
    }

    public static String getText(ViewInteraction matcher) {
        final StringBuilder text = new StringBuilder();
        matcher.perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "Text of the view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                text.append(tv.getText().toString());
            }
        });

        return text.toString();
    }

}