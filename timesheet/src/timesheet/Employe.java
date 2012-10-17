package timesheet;

import java.util.ArrayList;
import net.sf.json.*;

public class Employe {
    
    public static final int NO_ADMIN = 1000;
    private int numero;    
    private ArrayList<Jour> semaine;
    
    public Employe(int numero) {
        this.numero = numero;
    }
    
    public Employe(JSONObject json) {
        numero = json.getInt("numero_employe");
        
        semaine = new ArrayList<Jour>();
        
        JSONArray jour1 = json.getJSONArray("jour1");
        semaine.add(new Jour(jour1));
        
        JSONArray jour2 = json.getJSONArray("jour2");
        semaine.add(new Jour(jour2));
        
        JSONArray jour3 = json.getJSONArray("jour3");
        semaine.add(new Jour(jour3));
        
        JSONArray jour4 = json.getJSONArray("jour4");
        semaine.add(new Jour(jour4));
        
        JSONArray jour5 = json.getJSONArray("jour5");
        semaine.add(new Jour(jour5));
        
        JSONArray weekend1 = json.getJSONArray("weekend1");
        semaine.add(new Jour(weekend1));
        
        JSONArray weekend2 = json.getJSONArray("weekend2");
        semaine.add(new Jour(weekend2));
        
    }
    
    /**
    * La méthode publique isAdministration() permet de savoir si
    * l'employé fait parti de l'administration.
    * 
    * @return <code>true</code> si c'est un employé de l'administration
    * et <code>false</code> dans le cas contraire.
    */
    public boolean isAdministration()
    {
        return this.numero < NO_ADMIN;
    }
    
    public double getWorkedHours()
    {
    	double workedHours = 0;
    	for (Jour jour : semaine) {
    		workedHours += jour.getWorkedMinutes();
		}
    	return workedHours/60;
    }
    
    @Override
    public String toString() {
        String resultat = "Numéro d'employé : " + numero;
        for (Jour jour : semaine) {
            resultat += "\n" + jour.toString();
        }
        return resultat;
    }

	public double getWorkedHoursTele() {
    	double workedHoursTele = 0;
    	for (Jour jour : semaine) {
    		workedHoursTele += jour.getWorkedMinutesTele();
		}
    	return workedHoursTele/60;
	}
	
	public ArrayList<Jour> getSemaine()
	{
		return semaine;
	}
}
    