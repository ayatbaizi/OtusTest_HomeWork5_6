package org.otus.data.ProfileData;

public enum LanguageLevelData {
   BEGINNER("Начальный уровень (Beginner)"),
   ELEMENTARY("Элементарный уровень (Elementary)");


   private String name;

   LanguageLevelData(String name){
      this.name = name;
   }

   public String getName(){
      return this.name;
   }
}
