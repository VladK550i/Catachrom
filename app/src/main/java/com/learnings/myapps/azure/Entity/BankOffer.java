package com.learnings.myapps.azure.entity;

import java.util.Date;


public class BankOffer {

    public String getOfferName() {
        return OfferName;
    }
    public void setOfferName(String offerName) {
        OfferName = offerName;
    }

    public String getCurrency() {
        return Currency;
    }
    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getCapitalize() {
        return Capitalize;
    }
    public void setCapitalize(String capitalize) {
        Capitalize = capitalize;
    }

    public boolean isWithReplenishment() {
        return withReplenishment;
    }
    public void setWithReplenishment(boolean withReplenishment) {
        this.withReplenishment = withReplenishment;
    }

    public boolean isWithPartialCashOut() {
        return withPartialCashOut;
    }
    public void setWithPartialCashOut(boolean withPartialCashOut) {
        this.withPartialCashOut = withPartialCashOut;
    }

//    public Date getActiveFrom() {
//        return ActiveFrom;
//    }
//    public void setActiveFrom(Date activeFrom) {
//        ActiveFrom = activeFrom;
//    }
//
//    public Date getActiveTo() {
//        return ActiveTo;
//    }
//    public void setActiveTo(Date activeTo) {
//        ActiveTo = activeTo;
//    }

    public float getInterestRate() {
        return InterestRate;
    }
    public void setInterestRate(float interestRate) {
        InterestRate = interestRate;
    }

    public String getInterestPeriodicity() {
        return InterestPeriodicity;
    }
    public void setInterestPeriodicity(String interestPeriodicity) {
        InterestPeriodicity = interestPeriodicity;
    }

    public float getEarlInterestRate() {
        return EarlInterestRate;
    }
    public void setEarlInterestRate(float earlInterestRate) {
        EarlInterestRate = earlInterestRate;
    }

    public String getBankRefRecId() {
        return BankRefRecId;
    }
    public void setBankRefRecId(String bankRefRecId) {
        BankRefRecId = bankRefRecId;
    }

    public int getEarlyTerminationTerm() {
        return EarlyTerminationTerm;
    }
    public void setEarlyTerminationTerm(int earlyTerminationTerm) {
        EarlyTerminationTerm = earlyTerminationTerm;
    }

    public int getMinStartFunds() {
        return MinStartFunds;
    }
    public void setMinStartFunds(int minStartFunds) {
        MinStartFunds = minStartFunds;
    }


    public String id;
    private String BankRefRecId;
    private String OfferName;
    private String Currency;  //enum inside builder
    private String Capitalize; //enum
    private boolean withReplenishment;
    private boolean withPartialCashOut;
    private String InterestPeriodicity; //enum
    //private Date ActiveFrom;
    //private Date ActiveTo;
    private int MinStartFunds;
    private float InterestRate;
    private float EarlInterestRate;
    private int EarlyTerminationTerm;

}
