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
    //public Country[] countries = new Country[90]; //array of 90 country codes for comboLists
    public HashMap <String, String> ccCodes = new HashMap();
    String ctry;
    String units;
    
    
    
    public CountryCodes ()
    {
        this.ctry = null;
        this.units = null;
    }
    
    public void update() //use as get method for country codes
    {
        rdFile(path);
    }
    
    private void rdFile (String path)
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
            //System.out.println(stdMap.get(0));
            
            
            String units;
            String country;
            
            int firstIndex = 0; //use to get substrings for Country objs
            int nextIndex = 0;
            
            int countriesIndex = 0;
            for (Object c : stdMap)
            {
                
                firstIndex = c.toString().indexOf("=", firstIndex);
                units = c.toString().substring(firstIndex+1, firstIndex+4);
                //units are 3 chars, after =
                
                
                
                firstIndex = c.toString().indexOf("=", firstIndex+1);
                //search for next =, country name will follow
                nextIndex = c.toString().indexOf("}", firstIndex);
                //search for end of country name, nextIndex-1
                country = c.toString().substring(firstIndex+1, nextIndex);
                
                ccCodes.put(country, units);
                //save country and units inside hashmap
                firstIndex = 0;
                nextIndex = 0;
            }
            
            
        } //doing io so use exception handling
        
        catch(Exception e)
        {
            System.out.println(e.toString() + "\nError!");
        }
        
    }

    
    
    
    
    
}
