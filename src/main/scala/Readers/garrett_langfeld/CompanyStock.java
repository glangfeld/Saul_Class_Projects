package Readers.garrett_langfeld;

/**
 * Created by glang on 10/5/2016.
 */

import java.util.*;

public class CompanyStock {

    public String name;
    public String rating;
    public String date;
    public float open;
    public float high;
    public float low;
    public float close;
    public float volume;
    public float adj_close;

    public CompanyStock(String n, String r, String d, float o, float h, float l, float c, float v, float ac){
        name = n;
        rating = r;
        date = d;
        open = o;
        high = h;
        low = l;
        close = c;
        volume = v;
        adj_close = ac;

    }

    public void printCompany(){
        ArrayList info = new ArrayList();
        info.add(name);
        info.add(rating);
        info.add(date);
        info.add(open);
        info.add(high);
        info.add(low);
        info.add(close);
        info.add(volume);
        info.add(adj_close);
        System.out.println(info);
    }


}
