package org.otus.data.ProfileData;

public enum ContactsFieldData {
   CONTACT1("22334455"),
   CONTACT2("66778899");


   private String name;

   ContactsFieldData(String name){
      this.name = name;
   }

   public String getName(){
      return this.name;
   }
}
