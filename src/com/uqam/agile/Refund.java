package com.uqam.agile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Refund {
    public static void main(String[] args) throws IOException {
        JSONParser parser = new JSONParser();
        
        //L'object JSON retournée en cas d'erreur
        JSONObject errorObj = new JSONObject();
        errorObj.put("message","Données invalides");
  
        try {
            Object fileobject = parser.parse(new FileReader("file.json"));
            JSONObject obj = (JSONObject) fileobject;
            
            //L'object JSON qui sera sauvegarder Dans le fichier de sortie
            JSONObject resultingObj = new JSONObject();
        
            /////////////////////////////////////////////////////////
            //********LECTURE ET ECRITURE
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
            //////////////////////////////////////////////////////////////
            
            //on parcours la liste des réclamations
            Iterator i = reclamations.iterator();
            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                long soin = (long)slide.get("soin");
                String montant = (String)slide.get("montant");
                //on vérifie quelle type de contrat est assigné au client et on fais le calcul en fonction de celui-ci
                switch (contrat) {
                    case "A":  slide.put("montant", calculMontantA(soin, getMontantFromString(montant)));
                         break;
                    case "B":  slide.put("montant", calculMontantB(soin, getMontantFromString(montant)));
                         break;
                    case "C":  slide.put("montant", calculMontantC(soin, getMontantFromString(montant)));
                         break;
                    case "D":  slide.put("montant", calculMontantD(soin, getMontantFromString(montant)));
                         break;
                    default: errorDetected = true;
                         break;
                }
            }
            //enregistre les réclamations du client dans l'object qui sera enregistré dans le fichier de sortie
            resultingObj.put("reclamations",reclamations);
            //En cas d'erreur (données Invalides)
            if (errorDetected) resultingObj = errorObj;
            //Sauvegarde l'objet JSON dans le fichier de sortie
            saveOutputFile(resultingObj);
        }
        catch( Exception e){
            saveOutputFile(errorObj);
        }
  
    }
    
    public static boolean errorDetected = false;
    
    public static Float getMontantFromString(String montant){
        String[] data = montant.split("\\$");
        Float montantConverti =  Float.parseFloat(data[0]);       
        return montantConverti ;
    }  

    public static String calculMontantA(long soin, float montant){
        if (soin == 0)
            montant = (montant /100)*25;
        else if (soin == 100)
            montant = (montant /100)*25;
        else if (soin == 200)
            montant = (montant /100)*25;
        else if (soin >= 300 && soin <=399)
            montant = 0;
        else if (soin == 400)
            montant = 0;
        else if (soin == 500)
            montant = (montant /100)*25;
        else if (soin == 600)
            montant = (montant /100)*40;
        else if (soin == 700)
            montant = 0;
        else
            errorDetected = true;
        return String.format("%.02f",montant)+"$";
    }

    
    public static String calculMontantB(long soin, float montant){
        if (soin == 0){
            montant = (montant /100)*50;
            if (montant > 40)
                montant = 40;
        }
        else if (soin == 100){
            montant = (montant /100)*50;
            if (montant > 50)
                montant = 50;
        }
        else if (soin == 200 && montant > 70)
            montant = 70;
        else if (soin == 200)
            montant = montant;
        
        else if (soin >= 300 && soin <=399)
            montant = (montant /100)*50;
        else if (soin == 400)
            montant = 0;
        else if (soin == 500){
            montant = (montant /100)*50;
            if (montant > 50)
                montant = 50;
        }
        else if (soin == 600)
            montant = montant;
        else if (soin == 700)
            montant = (montant /100)*70;
        else
            errorDetected = true;
        
        return String.format("%.02f",montant)+"$";
    }
    
    public static String calculMontantC(long soin, float montant){
        if (soin != 100 && soin != 200 && !(soin >= 300 && soin >= 399) 
                && soin != 400 && soin != 500 && soin != 600 && soin != 700)
            errorDetected = true;
        return String.format("%.02f",(montant/100)*90)+"$";
    }
    
    public static String calculMontantD(long soin, float montant){
        if (soin == 0 && montant >= 85)
            montant = 85;
        else if (soin == 100 && montant >= 75)
            montant = 75;
        else if (soin == 200 && montant >=100)
            montant = 100;
        else if (soin >= 300 && soin <=399)
            montant = montant;
        else if (soin == 400 && montant > 65)
            montant = 65;
        else if (soin == 500 && montant > 75)
            montant = 75;
        else if (soin == 600 && montant > 200)
            montant = 100;
        else if (soin == 700 && montant > 90)
            montant = 90;
        else
            errorDetected = true;
        return String.format("%.02f",montant)+"$";
    }
    
    public static void saveOutputFile(JSONObject obj) throws IOException{
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
