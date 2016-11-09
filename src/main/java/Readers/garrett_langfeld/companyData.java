package Readers.garrett_langfeld;

import java.util.ArrayList;

/**
 * Created by glang on 10/3/2016.
 */
public class companyData {

    public String name;
    public String quarter;
    public String rating;
    public String endDate;
    public float ND_EBITDA;
    public float TD_EBIT;
    public float ND_EBIT;
    public float EBITDA_Interest;
    public float EBITDA_Cap_Int;
    public float EBIT_Int;
    public float int_exp;
    public float CE_TA;
    public float LT_DE;
    public float LT_DC;
    public float LT_DTA;
    public float TDE;
    public float TDC;
    public float TDA;
    public float num_rating;
    public float rating_change;
    public String ratingGroup;
    public String ratingLetter;
    public String rating6Groups;
    public String risk;


    //constructor
    public companyData(String n, String q, String r, String ed, float NDEBITDA, float TDEBIT, float NDEBIT, float EBITDAInterest, float EBITDACapInt, float EBITInt, float intexp, float CETA, float LTDE, float LTDC, float LTDTA, float TotDE, float TotDC, float TotDA, float numrating, float ratingchange){
            //System.out.println("Input array for companyData : " + A);
        name = n;
        quarter = q;
        rating = r;
        endDate = ed;
        ND_EBITDA = NDEBITDA;
        TD_EBIT = TDEBIT;
        ND_EBIT = NDEBIT;
        EBITDA_Interest = EBITDAInterest;
        EBITDA_Cap_Int = EBITDACapInt;
        EBIT_Int = EBITInt;
        int_exp = intexp;
        CE_TA = CETA;
        LT_DE = LTDE;
        LT_DC = LTDC;
        LT_DTA = LTDTA;
        TDE = TotDE;
        TDC = TotDC;
        TDA = TotDA;
        num_rating = numrating;
        rating_change = ratingchange;
        if (num_rating < 11){
            ratingGroup = "IG";
        }
        else{
            ratingGroup = "SG";
        }
        //setting ratingLetter
        if (num_rating < 8){
            ratingLetter = "A";
        }
        else if (num_rating >= 8 && num_rating < 16){
            ratingLetter = "B";
        }
        else{
            ratingLetter = "C";
        }
        //setting rating6Groups
        if (num_rating < 5){
            rating6Groups = "Aa";
        }
        else if (num_rating >= 5 && num_rating < 8){
            rating6Groups = "A";
        }
        else if (num_rating >= 8 && num_rating < 11){
            rating6Groups = "Baa";
        }
        else if (num_rating >= 11 && num_rating < 14){
            rating6Groups = "Ba";
        }
        else if (num_rating >= 14 && num_rating < 17){
            rating6Groups = "B";
        }
        else{
            rating6Groups = "C";
        }
        if (num_rating < 5){
            risk = "safe";
        }
        else if (num_rating >= 5 && num_rating < 11){
            risk = "low";
        }
        else if (num_rating >= 11 && num_rating < 17){
            risk = "speculative";
        }
        else{
            risk = "highly_speculative";
        }
    }

    public void printCompany(){
        System.out.println(name);
        System.out.println(quarter);
        System.out.println(rating);
        System.out.println(endDate);
        System.out.println(name);
        System.out.println(ND_EBITDA);
        System.out.println(TD_EBIT);
        System.out.println(ND_EBIT);
        System.out.println(EBITDA_Interest);
        System.out.println(EBITDA_Cap_Int);
        System.out.println(EBIT_Int);
        System.out.println(int_exp);
        System.out.println(CE_TA);
        System.out.println(LT_DE);
        System.out.println(LT_DC);
        System.out.println(LT_DTA);
        System.out.println(TDE);
        System.out.println(TDC);
        System.out.println(TDA);
        System.out.println(num_rating);
        System.out.println(rating_change);
    }

}
