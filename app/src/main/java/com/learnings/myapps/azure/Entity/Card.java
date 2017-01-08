package com.learnings.myapps.azure.entity;


public class Card {
    public String getCardId() {
        return CardId;
    }
    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getBankRefRecID() {
        return BankRefRecID;
    }
    public void setBankRefRecID(String bankRefRecID) {
        BankRefRecID = bankRefRecID;
    }

    public String id;
    private String CardId;
    private String BankRefRecID;
}
