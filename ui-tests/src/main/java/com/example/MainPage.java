package com.example;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
}



class LoginModal extends ElementsContainer {




}


class ElementsList {



    //List<T extends ElementsContainer> T elems  =

    List<SelenideElement> selenideElements = new ArrayList<>();


    void tes(){
        selenideElements.addAll(Selenide.$$(".test"));
    }

}