package org.otus.exceptions;


import org.otus.data.DriverData;

public class BrowserNotSupportedExeception extends Exception{

   public BrowserNotSupportedExeception(DriverData browserType){
      super(String.format("Browser %s not supported", browserType));
   }
}
