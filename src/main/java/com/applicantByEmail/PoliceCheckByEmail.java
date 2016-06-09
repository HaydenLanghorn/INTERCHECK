package com.applicantByEmail;

import com.domain.ApplicantPoliceCheck;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by yashar on 8/06/2016.
 */
public class PoliceCheckByEmail {
    public static void main(String [ ] args)
    {
        final  String apiEndPoint =  "https://secure.policecheckexpress.com.au/pce/api/policeCheck";
        final  String apiToken =  "secure_token";
        final  String applicantEmail ="applicantEmail@email.com";

        try {
            ApplicantPoliceCheck applicantPoliceCheck = readJsonFromUrl(apiEndPoint,apiToken,applicantEmail);
            if(applicantPoliceCheck!=null) {
                if (applicantPoliceCheck.isError()) {

                    System.out.println("++++++++++++++++++++++++++");
                    System.out.println("Error  = " + applicantPoliceCheck.getErrorMessage());
                    System.out.println("++++++++++++++++++++++++++");
                } else {
                    System.out.println("**************************");
                    System.out.println("Reference No = " + applicantPoliceCheck.getId());
                    System.out.println("First Name = " + applicantPoliceCheck.getFirstName());
                    System.out.println("Last Name = " + applicantPoliceCheck.getLastName());
                    System.out.println("Email = " + applicantPoliceCheck.getEmail());
                    System.out.println("Purpose = " + applicantPoliceCheck.getPurpose());
                    System.out.println("Status = " + applicantPoliceCheck.getStatus());
                    System.out.println("**************************");

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ApplicantPoliceCheck readJsonFromUrl(String api,String token,String email) throws IOException {

        final String  url= api+"?token="+token+"&applicant="+email;
        InputStream is = new URL(url).openStream();
        JSONArray json;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            json = new JSONArray("["+jsonText+"]");
            JSONObject obj=(JSONObject)json.get(0);
            ApplicantPoliceCheck applicantPoliceCheck= fillApplicantInfo(obj);
           return applicantPoliceCheck;
        } catch (JSONException e) {
            return null;
        }
        catch (IOException e) {
            return null;
        }

    }

    private static ApplicantPoliceCheck fillApplicantInfo(JSONObject obj) throws JSONException {
        ApplicantPoliceCheck applicantPoliceCheck=new ApplicantPoliceCheck();
        if(!(Boolean)obj.get("error")) {
            applicantPoliceCheck.setId((Integer) obj.get("id"));
            applicantPoliceCheck.setEmail((String) obj.get("email"));
            applicantPoliceCheck.setPurpose((String) obj.get("purpose"));
            applicantPoliceCheck.setFirstName((String) obj.get("firstName"));
            applicantPoliceCheck.setLastName((String) obj.get("lastName"));
            applicantPoliceCheck.setStatus((String) obj.get("status"));
            applicantPoliceCheck.setError(false);
        }else {
            applicantPoliceCheck.setError(true);
            applicantPoliceCheck.setErrorMessage((String) obj.get("errorMessage"));
        }
        return applicantPoliceCheck;
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
