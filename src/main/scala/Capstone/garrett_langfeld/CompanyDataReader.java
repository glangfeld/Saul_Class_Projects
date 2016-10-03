package Capstone.garrett_langfeld;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompanyDataReader
{

    //constructor
    public CompanyDataReader() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Initial_Data.csv"));
        String line="";
        while ((line = br.readLine()) != null) {

            String cvsSplitBy = ",";
            // use comma as separator
            String[] cols = line.split(cvsSplitBy);
            System.out.println("Coulmn 4= " + cols[4] + " , Column 5=" + cols[5]);
            for (int i = 0; i < cols.length; i++){
                cols = line.split(cvsSplitBy);
                String f = cols[i];
            }
        }
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