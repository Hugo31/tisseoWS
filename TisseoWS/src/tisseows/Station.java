/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tisseows;

import org.json.simple.JSONObject;

/**
 *
 * @author Hugo
 */
public class Station {
    
    private JSONObject obj;
    
    public Station(JSONObject _obj){
        this.obj = _obj;
    }
    
    public String getName(){
        return (String) obj.get("name");
    }
    
    public String getAdresse(){
        return (String) obj.get("address");
    }
    
    public String getStatut(){
        return (String) obj.get("status");
    }
    
    public long getBikeStands(){
        return (long) obj.get("available_bike_stands");
    }
        
    public long getBike(){
        return (long) obj.get("available_bikes");
    }
        
}
