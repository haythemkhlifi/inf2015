package com.uqam.agile;
import org.json.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        JSONObject obj = new JSONObject(str) ;
    
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        JSONArray arr = obj.getJSONArray("posts");

        //for(int i =0; i < arr.length(); i++)
        //    String post_id = arr.getJSONObject(i).getString("post_id");
        //}
    }

}



