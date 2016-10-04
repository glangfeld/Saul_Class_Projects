package Capstone.garrett_langfeld;

import java.util.ArrayList;

/**
 * Created by glang on 10/3/2016.
 */
public class companyData {

    public Object quarter;
    public Object rating;
    public Object endDate;
    public Object ND_EBITDA;
    public Object TD_EBIT;
    public Object ND_EBIT;
    public Object EBITDA_Interest;
    public Object EBITDA_Cap_Int;
    public Object EBITDA_Int;
    public Object int_exp;
    public Object CE_TA;
    public Object LT_DE;
    public Object LT_DC;
    public Object LT_DTA;
    public Object TDE;
    public Object TDC;
    public Object TDA;


    //constructor
    public companyData(ArrayList A){
        if (A.size() > 0) {
            System.out.println("Input array for companyData : " + A.get(0));
            quarter = A.get(0);
            rating = A.get(1);
            endDate = A.get(2);
            ND_EBITDA = A.get(3);
            TD_EBIT = A.get(4);
            ND_EBIT = A.get(5);
            EBITDA_Interest = A.get(6);
            EBITDA_Cap_Int = A.get(7);
            EBITDA_Int = A.get(8);
            int_exp = A.get(9);
            CE_TA = A.get(10);
            LT_DE = A.get(11);
            LT_DC = A.get(12);
            LT_DTA = A.get(13);
            TDE = A.get(14);
            TDC = A.get(15);
            TDA = A.get(16);
        }

    }

}
