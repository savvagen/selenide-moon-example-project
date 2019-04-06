package com.example.models;

public class Locales {

    public enum Locale {
        UNITED_STATES("en-US"),
        ENGLAND("en"),
        BULGARIA("bg"),
        RUSSIA("ru"),
        UKRAINE("uk");

        public String locale;

        Locale(String locale){
            this.locale = locale;
        }
    }


}
