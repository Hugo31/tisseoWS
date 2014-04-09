/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tisseows;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Permet de stocker un départ au format JSon et effectuer des get dessus
 * @author Hugo
 */
public class Depart {
    
    private final JSONObject obj;
    
    public Depart(JSONObject _obj){
        this.obj = _obj;
    }

    public String getDateTime(int index){
        return (String) ((JSONObject)((JSONArray)((JSONObject)obj).get("departure")).get(index)).get("dateTime");
    }

    public String getShortName(int index){
        return (String) ((JSONObject)((JSONObject)((JSONArray)((JSONObject)obj).get("departure")).get(index)).get("line")).get("shortName");
    }

    public String getDestinationName(int index){
        return (String) ((JSONObject)((JSONArray)((JSONObject)((JSONArray)((JSONObject)obj).get("departure")).get(index)).get("destination")).get(0)).get("name");
    }
    
    public String toString(int index){
        return "Passage : " + this.getDateTime(index);
    }
    
    @Override
    public String toString(){
        return this.getShortName(0) + " - Destination " + this.getDestinationName(0);
    }
    
    public int nbDeparts(){
        return ((JSONArray) obj.get("departure")).size() - 1;
    }
    
}
