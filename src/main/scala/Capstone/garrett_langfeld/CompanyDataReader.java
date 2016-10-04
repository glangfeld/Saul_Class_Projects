package Capstone.garrett_langfeld;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CompanyDataReader
{

    //public String[] allCompanies;
    public ArrayList allCompanies;
    public ArrayList<ArrayList<ArrayList>> allData;
    public ArrayList<Company> compData;

    //constructor
    public CompanyDataReader() throws IOException{
        //initializing allCompanies arrayList
        allCompanies = new ArrayList();
        allData = new ArrayList<ArrayList<ArrayList>>();
        compData = new ArrayList<Company>();

        BufferedReader br = new BufferedReader(new FileReader("/Users/glang/OneDrive/Documents/Tulane/Senior Year First Semester/Capstone/Saul_Class_Projects/data/garrett_langfeld/Initial_Data.csv"));
        String line="";
        //used to keep track of place in outer allData arraylist
        int comp_index = -1;
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
                    //System.out.println(companyName[0]);
                    //System.out.println(line.split(csvSplitBy)[0]);
                    allCompanies.add(companyName[0]);
                    //incrementing comp_index
                    comp_index++;
                    //creating middle arrayList, then adding to allData
                    ArrayList<ArrayList> middle = new ArrayList<ArrayList>();
                    allData.add(middle);
                }
                //adding data to allData arraylist of arraylists of arraylists
                else{
                    ArrayList inner = new ArrayList();
                    String[] row = line.split(csvSplitBy);
                    if (row.length > 0){
                        for (int i = 0; i < row.length; i++){
                            if(row[i].length() > 0) {
                                inner.add(row[i]);
                            }
                        }
                    }
                    //System.out.println(inner);
                    allData.get(comp_index).add(inner);
                }
                String[] cols = line.split(csvSplitBy);
                //System.out.println("Column 0 = " + cols[0] + " , Column 4= " + cols[4] + " , Column 5=" + cols[5]);
                for (int i = 0; i < cols.length; i++) {
                    cols = line.split(csvSplitBy);
                    String f = cols[i];
                }
            }
        }

        //filling compData arraylist
        /*
        for (int j = 0; j < allData.size(); j++){
            String name = allCompanies.get(j).toString();
            System.out.println(name);
            ArrayList<ArrayList> data = allData.get(j);
            //System.out.println(data);
            Company comp = new Company(name, data);
            compData.add(comp);
        }
        */

        System.out.println(allCompanies);
        System.out.println(allData);
        //System.out.println(compData);

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