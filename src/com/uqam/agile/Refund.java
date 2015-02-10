package com.uqam.agile;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author gh0st
 */
public class Refund {
    
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        boolean fileError = false;
        
        try {
            Object fileobject = parser.parse(new FileReader("file.json"));
            JSONObject obj = (JSONObject) fileobject;

            String contrat  = (String) obj.get("contrat");
            String client  = (String) obj.get("client");
            String mois  = (String) obj.get("mois");
            JSONArray reclamations = (JSONArray) obj.get("reclamations");
            
            Iterator i = reclamations.iterator();

            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                long soin = (long)slide.get("soin");
                System.out.println(soin);
            }
        
            switch (contrat) {
                case "A":  
                     break;
                case "B":  
                     break;
                case "C":  
                     break;
                case "D":  
                     break;
                default: fileError = true;
                     break;
            }
            
        }
        catch( Exception e){
            fileError = true;
            e.printStackTrace();
        }
    }

}



