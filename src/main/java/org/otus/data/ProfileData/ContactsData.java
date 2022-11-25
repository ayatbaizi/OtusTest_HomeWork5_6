package org.otus.data.ProfileData;

public enum ContactsData {
   VK("vk"),
   TELEGRAM("telegram"),
   VIBER("viber"),
   WHATSAPP("whatsapp");

   private String name;

   ContactsData(String name){
      this.name = name;
   }

   public String getName(){
      return this.name;
   }
}
