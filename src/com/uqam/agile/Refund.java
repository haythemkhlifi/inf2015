package com.uqam.agile;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author gh0st
 */
public class Refund {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        boolean fileError = false;
        try {
            Object fileobject = parser.parse(new FileReader("file.json"));
            JSONObject obj = (JSONObject) fileobject;

            String contrat  = (String) obj.get("contrat");
            String client  = (String) obj.get("client");
            String mois  = (String) obj.get("mois");
            JSONArray reclamations = (JSONArray) obj.get("reclamations");
            
            
        }
        catch( Exception e){
            fileError = true;
        }
    }

}



