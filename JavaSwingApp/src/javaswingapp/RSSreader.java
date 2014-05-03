/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaswingapp;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author mikem_000
 */

//used to read RSS, get back values for USD to XXX conversions
public class RSSreader {
    
    public HashMap<String, Double> getRSS() //call on update to refresh rates
    {
        String URLstr = "http://themoneyconverter.com/rss-feed/";
        String FromCurrency = "USD";
        SyndFeed feed = null;
        HashMap <String, Double> conversionRates = new HashMap();
        //use hm to store country/convRates from USD

        try{
            URL feedURL = new URL(URLstr+FromCurrency+"/rss.xml");

            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedURL));

            System.out.println(feed);
        } //end try

        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());

        } //end catch
        
        if (feed != null)
        {
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries())
            {
                String title = entry.getTitle();
                String countryUnits = title.substring(0,3); 
                //get units abbrev to use as key in hashmap
                
                
                String descr = entry.getDescription().getValue();
                int firstIndex = descr.indexOf("=")+2; //get index of 1st digit
                //1 United States Dollar = XX.XXXX Pesos
                
                
                int lastIndex = descr.indexOf(" ", firstIndex); //get end of dbl
                String rateStr = descr.substring(firstIndex, lastIndex); 
                String finalRateStr = rateStr.replaceAll(",", ""); //delete commas
                Double convRate = Double.parseDouble(finalRateStr); //convert str to dbl
                conversionRates.put(countryUnits, convRate); //put K/V pair in HM

                
                
                
                
//                System.out.println("Title: "+title+"\nDescription: "+descr);
//                System.out.println("convRate: " + convRate + "\n");
                
            } //end for
        } //end if
        
        else
        {
            System.out.println("RSS Feed Unavailable");
        }
        
        return conversionRates;
    } //end getRSS method
    
    
}
