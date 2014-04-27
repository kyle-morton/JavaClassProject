package javaswingapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mikem_000
 */

import java.nio.file.*; //use for Files & Paths
import java.util.*;
import java.io.*;
import flexjson.*;


public class CountryCodes {
    private static String path = "config.json"; //file that contains country cds
    String ctry;
    String units;
    
    public CountryCodes ()
    {
        this.ctry = null;
        this.units = null;
    }
    
    public static void update()
    {
        rdFile(path);
    }
    
    private static void rdFile (String path)
    {
        byte[] rawFile; //byte array to store read CCs
        String rwd; //used to store actual working directory
        try{
            rwd = new File( "." ).getCanonicalPath();
            rawFile = Files.readAllBytes(Paths.get(path)); //get path object
            String configFile = new String(rawFile); //convert byte array to Str
            JSONDeserializer<List<CountryCodes>> deserializer = new JSONDeserializer<
                    List<CountryCodes>>();
            List stdMap = deserializer.deserialize(configFile); //list using JSON
            //System.out.println(stdMap);
            System.out.println(stdMap.get(0));
            
        
        } //doing io so use exception handling
        
        catch(Exception e)
        {
            System.out.println(e.toString() + "\nError!");
        }
    }

    
    
    
    
    
}
