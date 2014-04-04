/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tisseows;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Hugo
 */
public class RequestTisseo {
    
    private URIBuilder urib;
    
    public RequestTisseo(){
        this.urib = new URIBuilder()
        .setScheme("http")
        .setHost("pt.data.tisseo.fr");
    }
    
    public String request() throws IOException, URISyntaxException{
        this.urib.setParameter("key", "a03561f2fd10641d96fb8188d209414d8");
        this.urib.setParameter("format", "json");
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
        return s;
    }
    
    public void resetURIB(){
        this.urib = new URIBuilder()
        .setScheme("http")
        .setHost("pt.data.tisseo.fr");
    }
    
    public void setPathURIB(String path){
        this.urib.setPath(path);
    }
    
    public void addParamURIB(String param, String value){
        this.urib.setParameter(param, value);
    }
}
