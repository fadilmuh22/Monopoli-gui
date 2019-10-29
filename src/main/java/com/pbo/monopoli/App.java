package com.pbo.monopoli;

import java.util.ArrayList;
import java.util.HashMap;

import com.pbo.monopoli.models.IniClass;

public class App {
    public static void main( String[] args )
    {
        HashMap<String, Integer> aset = new HashMap<>();
        aset.put("r", 4);
        System.out.println( "r adalah " + aset.get("r") );

        IniClass cl = new IniClass();
        cl.hallo();
        ArrayList<String> listString = new ArrayList<>();
        listString.add("fadil");
        System.out.println(listString.get(0));
        // Coba cb = new Coba();
        // cb.getFile();
        // System.out.println( "Hello World!" );
    }

}
