package org.otus.exceptions;



public class BrowserNotSupportedExeception extends Exception{

   public BrowserNotSupportedExeception(String browserType){
      super(String.format("Browser %s not supported", browserType));
   }
}
