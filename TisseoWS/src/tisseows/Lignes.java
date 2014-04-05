/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tisseows;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Kairi Takahashi
 */
public class Lignes {
    private JSONObject obj;
    private int like;
    private int unlike;
    
    public Lignes(JSONObject _obj){
        this.obj = _obj;
    }
    
    public String getShortName(int index){
        return (String) ((JSONObject)((JSONArray)(obj).get("line")).get(index)).get("shortName");
    }

    public String getName(int index){
        return (String) ((JSONObject)((JSONArray)(obj).get("line")).get(index)).get("name");
    }
    
    public String getId(int index){
        return (String) ((JSONObject)((JSONArray)(obj).get("line")).get(index)).get("id");
    }
    
    public int getLike(){
        return like;
    }
    
    public int getUnlike(){
        return unlike;
    }
    
    public void setLike(int i) {
        like = i;
    }
    
    public void setUnlike(int i) {
        unlike = i;
    }
    
    public String toString(int index){
        return this.getShortName(index) +" - "+ this.getName(index);
    }

}
