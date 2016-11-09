package Readers.garrett_langfeld;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompanyDataReader
{

    //public String[] allCompanies;
    public ArrayList allCompanies;
    public ArrayList<ArrayList<ArrayList<String>>> allData;
    public ArrayList<companyData> compData;

    //constructor
    public CompanyDataReader() throws IOException{
        //initializing allCompanies arrayList
        allCompanies = new ArrayList();
        allData = new ArrayList<ArrayList<ArrayList<String>>>();
        compData = new ArrayList<companyData>();


        //BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Initial_Data11.csv"));
        BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Initial_Data14.csv"));
        String line="";
        //used to keep track of place in outer allData arraylist
        int comp_index = -1;
        while ((line = br.readLine()) != null) {
            //skipping first row with headings
            if (line.substring(0,7).equals("Company") || line.substring(0,7).equals(",,,,,,,")){
                //skip this line
            }
            else {
                //System.out.println(line.charAt(0));
                //System.out.println(line);
                //System.out.println(line.substring(0, 7));
                // use comma as separator
                String csvSplitBy = ",";

                ArrayList inner = new ArrayList<companyData>();
                String[] row = line.split(csvSplitBy);
                if (row.length > 0){
                    String name = row[0];
                    String quarter = row[1];
                    String rating = row[2];
                    String endDate = row[3];
                    float ND_EBITDA = Float.parseFloat(row[4]);
                    float TD_EBIT = Float.parseFloat(row[5]);
                    float ND_EBIT = Float.parseFloat(row[6]);
                    float EBITDA_Interest = Float.parseFloat(row[7]);
                    float EBITDA_Cap_Int = Float.parseFloat(row[8]);
                    float EBIT_Int = Float.parseFloat(row[9]);
                    float int_exp = Float.parseFloat(row[10]);
                    float CE_TA = Float.parseFloat(row[11]);
                    float LT_DE = Float.parseFloat(row[12]);
                    float LT_DC = Float.parseFloat(row[13]);
                    float LT_DTA = Float.parseFloat(row[14]);
                    float TDE = Float.parseFloat(row[15]);
                    float TDC = Float.parseFloat(row[16]);
                    float TDA = Float.parseFloat(row[17]);
                    float num_rating = Float.parseFloat(row[18]);
                    float rating_change = Float.parseFloat(row[19]);
                    companyData comp = new companyData(name,quarter,rating, endDate, ND_EBITDA, TD_EBIT, ND_EBIT, EBITDA_Interest, EBITDA_Cap_Int, EBIT_Int, int_exp, CE_TA, LT_DE, LT_DC, LT_DTA, TDE, TDC, TDA, num_rating, rating_change);
                    compData.add(comp);
                }

            }
        }



    }








    public static void main(String[] args) throws IOException
    {

        CompanyDataReader c = new CompanyDataReader();
        c.compData.get(5).printCompany();
 int i =1;


    }
}