package com.sme;

import com.domain.Address;
import com.domain.ApplicantNames;
import com.domain.SmePortalCheck;
import com.enums.*;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yashar on 9/06/2016.
 */
public class SmePoliceCheck {
    public static void main(String [ ] args) throws IOException, JSONException {

        // This API is for SME
        // After creating Police Check you should Upload documents and then submit the police check
        // 1-Create Police Check
        // 2-Upload Documents for Police Check ID
        // 3-Submit Police Check to Intercheck

        final  String apiEndPoint =  "https://secure.policecheckexpress.com.au/pce/api/portalCheckSme/new";
        final  String apiToken =  "secure token";
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(apiEndPoint);

            //filling Portal Check with sample Data

            SmePortalCheck smePortalCheck=fillSampleData();
            String parameters=fillParameters(smePortalCheck,apiToken);
            StringEntity input = new StringEntity(parameters);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
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

    private static SmePortalCheck fillSampleData() {
        SmePortalCheck smePortalCheck=new SmePortalCheck();
        smePortalCheck.setDepartment("sydney");
        smePortalCheck.setCheckType(CheckType.STANDARD.getId());
        smePortalCheck.setPurchaseOrder("123456");
        smePortalCheck.setEmpId("123");

        ///////// Filling Names
        List<ApplicantNames> applicantNames=new ArrayList<ApplicantNames>();
        ApplicantNames name1=new ApplicantNames();
        name1.setType(NameType.PRIMARY.getType());
        name1.setFirstGivenName("Yashar");
        name1.setOtherGivenNames("");
        name1.setSingleNameOnly(true);
        name1.setSurname("Seresht");

        ApplicantNames name2=new ApplicantNames();
        name2.setType(NameType.PREVIOUS.getType());
        name2.setFirstGivenName("Jim");
        name2.setOtherGivenNames("");
        name2.setSingleNameOnly(true);
        name2.setSurname("Seresht");

        applicantNames.add(name1);
        applicantNames.add(name2);
        smePortalCheck.setNames(applicantNames);

        ////////////
        smePortalCheck.setGender(Gender.MALE.getType());
        smePortalCheck.setBirthDay(1);
        smePortalCheck.setBirthMonth(5);
        smePortalCheck.setBirthYear(1981);
        smePortalCheck.setCountry(CountryCode.AU.getAlpha3());
        smePortalCheck.setPobStateTerritory(State.Victoria.getType());
        smePortalCheck.setPobSubTown("South Melbourne");
        smePortalCheck.setPobStateTerritoryOther("");

        /////////////////// Filling Addresses
        List<Address> addresses=new ArrayList<Address>();
        Address address1=new Address();
        address1.setCountry(CountryCode.AU.getAlpha3());
        address1.setType(AddressType.CURRENT.getType());
        address1.setStreetNo("56");
        address1.setStreetName("Smith Street");
        address1.setStateTerritory(State.Victoria.getType());
        address1.setSuburb("South Melbourne");
        address1.setDayFrom(1);
        address1.setMonthFrom(5);
        address1.setYearFrom(2015);
        address1.setDayTo(1);
        address1.setMonthTo(5);
        address1.setYearTo(2016);
        address1.setPostCode("3205");
        address1.setStateTerritoryOther("");

        Address address2=new Address();
        address2.setCountry(CountryCode.AU.getAlpha3());
        address2.setType(AddressType.PREVIOUS.getType());
        address2.setStreetNo("58");
        address2.setStreetName("Smith Street");
        address2.setStateTerritory(State.Victoria.getType());
        address2.setSuburb("South Melbourne");
        address2.setDayFrom(1);
        address2.setMonthFrom(5);
        address2.setYearFrom(2014);
        address2.setDayTo(1);
        address2.setMonthTo(5);
        address2.setYearTo(2015);
        address2.setStateTerritoryOther("");
        address2.setPostCode("3005");

        addresses.add(address1);
        addresses.add(address2);

        smePortalCheck.setAddresses(addresses);
        //////////////////////////////
        smePortalCheck.setPhoneMobile("0432024967");
        smePortalCheck.setPhoneWork("038199094");
        smePortalCheck.setPhoneHome("038199092");

        ////////////////////////////
        smePortalCheck.setOzDriverLicense("123");
        smePortalCheck.setOzDlAgency(State.Australian_Capital_Territory.getType());
        smePortalCheck.setFirearmsLicense("456");
        smePortalCheck.setFlAgency(State.Australian_Capital_Territory.getType());
        ///////////////////////////
        smePortalCheck.setChildVul(false);
        smePortalCheck.setPurpose("Nurse in Hospital");
        smePortalCheck.setUserFullName("Sunny Palm");
        //////////////////////
        smePortalCheck.setNote("Note for Crim Track");

        smePortalCheck.setPriority(false);
        smePortalCheck.setReason("Urgent Reason");

        return smePortalCheck;
    }

    private static String fillParameters(SmePortalCheck sme,String token) {
        String nameString="[";
        String addressString="[";
        for(ApplicantNames name:sme.getNames()){
            nameString=nameString+"{\"surname\":\""+name.getSurname()+"\",\"firstGivenName\":\""+name.getFirstGivenName()+"\"," +
                    "\"otherGivenNames\":\""+name.getOtherGivenNames()+"\",\"singleNameOnly\":\""+name.getSingleNameOnly()+"\",\"type\":\""+name.getType()+"\"},";
        }
        nameString = nameString.substring(0, nameString.length()-1);
        nameString=nameString+"]";
        for(Address address:sme.getAddresses()){
            addressString=addressString+"{\"streetNo\":\""+address.getStreetNo()+"\",\"streetName\":\""+address.getStreetName()+"\",\"suburb\":\""+address.getSuburb()+"\"," +
                    "\"stateTerritory\":\""+address.getStateTerritory()+"\",\"stateTerritoryOther\":\""+address.getStateTerritoryOther()+"\",\"postCode\":\""+address.getPostCode()+"\"," +
                    "\"country\":\""+address.getCountry()+"\",\"dayFrom\":\""+address.getDayFrom()+"\",\"monthFrom\":\""+address.getMonthFrom() + "\"," +
                    "\"yearFrom\":\""+address.getYearFrom()+"\",\"dayTo\":\""+address.getDayTo()+"\",\"monthTo\":\""+address.getMonthTo()+"\",\"yearTo\":\""+address.getYearTo()+"\",\"type\":\"" + address.getType()+"\"},";
        }
        addressString = addressString.substring(0, addressString.length()-1);
        addressString=addressString+"]";

        return "{\"token\":\""+token+"\",\"checkType\":\""+sme.getCheckType()+"\",\"department\":\""+sme.getDepartment()+"\",\"empId\":\""+sme.getEmpId()+"\"," +
                "\"purchaseOrder\":\""+sme.getPurchaseOrder()+"\",\"names\":"+nameString+",\"gender\":\""+sme.getGender()+"\"," +
                "\"birthDay\":\""+sme.getBirthDay()+"\",\"birthMonth\":\""+sme.getBirthMonth()+"\",\"birthYear\":\""+sme.getBirthYear()+"\"," +
                "\"pobSubTown\":\""+sme.getPobSubTown()+"\",\"pobStateTerritory\":\""+sme.getPobStateTerritory()+"\"," +
                "\"country\":\""+sme.getCountry()+"\",\"pobStateTerritoryOther\":\""+sme.getPobStateTerritoryOther()+"\",\"addresses\":"+addressString+"," +
                "\"phoneHome\":\""+sme.getPhoneHome()+"\",\"phoneWork\":\""+sme.getPhoneWork()+"\",\"phoneMobile\":\""+sme.getPhoneMobile()+"\"," +
                "\"ozDriverLicense\":\""+sme.getOzDriverLicense()+"\",\"ozDlAgency\":\""+sme.getOzDlAgency()+"\",\"firearmsLicense\":\""+sme.getFirearmsLicense()+"\",\"flAgency\":\""+sme.getFlAgency()+"\"," +
                "\"childVul\":\""+sme.isChildVul()+"\",\"purpose\":\""+sme.getPurpose()+"\",\"userFullName\":\""+sme.getUserFullName()+"\"," +
                "\"note\":\""+sme.getNote()+"\",\"priority\":\""+sme.isPriority()+"\"," +
                "\"reason\":\""+sme.getReason()+"\"}";
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
