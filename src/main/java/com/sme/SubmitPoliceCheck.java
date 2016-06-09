package com.sme;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by yashar on 9/06/2016.
 */
public class SubmitPoliceCheck {
    public static void main(String [ ] args) throws IOException, JSONException {


        final String apiEndPoint = "https://secure.policecheckexpress.com.au/pce/api/portalCheckSme/submit";
        final String apiToken = "secure token";
        final String policeCheckId = "30118";

        HttpClient client = new DefaultHttpClient() ;
        HttpPost postRequest = new HttpPost (apiEndPoint) ;
        try
        {

            MultipartEntity multiPartEntity = new MultipartEntity () ;
            StringBody id = new StringBody(policeCheckId);

            StringBody token = new StringBody(apiToken);
            multiPartEntity.addPart("id",id);
            multiPartEntity.addPart("token",token);

            //Set to request body
            postRequest.setEntity(multiPartEntity) ;

            //Send request
            HttpResponse response = client.execute(postRequest) ;

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String jsonText = readAll(br);
            JSONArray json = new JSONArray("["+jsonText+"]");
            JSONObject obj = (JSONObject)json.get(0);
            if(!(Boolean)obj.get("error")) {
                System.out.println(obj.get("message"));
                System.out.println("Police Check Id = "+obj.get("id"));
            }
            else{
                System.out.println("++++++++++++++++++++++++++");
                System.out.println("Error  = " + obj.get("message"));
                System.out.println("++++++++++++++++++++++++++");

            }

            client.getConnectionManager().shutdown();
        }
        catch (Exception ex)
        {
            ex.printStackTrace() ;
        }


    }

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
