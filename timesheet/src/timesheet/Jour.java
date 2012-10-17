package timesheet;

import java.util.ArrayList;
import net.sf.json.*;

public class Jour {
    private ArrayList<Projet> projets;
    private int workedMinutes;
    private int workedMinutesTele;
    
    public Jour(JSONArray jsonArray) {
    	Projet currentProjet;
        projets = new ArrayList<Projet>();
        
        for (int i = 0; i < jsonArray.size(); i++) {
        	currentProjet = new Projet((JSONObject)jsonArray.get(i)); 
            projets.add(currentProjet);
            workedMinutes += currentProjet.getMinutes();
            if(currentProjet.isTeletravail())
            {
            	workedMinutesTele += currentProjet.getMinutes();
            }
        }
    }
    
    @Override
    public String toString() {
        String resultat = "";
        for (Projet projet : projets) {
            resultat += "\n" + projet.toString();
        }
        return resultat;
    }

    public int getWorkedMinutes()
    {
    	return workedMinutes;
    }
    
    public int getWorkedMinutesTele()
    {
    	return workedMinutesTele;
    }
}
