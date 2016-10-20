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

    }

}
