package com.uqam.agile;
import java.util.Iterator;
import org.json.*;

/**
 *
 * @author gh0st
 */
public class Refund {
    
    public static void main(String[] args) {
        String str = "{\n" +
        " \"client\": \"100323\",\n" +
        " \"contrat\": \"A\",\n" +
        " \"mois\": \"2015-01\",\n" +
        " \"reclamations\": [\n" +
        " {\n" +
        " \"soin\": 100,\n" +
        " \"date\": \"2015-01-11\",\n" +
        " \"montant\": \"234.00$\"\n" +
        " },\n" +
        " {\n" +
        " \"soin\": 200,\n" +
        " \"date\": \"2015-01-13\",\n" +
        " \"montant\": \"90.00$\"\n" +
        " },\n" +
        " {\n" +
        " \"soin\": 334,\n" +
        " \"date\": \"2015-01-23\",\n" +
        " \"montant\": \"125.00$\"\n" +
        " }\n" +
        " ]\n" +
        "}";
        
        boolean fileError = false;
        
        JSONObject obj = new JSONObject(str) ; 
        JSONObject objTemp;
        
        String contract  = obj.getString("contrat");
        int client  = obj.getInt("client");
        String mois  = obj.getString("mois");
        JSONArray arr = obj.getJSONArray("reclamations");
        
        for (int i = 0; i < arr.length(); i++)
        {
            objTemp = arr.getJSONObject(i);
            System.out.println(objTemp.getInt("soin"));
        }
        
         switch (contract) {
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

}



