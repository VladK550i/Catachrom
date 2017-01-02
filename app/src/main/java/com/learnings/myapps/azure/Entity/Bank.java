package com.learnings.myapps.azure.Entity;


public class Bank {
    public String getBankName() {
        return BankName;
    }
    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBankRecId() {
        return BankRecId;
    }
    public void setBankRecId(String bankRecId) {
        BankRecId = bankRecId;
    }

    public String id;
    private String BankRecId;
    private String BankName;
}
