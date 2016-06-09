package com.applicantsReport;

import com.domain.ApplicantInformation;
import com.domain.ReportResults;
import com.enums.ReportStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yashar on 8/06/2016.
 */
public class PoliceCheckReport {

    public static void main(String [ ] args) throws IOException {

        final  String apiEndPoint =  "https://secure.policecheckexpress.com.au/pce/api/policeCheck/report";
        final  String apiToken =  "secure Token";
        final  int status = ReportStatus.RESULT_IN.getId();
        // You Can see all the Status In ReportStatus Enum

        try {
            ReportResults reportResults = readJsonFromUrl(apiEndPoint,apiToken,status);
            if(reportResults.isError()){
                System.out.println("++++++++++++++++++++++++++");
                System.out.println("Error  = " + reportResults.getErrorMessage());
                System.out.println("++++++++++++++++++++++++++");

            }else {
                System.out.println("++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Police Check Count = " + reportResults.getApplicants().size());
                System.out.println("++++++++++++++++++++++++++++++++++++++++");
                for (ApplicantInformation applicant : reportResults.getApplicants()) {
                    System.out.println("***********************************");
                    System.out.println(" Reference No = " + applicant.getReferenceNo());
                    System.out.println(" FullName = " + applicant.getName());
                    System.out.println(" Department = " + applicant.getDepartment());
                    System.out.println(" Status = " + applicant.getStatusDescription());
                    System.out.println(" Check Method = " + applicant.getCheckMethodDescription());
                    System.out.println(" Purchase Order = " + applicant.getPurchaseOrder());
                    System.out.println(" Paid Invited Or Not = " + applicant.isPaidInvite());
                    System.out.println(" DCO = " + applicant.getDco());
                    System.out.println(" Result Date = " + applicant.getResultDate());
                    System.out.println(" Result Destroyed = " + applicant.isResultDestroyed());
                    System.out.println(" Employee ID = " + applicant.getEmpId());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ReportResults readJsonFromUrl(String api,String token,int status) throws IOException {

        final String  url= api+"?token="+token+"&status="+status;
        InputStream is = new URL(url).openStream();
        JSONArray json;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            json = new JSONArray("["+jsonText+"]");
            JSONObject obj=(JSONObject)json.get(0);
            ReportResults reportResults = fillApplicantInfo(obj);
            return reportResults;
        } catch (JSONException e) {
            return null;
        }
        catch (IOException e) {
            return null;
        }

    }

    private static ReportResults fillApplicantInfo(JSONObject obj) throws JSONException {
        ReportResults reportResults =new ReportResults();
        List<ApplicantInformation> applicantLists=new ArrayList<ApplicantInformation>();
        if(!(Boolean)obj.get("error")) {
            JSONArray applicants= (JSONArray) obj.get("applicants");
            for (int i=0;i<applicants.length();i++){
                ApplicantInformation applicant=fillApplicant((JSONObject) applicants.get(i));
                applicantLists.add(applicant);
            }
            reportResults.setApplicants(applicantLists);
            reportResults.setError(false);

        }else {
            reportResults.setError(true);
            reportResults.setErrorMessage((String) obj.get("errorMessage"));
        }
        return reportResults;
    }

    private static ApplicantInformation fillApplicant(JSONObject applicantObj) throws JSONException {
        ApplicantInformation applicantInformation =new ApplicantInformation();
        applicantInformation.setReferenceNo((Integer) applicantObj.get("referenceNo"));
        applicantInformation.setName((String) applicantObj.get("name"));
        applicantInformation.setStatusDescription((String) applicantObj.get("statusDescription"));
        applicantInformation.setCheckMethodDescription((String) applicantObj.get("checkMethodDescription"));
        applicantInformation.setPaidInvite((Boolean) applicantObj.get("paidInvite"));

        try {
            applicantInformation.setDepartment((String) applicantObj.get("department"));
        }
        catch (Exception e){
            applicantInformation.setDepartment(null);
        }
       try {
           applicantInformation.setDco((String) applicantObj.get("dco"));
       }
       catch (Exception e){
           applicantInformation.setDco(null);
       }
        try {
            applicantInformation.setResultDestroyed((Boolean) applicantObj.get("resultDestroyed"));
        }
        catch (Exception e){
            applicantInformation.setResultDestroyed(false);
        }
        try {
            applicantInformation.setEmpId((String) applicantObj.get("empId"));
        }
        catch (Exception e){
            applicantInformation.setEmpId(null);
        }

        try {
            applicantInformation.setPurchaseOrder((String) applicantObj.get("purchaseOrder"));
        }
        catch (Exception e){
            applicantInformation.setPurchaseOrder(null);
        }
        try {
            applicantInformation.setResultDate((String) applicantObj.get("resultDate"));
        }
        catch (Exception e){
            applicantInformation.setResultDate(null);
        }




        return applicantInformation;
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
