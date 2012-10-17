package timesheet;

import net.sf.json.JSONObject;

public class Projet {
    public static final int CODE_TELETRAVAIL = 900;
    
    private int code;
    private int minutes;

    Projet(JSONObject objet) {
        code = objet.getInt("projet");
        minutes = objet.getInt("minutes");
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public int getCode()
    {
        return this.code;
    }
    
    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }
    
    public int getMinutes()
    {
        return this.minutes;
    }
    
    public boolean isTeletravail()
    {
        return this.code > CODE_TELETRAVAIL;
    }
    
    @Override
    public String toString() {
        String resultat = "";
        resultat += "code : " + code;
        resultat += "\nminutes : " + minutes;
        return resultat;
    }
}
