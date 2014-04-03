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
public class Request {
    
    public String request() throws IOException, URISyntaxException{
        URI uri = new URIBuilder()
        .setScheme("http")
        .setHost("pt.data.tisseo.fr")
        .setPath("/placesList")
        .setParameter("term", "Univ. Paul Sabatier (Ut3) (TOULOUSE)")
        .setParameter("key", "a03561f2fd10641d96fb8188d209414d8")
        .setParameter("displayOnlyStopAreas", "1")
        .build();
        //http://pt.data.tisseo.fr/placesList?term=cav&key=a03561f2fd10641d96fb8188d209414d8
        
        String s = ""; 
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                s = EntityUtils.toString(entity);
            }
        } finally {
            response.close();
        } 
        return s;
    }
}
