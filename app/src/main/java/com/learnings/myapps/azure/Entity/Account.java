package com.learnings.myapps.azure.entity;

import java.util.Date;

public class Account {

    public Date getDateFrom() {
        return DateFrom;
    }
    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

//    public Date getDateTo() {
//        return DateTo;
//    }
//    public void setDateTo(Date dateTo) {
//        DateTo = dateTo;
//    }

    public String getBankRefRecID() {
        return BankRefRecID;
    }
    public void setBankRefRecID(String bankRefRecID) {
        BankRefRecID = bankRefRecID;
    }

    public float getStartFunds() {
        return StartFunds;
    }
    public void setStartFunds(float startFunds) {
        StartFunds = startFunds;
    }

    public float getAccumulatedFunds() {
        return AccumulatedFunds;
    }
    public void setAccumulatedFunds(float accumulatedFunds) {
        AccumulatedFunds = accumulatedFunds;
    }

    public float getOtherFunds() {
        return OtherFunds;
    }
    public void setOtherFunds(float otherFunds) {
        OtherFunds = otherFunds;
    }

//    public String getAccountNumber() {
//        return AccountNumber;
//    }
//    public void setAccountNumber(String accountNumber) {
//        AccountNumber = accountNumber;
//    }

    public String getBankOfferRefRecId() {
        return BankOfferRefRecId;
    }
    public void setBankOfferRefRecId(String bankOfferRefRecId) {
        BankOfferRefRecId = bankOfferRefRecId;
    }

    public String getLoginRefRecId() {
        return LoginRefRecId;
    }
    public void setLoginRefRecId(String loginRefRecId) {
        LoginRefRecId = loginRefRecId;
    }

//    public String getHolderName() {
//        return HolderName;
//    }
//    public void setHolderName(String holderName) {
//        HolderName = holderName;
//    }

    public String getNotice() {
        return Notice;
    }
    public void setNotice(String notice) {
        Notice = notice;
    }

    public float getIncomingTax() {
        return IncomingTax;
    }
    public void setIncomingTax(float incomingTax) {
        IncomingTax = incomingTax;
    }

    public int getDepositTermMonth() {
        return DepositTermMonth;
    }
    public void setDepositTermMonth(int depositTermMonth) {
        DepositTermMonth = depositTermMonth;
    }

    public String getHolderName() {
        return HolderName;
    }
    public void setHolderName(String holderName) {
        HolderName = holderName;
    }

    public String id;
    private String LoginRefRecId;
    private Date DateFrom;              //с
    //private Date DateTo;                //по
    private int DepositTermMonth;
    private String BankRefRecID;        //id БАНКА
    private String BankOfferRefRecId;    //id ОФФЕРА
    private float StartFunds;         //начальный капитал
    private float AccumulatedFunds;   //итоговый капитал
    private float OtherFunds;  //добавочная сумма
    //private String AccountNumber;       //номер счёта
    private String HolderName;
    private String Notice;
    private float IncomingTax;

}
