package org.otus;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.otus.components.LoginPageComponent;
import org.otus.components.MainPageComponent;
import org.otus.components.UserProfileInfoComponents;
import org.otus.data.ProfileData.ContactsData;
import org.otus.exceptions.BrowserNotSupportedExeception;
import org.otus.driver.WebDriverFactory;
import org.otus.pages.MainPage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class OtusTest {

   private WebDriver driver;


   @BeforeAll
   public static void initDriver() throws BrowserNotSupportedExeception {
      new WebDriverFactory().initDriver();

   }

   @BeforeEach
   public void initDriverOptions() throws BrowserNotSupportedExeception {
      List<String> options = new ArrayList<>();
      driver = new WebDriverFactory().create(WebDriverFactory.setDriverType(), options);
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
   }

   @AfterEach
   public void close() {
      if (driver != null) {
         driver.close();
         driver.quit();
      }
   }



   @Test
   public void testMainMenu() throws BrowserNotSupportedExeception {
      LoginPageComponent loginPage = new LoginPageComponent(driver);
      MainPageComponent mainPageComponent = new MainPageComponent(driver);
      LoginPageComponent loginPageComponent = new LoginPageComponent(driver);
      UserProfileInfoComponents userProfileInfoComponents = new UserProfileInfoComponents(driver);
      MainPage mainPage = new MainPage(driver);
      //Открыть https://otus.ru
      mainPage.open("/");


      //Авторизоваться на сайте
      mainPageComponent.clickButtonLogin();

      loginPage.waitVisible();
      loginPage.authorizationUser();
      loginPage.waitClickInvisible();


      //Войти в личный кабинет
      mainPageComponent.moveCoursorOnUserIcon();
      mainPageComponent.clickUserProfile();

      //В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
      userProfileInfoComponents.fillFieldsInfo();

      userProfileInfoComponents.deleteCommunicationMethodsIfExist();
      userProfileInfoComponents.clickButtonAddNewContact();


      userProfileInfoComponents.clickContactDropdownList();
      userProfileInfoComponents.selectContact(ContactsData.VK);


      userProfileInfoComponents.clickButtonAddNewContact();
      userProfileInfoComponents.clickContactDropdownList();
      userProfileInfoComponents.selectContact(ContactsData.TELEGRAM);
      userProfileInfoComponents.fillContactInfo();
      //userProfileInfoComponents.fillContactInfo2();



      //Нажать сохранить
      userProfileInfoComponents.clickSaveAndContinueButton();

      //Открыть https://otus.ru в "чистом браузере"


      //close();
      driver.close();
      driver.quit();
      initDriverOptions();

      LoginPageComponent loginPage2 = new LoginPageComponent(driver);
      MainPageComponent mainPageComponent2 = new MainPageComponent(driver);
      LoginPageComponent loginPageComponent2 = new LoginPageComponent(driver);
      UserProfileInfoComponents userProfileInfoComponents2 = new UserProfileInfoComponents(driver);
      MainPage mainPage2 = new MainPage(driver);


      //Авторизоваться на сайте
      mainPage2.open("/");
      mainPageComponent2.clickButtonLogin();

      loginPageComponent2.waitVisible();
      loginPageComponent2.authorizationUser();
      loginPageComponent2.waitClickInvisible();


      //Войти в личный кабинет
      mainPageComponent2.moveCoursorOnUserIcon();
      mainPageComponent2.clickUserProfile();

      //      Проверить, что в разделе "О себе" отображаются указанные ранее данные
      userProfileInfoComponents2.checkInfoData();
   }

}



