package org.otus.components;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPageComponent extends AbsBaseComponent {

   public MainPageComponent(WebDriver driver) {
      super(driver);
   }

   @FindBy(css = ".header2__auth")
   private WebElement loginButton;

   @FindBy(css = ".ic-blog-default-avatar")
   private WebElement userIcon;

   @FindBy(className = "header2-menu__dropdown-text")
   private WebElement userProfileButton;

   @FindBy(xpath = "//div[contains(@class, 'header2__right__menu__item')])[1]")
   private WebElement buttonProfile;


   public void moveCoursorOnUserIcon() {
      actions.moveToElement(userIcon).build().perform();
   }

   public void clickUserProfile(){
      userProfileButton.click();
   }
   public MainPageComponent clickButtonLogin(){
      loginButton.click();
      return new MainPageComponent(driver);
   }




}

