package org.otus.components;

import org.openqa.selenium.WebDriver;
import org.otus.pageobject.AbsPageObject;


public abstract class AbsBaseComponent extends AbsPageObject {

   public AbsBaseComponent(WebDriver driver) {
      super(driver);
   }


}
