/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tisseows;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import javax.swing.text.Document;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Kairi Takahashi
 */
public class DatabaseCouchDB {
    
    private URIBuilder urib;
    private String table = "tisseows/";
    
    public DatabaseCouchDB() {
        this.urib = new URIBuilder();
        urib.setScheme("http");
        urib.setHost("localhost:5984/"+table);
    }
   
    public void setIdTable(String id) {
        String s = "localhost:5984/tisseows/"+id;
        urib.setHost(s);
    }
    
    public void resetURIB() {
        urib.setHost("localhost:5984/tisseows/");
    }
    
     public boolean tableAlreadyExist() throws URISyntaxException, IOException {
        if (request().contains("not_found"))
            return false;
        return true;
    }
    
    public boolean idAlreadyExist(String id) throws URISyntaxException, IOException {
        setIdTable(id);
        if (request().contains("not_found"))
            return false;
        return true;
    }
    
    public String request() throws URISyntaxException, IOException {
        URI uri = urib.build();
        
        String s = ""; 
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        try (CloseableHttpResponse response = httpclient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                s = EntityUtils.toString(entity);
            }
        } 
        resetURIB();
        return s;
    }

    public void addTable() throws URISyntaxException, UnsupportedEncodingException, IOException {
       setIdTable("localhost:5984/");
       putRequest(table);
    }
    
    protected void putRequest(String content) throws URISyntaxException, UnsupportedEncodingException, IOException{
        URI uri = urib.build();
       
        //.getBytes("ISO-8859-1"), Charset.forName("UTF-8")
        HttpPut httpput = new HttpPut(uri);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        StringEntity se = new StringEntity(content);
        httpput.setEntity(se);
        CloseableHttpResponse response = httpclient.execute(httpput);
        resetURIB();
    }
    
    public void addIdToTable(Lignes l, int index) throws URISyntaxException, UnsupportedEncodingException, IOException { 
        String json = "{"
        + " \"name\":\""+l.getName(index)+"\", "
        + "\"shortname\":\""+l.getShortName(index)+"\", "
        + "\"like\":\"0\", \"unlike\":\"0\" }";
        
        String jsonWithISO = new String(json.getBytes("ISO-8859-1"), Charset.forName("UTF-8"));
        
        setIdTable(l.getId(index));
        putRequest(jsonWithISO);
    }
    
    public void modifyToTable(Lignes l, int index, int like, int unlike) throws URISyntaxException, UnsupportedEncodingException, IOException, ParseException { 
        String json = "{"
        + " \"_rev\":\""+getRev(l.getId(index))+"\", "        
        + " \"name\":\""+l.getName(index)+"\", "
        + "\"shortname\":\""+l.getShortName(index)+"\", "
        + "\"like\":\""+like+"\", \"unlike\":\""+unlike+"\" }";
        
        setIdTable(l.getId(index));
        putRequest(json);
    }

    protected JSONObject recupArray(String id) throws ParseException, URISyntaxException, IOException {
        setIdTable(id);
        JSONParser parser = new JSONParser();
         Object obj = parser.parse(request());
        JSONObject array = (JSONObject)obj;
        return array;
    }
    
    public String getRev(String id) throws URISyntaxException, IOException, ParseException {
        return (String)recupArray(id).get("_rev");
    }
    
    public String getLike(String id) throws URISyntaxException, IOException, ParseException {
        return (String)recupArray(id).get("like");
    }
    
     public String getUnlike(String id) throws URISyntaxException, IOException, ParseException {
        return (String)recupArray(id).get("unlike");
    }
}
