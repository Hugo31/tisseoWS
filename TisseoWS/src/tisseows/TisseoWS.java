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
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     * @throws org.json.simple.parser.ParseException
     */
    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        
        Request r = new Request();
        JSONParser parser = new JSONParser();
        
        r.setPathURIB("/placesList");
        r.addParamURIB("term", "Univ. Paul Sabatier (Ut3) (TOULOUSE)");
        r.addParamURIB("displayOnlyStopAreas", "1");
        
        Object obj = parser.parse(r.request());
        JSONObject array = (JSONObject)obj;
        System.out.println(r.request());
        //http://pt.data.tisseo.fr/stopPointsList?stopAreaId=1970329131943016&network=Tisséo
        r.resetURIB();
        r.setPathURIB("/stopPointsList");
        r.addParamURIB("stopAreaId", (String) ((JSONObject)((JSONArray)((JSONObject)array.get("placesList")).get("place")).get(0)).get("id"));
        r.addParamURIB("network", "Tisséo");
        System.out.println(r.request());
        
        obj = parser.parse(r.request());
        array = (JSONObject)obj;
        int nbStops = ((JSONArray)((JSONObject)array.get("physicalStops")).get("physicalStop")).size();
        int nbDeparts;
        Object obj2;
        JSONObject array2;
        String dateTime, shortName, destinationName;
        for(int i = 0 ; i < nbStops ; i++){
            r.resetURIB();
            r.setPathURIB("/departureBoard");
            r.addParamURIB("stopPointId", (String) ((JSONObject)((JSONArray)((JSONObject)array.get("physicalStops")).get("physicalStop")).get(i)).get("id"));
            obj2 = parser.parse(r.request());
            array2 = (JSONObject)obj2;
            
            nbDeparts = ((JSONArray)((JSONObject)array2.get("departures")).get("departure")).size();
            if(nbDeparts>1){
                System.out.println(((JSONObject)((JSONObject)array2.get("departures")).get("stop")).get("name"));
                for(int j = 0 ; j < nbDeparts-1 ; j++){
                    dateTime = (String) ((JSONObject)((JSONArray)((JSONObject)array2.get("departures")).get("departure")).get(j)).get("dateTime");
                    shortName = (String) ((JSONObject)((JSONObject)((JSONArray)((JSONObject)array2.get("departures")).get("departure")).get(j)).get("line")).get("shortName");
                    destinationName = (String) ((JSONObject)((JSONArray)((JSONObject)((JSONArray)((JSONObject)array2.get("departures")).get("departure")).get(j)).get("destination")).get(0)).get("name");
                    System.out.println("    " + shortName + " : " + dateTime + " - Destination: " + destinationName);
                }
            }
        }
        
    }
    
}
