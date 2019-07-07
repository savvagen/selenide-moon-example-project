package com.example.pages;

import com.codeborne.selenide.Selenide;

public abstract class WebPage {

    public static String url;
    public static String title;

    public <T extends WebPage> T open(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        WebPage page = tClass.newInstance();
        Selenide.open(page.url);
        return tClass.newInstance();
    }


}
