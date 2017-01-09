package com.learnings.myapps.azure.main.fragments.processingModules;
import  java.util.Date;
/**
 * Created by USER on 08.01.2017.
 */

public final class DepositProcess {

    public static final int INTERESTRATE = 0;
    public static final int PROFIT = 1;
    public static final int FULLSUMM = 2;

    private DepositProcess() {
        throw new AssertionError();
    }
    //private static int myStaticMember;
    public static Float[] GetSimpleAccomulated(float startFunds, float interestRate, int depositDuration) {
        float profit, fullsumm, percent;
        Float[] result = new Float[3];

        profit = (startFunds*interestRate*depositDuration)/(365*100);
        fullsumm = startFunds + profit;
        percent = ((fullsumm - startFunds)/startFunds)*100;
        result[INTERESTRATE] = percent;
        result[PROFIT] = profit;
        result[FULLSUMM] = fullsumm;
        return result;
    }


    public static Float[] GetComplexAccomulated(float startFunds, float interestRate, int depositDuration, String interestTerm) { //DepositDuretion in Days !!!!
            //difficult percent

            Float[] result = new Float[3];
            float profit, fullsumm, percent;

            if (interestTerm.equals("daily")) {
                //Capitalization every day

                fullsumm = (float) (startFunds *Math.pow(1+interestRate/(365*100),depositDuration));
                profit = fullsumm - startFunds;
                percent = (profit/ startFunds) * 100;
                result[INTERESTRATE] = percent;
                result[PROFIT] = profit;
                result[FULLSUMM] = fullsumm;
            }

            if (interestTerm.equals("monthly")) {
                //Capitalization every 30 day

                fullsumm = (float) (startFunds *Math.pow(1+interestRate*30/(365*100),depositDuration/30));
                profit = fullsumm - startFunds;
                percent = (profit/ startFunds) * 100;
                result[INTERESTRATE] = percent;
                result[PROFIT] = profit;
                result[FULLSUMM] = fullsumm;
            }

            if (interestTerm.equals("quartally")) {

                //Capitalization every 90 day (kvartal)

                fullsumm = (float) (startFunds *Math.pow(1+interestRate*90/(365*100),depositDuration/90));
                profit = fullsumm - startFunds;
                percent = (profit/ startFunds) * 100;
                result[INTERESTRATE] = percent;
                result[PROFIT] = profit;
                result[FULLSUMM] = fullsumm;
            }

            if (interestTerm.equals("yearly")) {

                //Capitalization every 365 day

                fullsumm = (float) (startFunds *Math.pow(1+interestRate/100,depositDuration/365));
                profit = fullsumm - startFunds;
                percent = (profit/ startFunds) * 100;
                result[INTERESTRATE] = percent;
                result[PROFIT] = profit;
                result[FULLSUMM] = fullsumm;
            }

            return result;
    }

    public static float GetTaxBY(float startFunds, float interestRate, int depositDuration,  float profit, Date startDate, String currency) {
        Date date = new Date(2016, 04, 1);
        if(startDate.before(date)){
            return 0;
        }
        if((depositDuration>365 && currency.equals("BYN")) )//|| (depositDuration>730 && currency != "BYN") )
        {
            return 0;
        }
        else if(depositDuration>730){
            return 0;
        }

        if(interestRate<=1.7 && currency.equals("BYN")){
            return 0;
        }
        else if(interestRate<= 0.5){
            return 0;
        }
        return (float) (profit*0.13);
    }

    public static float GetTaxRU(float startFunds, float interestRate, int depositDuration,  float profit, Date startDate, String currency) {

        if(interestRate<=13.25 && currency.equals("RUR")){
            return 0;
        }
        else if(interestRate<= 8){
            return 0;
        }
        return (float) (profit*0.35);
    }

}
