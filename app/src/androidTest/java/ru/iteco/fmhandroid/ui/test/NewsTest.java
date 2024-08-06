package ru.iteco.fmhandroid.ui.test;


import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_ERROR_EMPTY_FIELDS;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_MESSAGE_CHANGES_NOT_BE_SAVED;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_MESSAGE_DELETE;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.help.Utils;
import ru.iteco.fmhandroid.ui.page.ControlPanelNewsPage;
import ru.iteco.fmhandroid.ui.page.CreateEditNewsPanelPage;
import ru.iteco.fmhandroid.ui.page.FilterPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.SidebarPage;
import ru.iteco.fmhandroid.ui.res.FakerDataGenerator;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {
    String fakeTitle;
    String fakeDescription;
    String editTitle;
    String date;
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);


    @Before
    @DisplayName("Настройка теста")
    @Description("Выполняется перед каждым тестом")
    public void setUp() {
        fakeTitle = FakerDataGenerator.generateFakeTitle();
        fakeDescription = FakerDataGenerator.generateFakeDescription();
        editTitle = FakerDataGenerator.generateFakeEditTitle();

        Utils.autoAuthorization();

        SidebarPage.clickButtonMainMenu();
        SidebarPage.clickMenuNews();
        NewsPage.clickShortcutControlPanel();
        ControlPanelNewsPage.clickButtonAddNews();
    }

    @After
    @DisplayName("Закрыть приложение")
    @Description("Выполняется после завершения теста")
    public void tearDown() {
        Allure.step("Выход после теста");
        Utils.logOut();
    }


    @Test
    @Story("N001")
    @DisplayName("Создание новости")
    public void createNewsTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeTitle);
        ControlPanelNewsPage.checkTextTitle(fakeTitle);
    }


    @Test
    @Story("N002")
    @DisplayName("Создание Новости с пустыми полями")
    public void createNewsWithEmptyFieldsTest() {
        CreateEditNewsPanelPage.clickButtonSave();
        Utils.waitForErrorText(TEXT_ERROR_EMPTY_FIELDS);

        io.qameta.allure.kotlin.Allure.step(
                "Дополнительных 2 шага," +
                        " необходимых для выхода из меню редактирования");
        CreateEditNewsPanelPage.clickButtonCancel();
        CreateEditNewsPanelPage.clickOk();
    }

    @Test
    @Story("N003")
    @DisplayName("Отмена создания новости")
    public void cancelNewsCreationTest() {
        CreateEditNewsPanelPage.clickButtonCancel();
        CreateEditNewsPanelPage.checkTextTitle(TEXT_MESSAGE_CHANGES_NOT_BE_SAVED);
        CreateEditNewsPanelPage.clickOk();
        ControlPanelNewsPage.controlPanelVisible();
    }

    @Test
    @Story("N004")
    @DisplayName("N004 Отмена редактирования новости")
    public void cancelEditingNewsTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeTitle);
        ControlPanelNewsPage.clickButtonEdit(fakeTitle);
        CreateEditNewsPanelPage.clickButtonCancel();
        CreateEditNewsPanelPage.checkTextTitle(TEXT_MESSAGE_CHANGES_NOT_BE_SAVED);
        CreateEditNewsPanelPage.clickOk();
        ControlPanelNewsPage.controlPanelVisible();
    }


    @Test
    @Story("N005")
    @DisplayName("Новость появляется на главной странице")
    public void newsAppearsOnMainPageTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        SidebarPage.clickButtonMainMenu();
        SidebarPage.clickMenuMain();
        Utils.checkText(fakeTitle);

    }


    @Test
    @Story("N006")
    @DisplayName("Редактирование новости")
    public void newsEditingTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeTitle);
        ControlPanelNewsPage.clickButtonEdit(fakeTitle);
        CreateEditNewsPanelPage.selectHeading(editTitle);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(editTitle);
        Utils.checkText(editTitle);

    }

    @Test
    @Story("N007")
    @DisplayName("Деактивация новости")
    public void newsDeactivationTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeTitle);
        ControlPanelNewsPage.clickButtonEdit(fakeTitle);
        CreateEditNewsPanelPage.clickSwitcher();
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeTitle);
        ControlPanelNewsPage.checkStatus(fakeTitle);
    }

    @Test
    @Story("N008")
    @DisplayName("Удаление новости")
    public void newsDeleteTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeTitle);
        ControlPanelNewsPage.clickDelete(fakeTitle);
        Utils.checkText(TEXT_MESSAGE_DELETE);
        ControlPanelNewsPage.clickOk();
        ControlPanelNewsPage.checkNewsDeleted(fakeTitle);
    }

    @Test
    @Story("N013")
    @DisplayName("Раскрыть описание новости в панели управления новостями")
    public void openCloseNewsDescriptionInTheNewsControlPanelTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeDescription);
        ControlPanelNewsPage.clickButtonToExpandNewsCard(fakeDescription);
        ControlPanelNewsPage.scrollNewsPage(fakeDescription);
        Utils.checkText(fakeDescription);
    }

    @Test
    @Story("N014")
    @DisplayName("Раскрыть Описание новости, на странице Новости")
    public void openCloseDescriptionOfNewsOnNewsPageTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        SidebarPage.clickButtonMainMenu();
        SidebarPage.clickMenuNews();
        NewsPage.scrollNewsPage(fakeTitle);
        NewsPage.clickButtonToExpandNewsCard(fakeTitle);
        Utils.checkText(fakeDescription);
    }


    @Test
    @Story("L005")
    @DisplayName("Корректная работа фильтра на панели управления новостям")
    public void correctOperationFilterNewsControlPanelTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.clickButtonFilter();
        FilterPage.selectCategory();
        FilterPage.clickButtonFilterShowResult();
        ControlPanelNewsPage.scrollNewsPage(fakeDescription);
        ControlPanelNewsPage.checkTextTitle(fakeTitle);
    }

    @Test
    @Story("L006")
    @DisplayName("Корректность работы фильтра в блоке новости")
    public void correctOperationFilterInNewsPageTest() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        SidebarPage.clickButtonMainMenu();
        SidebarPage.clickMenuNews();
        NewsPage.clickButtonFilter();
        FilterPage.selectCategory();
        FilterPage.clickButtonFilterShowResult();
        NewsPage.checkTextTitle(fakeTitle);
    }

    @Test
    @Story("DN003")
    @DisplayName("Корректное отображение даты созданиия и публикации")
    public void CorrectDisplayOfCreationAndPublicationDate() {
        CreateEditNewsPanelPage.selectCategories();
        CreateEditNewsPanelPage.selectHeading(fakeTitle);
        CreateEditNewsPanelPage.selectPublicationDate();
        CreateEditNewsPanelPage.clickOk();
        date = CreateEditNewsPanelPage.getPublicationDate();
        CreateEditNewsPanelPage.selectPublicationTime();
        CreateEditNewsPanelPage.clickOk();
        CreateEditNewsPanelPage.selectDescription(fakeDescription);
        CreateEditNewsPanelPage.clickButtonSave();
        ControlPanelNewsPage.scrollNewsPage(fakeTitle);
        ControlPanelNewsPage.checkTextTitle(fakeTitle);
        ControlPanelNewsPage.checkPublicationDate(fakeTitle, date);
        ControlPanelNewsPage.checkCreateDate(fakeTitle, date);
    }
}



