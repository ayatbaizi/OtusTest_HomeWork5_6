package org.otus.components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.otus.data.ProfileData.*;


import java.util.List;

public class UserProfileInfoComponents extends AbsBaseComponent{
   public UserProfileInfoComponents(WebDriver driver) {
      super(driver);
   }

   @FindBy(id = "id_fname")
   private WebElement firstNameField;

   @FindBy(id = "id_fname_latin")
   private WebElement firstNameLatinField;

   @FindBy(id = "id_lname")
   private WebElement secondNameField;

   @FindBy(id = "id_lname_latin")
   private WebElement secondNameLatinField;

   @FindBy(id = "id_blog_name")
   private WebElement blogNameField;

   @FindBy(css = "input[name = date_of_birth]")
   private WebElement birthDateField;

   @FindBy(xpath = "//*[contains(@class, 'container__col_12')]/input[contains(@name,'contact-0-value')]")
   private WebElement fieldContact1;

   @FindBy(xpath = "//*[contains(@class, 'container__col_12')]/input[contains(@name,'contact-1-value')]")
   private WebElement fieldContact2;

   @FindBy(xpath = "//span[contains(text(),'Способ связи')]")
   private WebElement selectDropdownListContact;

   @FindBy(css = "button[title = 'Сохранить и продолжить']")
   private WebElement saveAndContinueButton;

   @FindBy(className = "js-lk-cv-custom-select-add")
   private WebElement buttonAddNewContact;
   @FindBy(xpath = "//div[contains(@data-prefix, 'contact')]//*[not(contains(@class, '_ssm'))]/*/button[text() = 'Удалить']")
   private List<WebElement> deleteButtonsList;


   @FindBy(css = "[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']")
   private WebElement languageLevelDropdown;

   public void waitVisible(){

   }

   private String dropdownContacts = ("(//button[@data-value='%s'])[last()]");

   private String setLanguageLevel = ("//*[contains(text(), '%s')]");

   private void inputUserInfo(WebElement webElement, UserInfoData userInfoData){
      webElement.clear();
      webElement.sendKeys(userInfoData.getName());
   }

   public void inputContactInfo(WebElement webElement, ContactsFieldData contactsFieldData){
      webElement.clear();
      webElement.sendKeys(contactsFieldData.getName());
   }
   public void fillFieldsInfo(){
      inputUserInfo(firstNameField, UserInfoData.FIRST_NAME);
      inputUserInfo(firstNameLatinField, UserInfoData.FIRST_NAME_LATIN);
      inputUserInfo(secondNameField, UserInfoData.SECOND_NAME);
      inputUserInfo(secondNameLatinField, UserInfoData.SECOND_NAME_LATIN);
      inputUserInfo(blogNameField, UserInfoData.BLOG_NAME);
      inputUserInfo(birthDateField, UserInfoData.BIRTH_DATE);
   }

   public void fillContactInfo(){
      inputContactInfo(fieldContact1, ContactsFieldData.CONTACT1);
     // inputContactInfo(fieldContact2, ContactsFieldData.CONTACT2);
   }
   public void fillContactInfo2(){
      inputContactInfo(fieldContact2, ContactsFieldData.CONTACT2);
      // inputContactInfo(fieldC
      }

   public void clickContactDropdownList(){
      //actions.moveToElement(selectDropdownListContact).build().perform();
      selectDropdownListContact.click();
      //actions.click();
   }

   public void clickSaveAndContinueButton(){
      saveAndContinueButton.click();
   }

   public void clickButtonAddNewContact(){
      buttonAddNewContact.click();
   }

   public void deleteCommunicationMethodsIfExist() {
      if (deleteButtonsList.size() > 0) {
         String personalURL = driver.getCurrentUrl();
         for (WebElement deleteButton : deleteButtonsList) {
            deleteButton.click();
         }
         clickSaveAndContinueButton();
         driver.get(personalURL);


      }
   }


   public UserProfileInfoComponents selectContact(ContactsData contactsData){

      String locator = String.format(dropdownContacts, contactsData.getName());
      WebElement dropdownList = driver.findElement(By.xpath(locator));
      WebDriverWait wait = new WebDriverWait(driver, 5);
      Assertions.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(dropdownContacts))));
      dropdownList.click();

      return this;
   }


   private UserProfileInfoComponents selectLanguageLevel(LanguageLevelData languageLevelData){

      String locator = String.format(setLanguageLevel, languageLevelData.getName());
      WebElement setLanguageLevelElement = driver.findElement(By.xpath(locator));

      setLanguageLevelElement.click();

      return this;
   }

   public void clickLanguageLevelDropdown(){

         selectLanguageLevel(LanguageLevelData.ELEMENTARY);
      }

   public void checkInfoData() {
      Assertions.assertEquals(UserInfoData.FIRST_NAME.getName(), firstNameField.getAttribute("value"));
      Assertions.assertEquals(UserInfoData.FIRST_NAME_LATIN.getName(), firstNameLatinField.getAttribute("value"));
      Assertions.assertEquals(UserInfoData.SECOND_NAME.getName(), secondNameField.getAttribute("value"));
      Assertions.assertEquals(UserInfoData.SECOND_NAME_LATIN.getName(), secondNameLatinField.getAttribute("value"));
      Assertions.assertEquals(UserInfoData.BLOG_NAME.getName(), blogNameField.getAttribute("value"));
      Assertions.assertEquals(UserInfoData.BIRTH_DATE.getName(), birthDateField.getAttribute("value"));
   }

}
