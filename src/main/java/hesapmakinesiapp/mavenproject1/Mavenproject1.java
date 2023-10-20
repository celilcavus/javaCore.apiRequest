/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package hesapmakinesiapp.mavenproject1;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author Celil
 */
public class Mavenproject1 {

     private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) {
        Mavenproject1 mav = new Mavenproject1();
        try {
            mav.HttpGetReq();
        } catch (Exception ex) {
            Logger.getLogger(Mavenproject1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void HttpGetReq() throws Exception {

        HttpGet request = new HttpGet("https://random.dog/woof.json");

        // add request headers
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                var model = new JSONObject(result);
                /*
                System.out.println(model.get(String.valueOf(new DogModel().fileSizeBytes)));
                System.out.println(model.get(new DogModel().url));
*/
                var s = model.get("url");
                System.out.println("IMAGE ===> ".concat(s.toString()));
            }

        }

    }
}
