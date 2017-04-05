package Readers.garrett_langfeld;

/**
 * Created by glang on 3/31/2017.
 */

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompanyDataReaderCompustat {
    //public String[] allCompanies;
    public ArrayList allCompanies;
    public ArrayList<ArrayList<ArrayList<String>>> allData;
    public ArrayList<companyDataCompustat> compData;

    //constructor
    public CompanyDataReaderCompustat() throws IOException{
        //initializing allCompanies arrayList
        allCompanies = new ArrayList();
        allData = new ArrayList<ArrayList<ArrayList<String>>>();
        compData = new ArrayList<companyDataCompustat>();


        //BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Initial_Data11.csv"));
        //BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/Compustat_Data_NN_2.csv"));
        //BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/Compustat_Data_2_Randomized.csv"));
        BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Compustat/Compustat_Data_2_Randomized_Stand.csv"));
        String line="";
        //used to keep track of place in outer allData arraylist
        int comp_index = -1;
        while ((line = br.readLine()) != null) {
            //skipping first row with headings
            if (line.substring(0,4).equals("Data") || line.substring(0,7).equals(",,,,,,,")){
                //skip this line
            }
            else {
                //System.out.println(line.charAt(0));
                //System.out.println(line);
                //System.out.println(line.substring(0, 7));
                // use comma as separator
                String csvSplitBy = ",";

                ArrayList inner = new ArrayList<companyDataCompustat>();
                String[] row = line.split(csvSplitBy);
                if (row.length > 0){
                    float date = Float.parseFloat(row[0]);
                    float company = Float.parseFloat(row[1]);
                    float LTD_IC = Float.parseFloat(row[2]);
                    float TD_IC = Float.parseFloat(row[3]);
                    float R_CA = Float.parseFloat(row[4]);
                    float TD_TA = Float.parseFloat(row[5]);
                    float TD_EBITDA = Float.parseFloat(row[6]);
                    float LTD_TL = Float.parseFloat(row[7]);
                    float CF_TD = Float.parseFloat(row[8]);
                    float LTD_BE = Float.parseFloat(row[9]);
                    float TD_A = Float.parseFloat(row[10]);
                    float TD_C = Float.parseFloat(row[11]);
                    float TD_E = Float.parseFloat(row[12]);
                    float Int_Cov = Float.parseFloat(row[13]);
                    float quick_ratio = Float.parseFloat(row[14]);
                    float current_ratio = Float.parseFloat(row[15]);
                    float cash_conv = Float.parseFloat(row[16]);
                    //float rating = Float.parseFloat(row[17]);
                    String rating = row[17];
                    float sector = Float.parseFloat(row[18]);

                    companyDataCompustat comp = new companyDataCompustat(date,company,LTD_IC,TD_IC,R_CA,TD_TA,TD_EBITDA,LTD_TL,CF_TD,LTD_BE,TD_A,TD_C,TD_E,Int_Cov,quick_ratio,current_ratio,cash_conv,rating,sector);
                    compData.add(comp);
                }

            }
        }



    }








    public static void main(String[] args) throws IOException
    {

        CompanyDataReaderCompustat c = new CompanyDataReaderCompustat();
        c.compData.get(5).printCompany();
        int i =1;


    }


}
