package org.otus;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.otus.components.LoginPageComponent;
import org.otus.components.MainPageComponent;
import org.otus.components.UserProfileInfoComponents;
import org.otus.data.ProfileData.ContactsData;
import org.otus.exceptions.BrowserNotSupportedExeception;
import org.otus.driver.WebDriverFactory;
import org.otus.pages.LoginPage;
import org.otus.pages.MainPage;

import java.util.ArrayList;
import java.util.List;

//import static org.otus.driver.WebDriverFactory.setDriverName;

public class OtusTest {

   private WebDriver driver;


   @BeforeAll
   public static void init() throws BrowserNotSupportedExeception {
      new WebDriverFactory().init();

   }

   @BeforeEach
   public void initDriver() throws BrowserNotSupportedExeception {
      List<String> options = new ArrayList<>();
      options.add("--window-size=1920,1080");
      driver = new WebDriverFactory().create(WebDriverFactory.setDriverName(), options);
      //log.info("Test started in {} browser", WebDriverFactory.setDriverName());
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
      LoginPage loginPage = new LoginPage(driver);
      MainPageComponent mainPageComponent = new MainPageComponent(driver);
      LoginPageComponent loginPageComponent = new LoginPageComponent(driver);
      UserProfileInfoComponents userProfileInfoComponents = new UserProfileInfoComponents(driver);

      //Открыть https://otus.ru
      new MainPage(driver)
              .open("/");


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



      userProfileInfoComponents.clickContactDropdownList();
      userProfileInfoComponents.selectContact(ContactsData.VK);
      userProfileInfoComponents.fillContactInfo();

      userProfileInfoComponents.clickButtonAddNewContact();
      userProfileInfoComponents.clickContactDropdownList();
      userProfileInfoComponents.selectContact(ContactsData.TELEGRAM);
      userProfileInfoComponents.fillContactInfo2();



      //Нажать сохранить
      userProfileInfoComponents.clickSaveAndContinueButton();

      //Открыть https://otus.ru в "чистом браузере"


      close();
      initDriver();
      new MainPage(driver).open("/");


      //Авторизоваться на сайте
      LoginPage loginPage2 = new LoginPage(driver);
      MainPageComponent mainPageComponent2 = new MainPageComponent(driver);
      LoginPageComponent loginPageComponent2 = new LoginPageComponent(driver);
      UserProfileInfoComponents userProfileInfoComponents2 = new UserProfileInfoComponents(driver);

      mainPageComponent2.clickButtonLogin();

      loginPage2.waitVisible();
      loginPage2.authorizationUser();
      loginPage2.waitClickInvisible();


      //Войти в личный кабинет
      mainPageComponent2.moveCoursorOnUserIcon();
      mainPageComponent2.clickUserProfile();

      //      Проверить, что в разделе "О себе" отображаются указанные ранее данные
      userProfileInfoComponents2.checkInfoData();
   }

}



