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


    //constructor
    public companyData(String name, String quarter, String rating, String endDate, float ND_EBITDA, float TD_EBIT, float ND_EBIT, float EBITDA_Interest, float EBITDA_Cap_Int, float EBIT_Int, float int_exp, float CE_TA, float LT_DE, float LT_DC, float LT_DTA, float TDE, float TDC, float TDA){
            //System.out.println("Input array for companyData : " + A);
        name = name;
        quarter = quarter;
        rating = rating;
        endDate = endDate;
        ND_EBITDA = ND_EBITDA;
        TD_EBIT = TD_EBIT;
        ND_EBIT = ND_EBIT;
        EBITDA_Interest = EBITDA_Interest;
        EBITDA_Cap_Int = EBITDA_Cap_Int;
        EBIT_Int = EBIT_Int;
        int_exp = int_exp;
        CE_TA = CE_TA;
        LT_DE = LT_DE;
        LT_DC = LT_DC;
        LT_DTA = LT_DTA;
        TDE = TDE;
        TDC = TDC;
        TDA = TDA;

    }

}
