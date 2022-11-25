package org.otus.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbsBasePage{
   private final static String LOGIN = System.getProperty("login");
   private final static String PASSWORD = System.getProperty("password");

   public LoginPage(WebDriver driver) {
      super(driver);
   }

   @FindBy(css = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)")
   private WebElement loginTextBox;

   @FindBy(css = ".js-psw-input")
   private WebElement passwordTextBox;

   @FindBy(css = "div.new-input-line_last:nth-child(5) > button:nth-child(1)")
   private WebElement loginButton;

   private String inputTextBox = "div.new-input-line_slim:nth-child(3) > input:nth-child(1)";

   private String loginModalWindow = "//div[contains(@class, 'modal-container')][1]";

   public void waitVisible(){
      WebDriverWait wait = new WebDriverWait(driver, 5);
      Assertions.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(inputTextBox))).isDisplayed()
              , "Login modal window doesn't displayed");
   }

   public void waitClickInvisible(){
      WebDriverWait wait = new WebDriverWait(driver, 5);
      Assertions.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loginModalWindow)))
              , "Login modal window still open");
   }

   public void authorizationUser(){
      loginTextBox.sendKeys(LOGIN);
      passwordTextBox.sendKeys(PASSWORD);
      loginButton.submit();
   }
}
