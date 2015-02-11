package com.uqam.agile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            //L'object JSON qui sera sauvegarder sans le fichier de sortie
            JSONObject resultingObj = (JSONObject) fileobject;
            //on récupere la valeur du contrat du fichier source
            String contrat  = (String) obj.get("contrat");
            //on récupere la valeur du client du fichier source
            String client  = (String) obj.get("client");
            //enregistre le client dans le fichier de sortie
            resultingObj.put("client",client);
            //on récupere la valeur du mois du fichier source
            String mois  = (String) obj.get("mois");
            //enregistre le mois dans le fichier de sortie
            resultingObj.put("mois",mois);
            //on récupere les réclamations du fichier source
            JSONArray reclamations = (JSONArray) obj.get("reclamations");
            
            
            //on parcours la liste des réclamations
            Iterator i = reclamations.iterator();
            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                long soin = (long)slide.get("soin");
                String montant = (String)slide.get("montant");
                //on vérifie quelle type de contrat est assigné au client et on fais le calcul en fonction de celui-ci
                switch (contrat) {
                    case "A":  calculMontantA(soin, montant);
                         break;
                    case "B":  calculMontantB(soin, montant);
                         break;
                    case "C":  calculMontantC(soin, montant);
                         break;
                    case "D":  calculMontantD(soin, montant);
                         break;
                    default: fileError = true;
                         break;
                }
            }    
        }
        catch( Exception e){
            fileError = true;
            e.printStackTrace();
        }
    }
    
    public static int calculMontantA(int soin, double montant){
    
double refund = 0.00;
    
    if (soin >= 300 && soin <= 399) {
        refund = 0.00 ;
        
     } else { 
    
    switch (soin) {
case 0:   refund = 0.25*montant;
break;
case 100: refund = 0.25*montant;
break;
case 200: refund = 0.25*montant;
break;
case 400: refund = 0.00;
break;
case 500: refund = 0.25*montant;
break;
case 600: refund = 0.40*montant;
break;
case 700: refund = 0.70*montant;
break;

default: fileError = true;
break;
}
    }//fin else

return refund;
}

public static int calculMontantB(int soin, double montant){
    
    double refund = 0.00;
    
    if (soin >= 300 && soin <= 399) {
        refund = 0.50*montant;
                } 
    else { 
    
    switch (soin) {
case 0: 
        if (montant > 40.00) { refund = 40.00; }
           else { refund = 0.50*montant; }
break;
case 100: 
        if (montant > 50.00) { refund = 50.00; }
           else { refund = 0.50*montant; }
break;
case 200: if (montant > 70.00) { refund = 70.00; }
            else { refund = montant; }
break;
case 400: 
        refund = 0.00;
break;
case 500: 
        if (montant > 50.00) { refund = 50.00; }
            else { refund = 0.50*montant; }
break;
case 600: 
        refund = montant;
break;
case 700: refund = 0.70*montant;
break;

default: fileError = true;
break;
}
    }//fin else

return refund;
}



public static int calculMontantC(int soin, double montant){
double refund = 0.00;
    
    if (soin >= 300 && soin <= 399) {
        refund = 0.90*montant ;
        
     } else { 
    
    switch (soin) {
case 0:   refund = 0.90*montant;
break;
case 100: refund = 0.90*montant;
break;
case 200: refund = 0.90*montant;
break;
case 400: refund = 0.00;
break;
case 500: refund = 0.90*montant;
break;
case 600: refund = 0.90*montant;
break;
case 700: refund = 0.90*montant;
break;

default: fileError = true;
break;
}
    }//fin else

return refund;
}

public static int calculMontantD(int soin, double montant){
    
double refund = 0.00;
    
    if (soin >= 300 && soin <= 399) {
        refund = montant;
                } 
    else { 
    
    switch (soin) {
case 0: 
        if (montant > 85.00) { refund = 85.00; }
           else { refund = montant; }
break;
case 100: 
        if (montant > 75.00) { refund = 75.00; }
           else { refund = montant; }
break;
case 200: if (montant > 100.00) { refund = 100.00; }
            else { refund = montant; }
break;
case 400: if (montant > 65.00) { refund = 65.00; }
            else { refund = montant; }
break;
case 500: 
        if (montant > 75.00) { refund = 75.00; }
            else { refund = montant; }
break;
case 600: if (montant > 100.00) { refund = 100.00; }
            else { refund = montant; }
break;
case 700: if (montant > 90.00) { refund = 90.00; }
            else { refund = montant; }
break;

default: fileError = true;
break;
}
    }//fin else

return refund;
}
    
    public void saveOutputFile(JSONObject obj) throws IOException{
        FileWriter file = new FileWriter("output.xml");
        try {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            file.flush();
            file.close();
        }
    }
}
