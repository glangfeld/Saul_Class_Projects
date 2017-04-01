package Readers.garrett_langfeld;

/**
 * Created by glang on 3/31/2017.
 */

import java.util.ArrayList;

public class companyDataCompustat {
    public float date;
    public float company;
    public float LTD_IC;
    public float TD_IC;
    public float R_CA;
    public float TD_TA;
    public float TD_EBITDA;
    public float LTD_TL;
    public float CF_TD;
    public float LTD_BE;
    public float TD_A;
    public float TD_C;
    public float TD_E;
    public float Int_Cov;
    public float quick_ratio;
    public float current_ratio;
    public float cash_conv;
    public String rating;
    public float sector;

    //constructor
    public companyDataCompustat(float d, float c, float LTDIC, float TDIC, float RCA, float TDTA, float TDEbitda, float LTDL, float CFTD, float LTDBE, float TDA, float TDC, float TDE, float IC, float qr, float cr, float cc, String rat, float sect){
        //System.out.println("Input array for companyData : " + A);
        date = d;
        company = c;
        LTD_IC = LTDIC;
        TD_IC = TDIC;
        R_CA = RCA;
        TD_TA = TDTA;
        TD_EBITDA = TDEbitda;
        LTD_TL = LTDL;
        CF_TD = CFTD;
        LTD_BE = LTDBE;
        TD_A = TDA;
        TD_C = TDC;
        TD_E = TDE;
        Int_Cov = IC;
        quick_ratio = qr;
        current_ratio = cr;
        cash_conv = cc;
        rating = rat;
        sector = sect;

    }

    public void printCompany(){
        System.out.println(date);
        System.out.println(company);
        System.out.println(rating);
        System.out.println(LTD_IC);
        System.out.println(TD_IC);
        System.out.println(R_CA);
        System.out.println(TD_TA);
        System.out.println(TD_EBITDA);
        System.out.println(LTD_TL);
        System.out.println(CF_TD);
        System.out.println(LTD_BE);
        System.out.println(TD_A);
        System.out.println(TD_C);
        System.out.println(TD_E);
        System.out.println(Int_Cov);
        System.out.println(quick_ratio);
        System.out.println(current_ratio);
        System.out.println(cash_conv);
        System.out.println(rating);
        System.out.println(sector);

    }

}
