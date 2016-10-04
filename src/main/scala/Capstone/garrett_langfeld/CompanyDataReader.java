package Capstone.garrett_langfeld;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompanyDataReader
{

    //public String[] allCompanies;
    public ArrayList allCompanies;

    //constructor
    public CompanyDataReader() throws IOException{
        //initializing allCompanies arrayList
        allCompanies = new ArrayList();

        BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Initial_Data.csv"));
        String line="";
        while ((line = br.readLine()) != null) {
            //skipping first row with headings
            if (line.substring(0,7).equals("Company")){
                //skip this line
            }
            else {
                //System.out.println(line.charAt(0));
                //System.out.println(line);
                //System.out.println(line.substring(0, 7));
                // use comma as separator
                String csvSplitBy = ",";
                if (line.charAt(0) != ',') {
                    //getting company name
                    String[] companyName = line.split(csvSplitBy);
                    System.out.println(companyName[0]);
                    //System.out.println(line.split(csvSplitBy)[0]);
                    allCompanies.add(companyName[0]);
                }
                String[] cols = line.split(csvSplitBy);
                //System.out.println("Column 0 = " + cols[0] + " , Column 4= " + cols[4] + " , Column 5=" + cols[5]);
                for (int i = 0; i < cols.length; i++) {
                    cols = line.split(csvSplitBy);
                    String f = cols[i];
                }
            }
        }

        System.out.println(allCompanies);

        /*
        //Get scanner instance
        Scanner scanner = new Scanner(new File("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Initial_Data.csv"));

        //Set the delimiter used in file
        scanner.useDelimiter(",");

        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext())
        {
            System.out.print(scanner.next() + "|");
        }

        //Do not forget to close the scanner
        scanner.close();
        */

    }

    public static void main(String[] args) throws IOException
    {

        CompanyDataReader c = new CompanyDataReader();


        /*
        //Get scanner instance
        Scanner scanner = new Scanner(new File("Initial_Data.csv"));
         
        //Set the delimiter used in file
        scanner.useDelimiter(",");
         
        //Get all tokens and store them in some data structure
        //I am just printing them
        while (scanner.hasNext()) 
        {
            System.out.print(scanner.next() + "|");
        }
         
        //Do not forget to close the scanner  
        scanner.close();
    }
    */
    }
}