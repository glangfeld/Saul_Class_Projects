package Readers.garrett_langfeld;

/**
 * Created by glang on 10/5/2016.
 */

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompanyStockReader {

    public ArrayList<CompanyStock> CS;

    public CompanyStockReader() throws IOException{
        CS = new ArrayList<CompanyStock>();

        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\glang\\OneDrive\\Documents\\Tulane\\Senior Year First Semester\\Capstone\\Saul_Class_Projects\\data\\garrett_langfeld\\ML_Project_Initial_Data2.csv"));
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/ML_Project_Initial_Data5.csv"));
        String line="";

        while ((line = br.readLine()) != null) {
            //skipping first row with headings
            if (line.substring(0, 7).equals("Company") || line.substring(0, 7).equals(",,,,,,,")) {
                //skip this line
            }
            else {
                //System.out.println(line.charAt(0));
                //System.out.println(line);
                //System.out.println(line.substring(0, 7));
                // use comma as separator
                String csvSplitBy = ",";

                //adding data to allData arraylist of arraylists of arraylists

                ArrayList inner = new ArrayList();
                String[] row = line.split(csvSplitBy);
                if (row.length > 0) {
                    String n = row[0];
                    String r = row[1];
                    String d = row[2];
                    float o = Float.parseFloat(row[3]);
                    float h = Float.parseFloat(row[4]);
                    float l = Float.parseFloat(row[5]);
                    float c = Float.parseFloat(row[6]);
                    float v = Float.parseFloat(row[7]);
                    float ac = Float.parseFloat(row[8]);
                    CompanyStock comp = new CompanyStock(n, r, d, o, h, l, c, v, ac);
                    comp.printCompany();
                    CS.add(comp);
                }

                //allData.get(comp_index).add(inner);
            }


        }

    }


    public static void main(String[] args) throws IOException
    {
        CompanyStockReader aapl = new CompanyStockReader();

    }

}
