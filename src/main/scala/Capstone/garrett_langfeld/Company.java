package Capstone.garrett_langfeld;

import java.util.*;

/**
 * Created by glang on 10/3/2016.
 */
public class Company {

    public String name;
    public ArrayList<companyData> data;

    public Company(String S, ArrayList<ArrayList> A){
        name = S;
        ArrayList<companyData> data = new ArrayList<companyData>();
        //iterating through A
        for (int i = 0; i < A.size(); i++){
            companyData comp = new companyData(A.get(i));
            data.add(comp);
        }
    }

}
