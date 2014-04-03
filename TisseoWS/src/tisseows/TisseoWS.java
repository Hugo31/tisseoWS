/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tisseows;

import java.io.IOException;
import java.net.URISyntaxException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Hugo
 */
public class TisseoWS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        
        Request r = new Request();
        //http://pt.data.tisseo.fr/placesList?term=cav
        r.setPathURIB("/placesList");
        r.addParamURIB("term", "Univ. Paul Sabatier (Ut3) (TOULOUSE)");
        r.addParamURIB("displayOnlyStopAreas", "1");
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(r.request());
        JSONObject array=(JSONObject)obj;
        //((JSONObject)((JSONArray)((JSONObject)array.get("placesList")).get("place")).get(0)).get("id");
        
        //http://pt.data.tisseo.fr/stopPointsList?stopAreaId=1970329131943016&network=Tisséo
        r.resetURIB();
        r.setPathURIB("/stopPointsList");
        r.addParamURIB("stopAreaId", (String) ((JSONObject)((JSONArray)((JSONObject)array.get("placesList")).get("place")).get(0)).get("id"));
        r.addParamURIB("network", "Tisséo");
        System.out.println(r.request());
        //http://pt.data.tisseo.fr/departureBoard?stopPointId=3377699720883436
        r.resetURIB();
        r.setPathURIB("/departureBoard");
        r.addParamURIB("stopPointId", "3377704015495640");
       
        
    }
    
}
