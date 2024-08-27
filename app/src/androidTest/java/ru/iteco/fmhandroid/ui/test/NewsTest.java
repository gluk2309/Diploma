package ru.iteco.fmhandroid.ui.test;


import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_ERROR_EMPTY_FIELDS;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_MESSAGE_CHANGES_NOT_BE_SAVED;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_MESSAGE_DELETE;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.help.Utils;
import ru.iteco.fmhandroid.ui.page.ControlPanelNewsPage;
import ru.iteco.fmhandroid.ui.page.CreateEditNewsPanelPage;
import ru.iteco.fmhandroid.ui.page.FilterPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.SidebarPage;
import ru.iteco.fmhandroid.ui.res.FakerDataGenerator;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

    SidebarPage sidebarPage = new SidebarPage();
    NewsPage newsPage = new NewsPage();
    ControlPanelNewsPage controlPanelNewsPage = new ControlPanelNewsPage();
    CreateEditNewsPanelPage createEditNewsPanelPage = new CreateEditNewsPanelPage();
    FilterPage filterPage = new FilterPage();
    Utils utils = new Utils();
    FakerDataGenerator fakerDataGenerator = new FakerDataGenerator();
    MainPage mainPage = new MainPage();

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
        fakeTitle = fakerDataGenerator.generateFakeTitle();
        fakeDescription = fakerDataGenerator.generateFakeDescription();
        editTitle = fakerDataGenerator.generateFakeEditTitle();

        try {
            mainPage.authorizationImageButtonVisible();
        } catch (Exception e) {
            utils.autoAuthorization();
        }

        sidebarPage.clickButtonMainMenu();
        sidebarPage.clickMenuNews();
        newsPage.clickShortcutControlPanel();
        controlPanelNewsPage.clickButtonAddNews();
    }


    @Test
    @Story("N001")
    @DisplayName("Создание новости")
    public void createNewsTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeTitle);
        controlPanelNewsPage.checkTextTitle(fakeTitle);
    }


    @Test
    @Story("N002")
    @DisplayName("Создание Новости с пустыми полями")
    public void createNewsWithEmptyFieldsTest() {
        createEditNewsPanelPage.clickButtonSave();
        utils.waitForErrorText(TEXT_ERROR_EMPTY_FIELDS);
    }

    @Test
    @Story("N003")
    @DisplayName("Отмена создания новости")
    public void cancelNewsCreationTest() {
        createEditNewsPanelPage.clickButtonCancel();
        createEditNewsPanelPage.checkTextTitle(TEXT_MESSAGE_CHANGES_NOT_BE_SAVED);
        createEditNewsPanelPage.clickOk();
        controlPanelNewsPage.controlPanelVisible();
    }

    @Test
    @Story("N004")
    @DisplayName("N004 Отмена редактирования новости")
    public void cancelEditingNewsTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeTitle);
        controlPanelNewsPage.clickButtonEdit(fakeTitle);
        createEditNewsPanelPage.clickButtonCancel();
        createEditNewsPanelPage.checkTextTitle(TEXT_MESSAGE_CHANGES_NOT_BE_SAVED);
        createEditNewsPanelPage.clickOk();
        controlPanelNewsPage.controlPanelVisible();
    }


    @Test
    @Story("N005")
    @DisplayName("Новость появляется на главной странице")
    public void newsAppearsOnMainPageTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        sidebarPage.clickButtonMainMenu();
        sidebarPage.clickMenuMain();
        utils.checkText(fakeTitle);

    }


    @Test
    @Story("N006")
    @DisplayName("Редактирование новости")
    public void newsEditingTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeTitle);
        controlPanelNewsPage.clickButtonEdit(fakeTitle);
        createEditNewsPanelPage.selectHeading(editTitle);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(editTitle);
        utils.checkText(editTitle);

    }

    @Test
    @Story("N007")
    @DisplayName("Деактивация новости")
    public void newsDeactivationTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeTitle);
        controlPanelNewsPage.clickButtonEdit(fakeTitle);
        createEditNewsPanelPage.clickSwitcher();
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeTitle);
        controlPanelNewsPage.checkStatus(fakeTitle);
    }

    @Test
    @Story("N008")
    @DisplayName("Удаление новости")
    public void newsDeleteTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeTitle);
        controlPanelNewsPage.clickDelete(fakeTitle);
        utils.checkText(TEXT_MESSAGE_DELETE);
        controlPanelNewsPage.clickOk();
        controlPanelNewsPage.checkNewsDeleted(fakeTitle);
    }

    @Test
    @Story("N013")
    @DisplayName("Раскрыть описание новости в панели управления новостями")
    public void openCloseNewsDescriptionInTheNewsControlPanelTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeDescription);
        controlPanelNewsPage.clickButtonToExpandNewsCard(fakeDescription);
        controlPanelNewsPage.scrollNewsPage(fakeDescription);
        utils.checkText(fakeDescription);
    }

    @Test
    @Story("N014")
    @DisplayName("Раскрыть Описание новости, на странице Новости")
    public void openCloseDescriptionOfNewsOnNewsPageTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        sidebarPage.clickButtonMainMenu();
        sidebarPage.clickMenuNews();
        newsPage.scrollNewsPage(fakeTitle);
        newsPage.clickButtonToExpandNewsCard(fakeTitle);
        utils.checkText(fakeDescription);
    }


    @Test
    @Story("L005")
    @DisplayName("Корректная работа фильтра на панели управления новостям")
    public void correctOperationFilterNewsControlPanelTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.clickButtonFilter();
        filterPage.selectCategory();
        filterPage.clickButtonFilterShowResult();
        controlPanelNewsPage.scrollNewsPage(fakeDescription);
        controlPanelNewsPage.checkTextTitle(fakeTitle);
    }

    @Test
    @Story("L006")
    @DisplayName("Корректность работы фильтра в блоке новости")
    public void correctOperationFilterInNewsPageTest() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        sidebarPage.clickButtonMainMenu();
        sidebarPage.clickMenuNews();
        newsPage.clickButtonFilter();
        filterPage.selectCategory();
        filterPage.clickButtonFilterShowResult();
        newsPage.checkTextTitle(fakeTitle);
    }

    @Test
    @Story("DN003")
    @DisplayName("Корректное отображение даты созданиия и публикации")
    public void CorrectDisplayOfCreationAndPublicationDate() {
        createEditNewsPanelPage.selectCategories();
        createEditNewsPanelPage.selectHeading(fakeTitle);
        createEditNewsPanelPage.selectPublicationDate();
        createEditNewsPanelPage.clickOk();
        date = createEditNewsPanelPage.getPublicationDate();
        createEditNewsPanelPage.selectPublicationTime();
        createEditNewsPanelPage.clickOk();
        createEditNewsPanelPage.selectDescription(fakeDescription);
        createEditNewsPanelPage.clickButtonSave();
        controlPanelNewsPage.scrollNewsPage(fakeTitle);
        controlPanelNewsPage.checkTextTitle(fakeTitle);
        controlPanelNewsPage.checkPublicationDate(fakeTitle, date);
        controlPanelNewsPage.checkCreateDate(fakeTitle, date);
    }
}



