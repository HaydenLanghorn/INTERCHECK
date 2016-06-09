package com.invitation;


import com.domain.Invite;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;

/**
 * Created by yashar on 8/06/2016.
 */
public class InviteApplicant {

    public static void main(String [ ] args) throws IOException, JSONException {

        //Creating Invitation
        final  String apiEndPoint =  "https://secure.policecheckexpress.com.au/pce/api/policeCheck/invite";
        final  String apiToken =  "Secure Token";
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(apiEndPoint);

            //filling Invitation with sample Data
            Invite invite=fillSampleData();

            String parameters=fillParameters(invite,apiToken);

            StringEntity input = new StringEntity(parameters);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);


            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String jsonText = readAll(br);
            JSONArray json = new JSONArray("["+jsonText+"]");
            JSONObject obj = (JSONObject)json.get(0);
            if(!(Boolean)obj.get("error")) {

                System.out.println(obj.get("message"));
                System.out.println("Invitation Id = "+obj.get("id"));
            }
            else{
                System.out.println("++++++++++++++++++++++++++");
                System.out.println("Error  = " + obj.get("message"));
                System.out.println("++++++++++++++++++++++++++");

            }

            httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private static Invite fillSampleData() {
        Invite invite=new Invite();
        invite.setCheckType(1);
        invite.setPurchaseOrder("123");
        invite.setPurpose("Trades Worker in Aged Care Facility");
        invite.setEmail("yashar.seresht@gmail.com");
        invite.setGivenName("Yashar");
        invite.setSurname("Seresht");
        invite.setDepartment("Sydney");
        invite.setPhone("0432024967");
        invite.setEmpId("147");
        invite.setReminderCount(3);
        invite.setChildVul(false);
        invite.setUserFullname("Sunny Palm");
        invite.setPaidInvite(1);
        return invite;

    }

    private static String fillParameters(Invite invite,String token) {
        return "{\"token\":\""+token+"\",\"checkType\":\""+invite.getCheckType()+"\",\"paidInvite\":\""+invite.getPaidInvite()+"\"," +
                "\"department\":\""+invite.getDepartment()+"\",\"email\":\""+invite.getEmail()+"\",\"surname\":\""+invite.getSurname()+"\"," +
                "\"givenName\":\""+invite.getGivenName()+"\",\"phone\":\""+invite.getPhone()+"\",\"empId\":\""+invite.getEmpId()+"\",\"reminderCount\":\""+invite.getReminderCount()+"\"," +
                "\"purchaseOrder\":\""+invite.getPurchaseOrder()+"\",\"purpose\":\""+invite.getPurpose()+"\",\"childVul\":\""+invite.getChildVul()+"\",\"userFullname\":\""+invite.getUserFullname()+"\"}";
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
