package timesheet;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class TimeSheet {

    public static void main(String[] args) {
        args = new String[2];
    	args[0] = "..\\test.json";
        args[1] = "..\\output.json";
        
        // Validation du nombre d'arguments.
        if(args.length < 2) {
            System.err.println("Au moins deux arguments sont nÃ©cessaires.");
        }
        // Lecture du fichier.
        String output = "";
        try {
           output = new Scanner(new File(args[0])).useDelimiter("\\Z").next(); 
        } catch (Exception e) {
            System.err.println("Erreur dans la lecture du fichier.");
            System.err.println(e.getMessage());
        }
        
        
        JSONObject employeJSON;
        try {
            employeJSON = (JSONObject)JSONSerializer.toJSON(output);
            Employe employe = new Employe(employeJSON);
            validateTimeSheet(employe);
            //System.out.println(employe);
        } catch (Exception e){
            System.err.println("Fichier JSON invalide.");
        }
        
    }
    
    public static boolean validateTimeSheet(Employe employe)
    {
    	double workedHours = employe.getWorkedHours();
    	double workedHoursTele = employe.getWorkedHoursTele();
    	boolean correct = true;
    	
    	if(employe.isAdministration())
    	{
    		if((workedHours-workedHoursTele) < 36)
    		{
    			correct = false;
    			System.out.println("L'employé n'a pas travaillé le nombre d'heures minimal.");
    		}
    		
    		if(workedHoursTele > 10)
    		{
    			correct = false;
    			System.out.println("L'employé a dépassé le nombre d'heures de télétravail permis.");
    		}
    		
    		if(!checkDays(employe,4))
        	{
        		correct = false;
        	}
    		
    	}
    	else
    	{
    		if((workedHours-workedHoursTele) < 38)
    		{
    			correct = false;
    			System.out.println("L'employé n'a pas travaillé le nombre d'heures minimal.");
    		}
    		
    		if(!checkDays(employe,6))
        	{
        		correct = false;
        	}
    	}
    	
    	if(workedHours > 43)
    	{
    		correct = false;
    		System.out.println("L'employé a dépassé le nombre d'heures de télétravail permis.");
    	}
    	
    	return correct;
    }
    
    public static boolean checkDays(Employe employe, int minHours)
    {
    	boolean retVal = true;
    	
    	ArrayList<Jour> semaine = employe.getSemaine();
    	for(int i = 0; i < 5; i++)
    	{
    		if(semaine.get(i).getWorkedMinutes() < minHours)
    		{
    			retVal = false;
    			break;
    		}
    	}
    	
    	return retVal;
    }
}
