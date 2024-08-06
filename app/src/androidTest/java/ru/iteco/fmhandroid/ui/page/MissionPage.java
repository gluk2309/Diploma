package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_MISSION;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.help.Utils;

public class MissionPage {

    private static final int ID_OUR_MISSION_ITEM_OPEN_CARD_IMAGE_BUTTON = R.id.our_mission_item_open_card_image_button;

    private static final ViewInteraction buttonMissionItem = onView(Utils.withIndex(withId(ID_OUR_MISSION_ITEM_OPEN_CARD_IMAGE_BUTTON), 0));
    private static final ViewInteraction itemMission = onView(withText(containsString(TEXT_MISSION)));


    public static void clickButtonMissionItem() {
        Allure.step("Нажать кнопку раскрыть миссию");
        buttonMissionItem.perform(click());
    }

    public static void checkItemMission() {
        Allure.step("Проверить текст миссии");
        itemMission.check(matches(isDisplayed()));

    }
}
