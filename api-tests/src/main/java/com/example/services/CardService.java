package com.example.services;

import com.example.WebService;
import com.example.assertions.AssertableResponse;
import com.example.models.Card;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CardService extends WebService {

    public CardService(String basePath) {
        super(basePath);
    }

    public AssertableResponse getCards(){
        return new AssertableResponse(requestSpec.when().get("/").then());
    }

    public AssertableResponse getCard(String cardId){
        return new AssertableResponse(requestSpec.when().get("/" + cardId).then());
    }

    public AssertableResponse addCard(Card card){
        return new AssertableResponse(requestSpec.when().body(card).post("/").then());
    }

    public AssertableResponse deleteCard(String cardId){
        return new AssertableResponse(requestSpec.when().delete("/" + cardId).then());
    }


}
