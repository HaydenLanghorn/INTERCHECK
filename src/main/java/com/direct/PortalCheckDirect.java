package com.direct;

import com.domain.Address;
import com.domain.ApplicantNames;
import com.domain.DirectPortalCheck;
import com.domain.Invite;
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
public class PortalCheckDirect {
    public static void main(String [ ] args) throws IOException, JSONException {

        //This API is for Direct Business
        final  String apiEndPoint =  "https://secure.policecheckexpress.com.au/pce/api/portalCheckDirect/new";
        final  String apiToken =  "secure Token";
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(apiEndPoint);

            //filling Portal Check with Sample Data
            DirectPortalCheck directPortalCheck=fillSampleData();
            String parameters=fillParameters(directPortalCheck,apiToken);
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

    private static DirectPortalCheck fillSampleData() {
        DirectPortalCheck directPortalCheck=new DirectPortalCheck();
        directPortalCheck.setDepartment("sydney");
        directPortalCheck.setCheckType(CheckType.STANDARD.getId());
        directPortalCheck.setPurchaseOrder("123456");
        directPortalCheck.setEmpId("123");

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
        directPortalCheck.setNames(applicantNames);

        ////////////
        directPortalCheck.setGender(Gender.MALE.getType());
        directPortalCheck.setBirthDay(1);
        directPortalCheck.setBirthMonth(5);
        directPortalCheck.setBirthYear(1981);
        directPortalCheck.setCountry(CountryCode.AU.getAlpha3());
        directPortalCheck.setPobStateTerritory(State.Victoria.getType());
        directPortalCheck.setPobSubTown("South Melbourne");
        directPortalCheck.setPobStateTerritoryOther("");

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

        directPortalCheck.setAddresses(addresses);
        //////////////////////////////
        directPortalCheck.setPhoneMobile("0432024967");
        directPortalCheck.setPhoneWork("038199094");
        directPortalCheck.setPhoneHome("038199092");

        ////////////////////////////
        directPortalCheck.setOzDriverLicense("123");
        directPortalCheck.setOzDlAgency(State.Australian_Capital_Territory.getType());
        directPortalCheck.setFirearmsLicense("456");
        directPortalCheck.setFlAgency(State.Australian_Capital_Territory.getType());
        ///////////////////////////
        directPortalCheck.setChildVul(false);
        directPortalCheck.setPurpose("Nurse in Hospital");
        directPortalCheck.setUserFullName("Sunny Palm");
        //////////////////////
        directPortalCheck.setNote("Note for Crim Track");
        directPortalCheck.setSightedDl(true);
        directPortalCheck.setSightedFl(true);
        directPortalCheck.setSightedPassport(true);
        directPortalCheck.setPriority(false);
        directPortalCheck.setReason("Urgent Reason");
        directPortalCheck.setInformedConsent(true);
        directPortalCheck.setIdentityConfirmed(true);
        return directPortalCheck;
    }

    private static String fillParameters(DirectPortalCheck direct,String token) {
        String nameString="[";
        String addressString="[";
        for(ApplicantNames name:direct.getNames()){
            nameString=nameString+"{\"surname\":\""+name.getSurname()+"\",\"firstGivenName\":\""+name.getFirstGivenName()+"\"," +
                    "\"otherGivenNames\":\""+name.getOtherGivenNames()+"\",\"singleNameOnly\":\""+name.getSingleNameOnly()+"\",\"type\":\""+name.getType()+"\"},";
        }
        nameString = nameString.substring(0, nameString.length()-1);
        nameString=nameString+"]";
        for(Address address:direct.getAddresses()){
            addressString=addressString+"{\"streetNo\":\""+address.getStreetNo()+"\",\"streetName\":\""+address.getStreetName()+"\",\"suburb\":\""+address.getSuburb()+"\"," +
                    "\"stateTerritory\":\""+address.getStateTerritory()+"\",\"stateTerritoryOther\":\""+address.getStateTerritoryOther()+"\",\"postCode\":\""+address.getPostCode()+"\"," +
                    "\"country\":\""+address.getCountry()+"\",\"dayFrom\":\""+address.getDayFrom()+"\",\"monthFrom\":\""+address.getMonthFrom() + "\"," +
                    "\"yearFrom\":\""+address.getYearFrom()+"\",\"dayTo\":\""+address.getDayTo()+"\",\"monthTo\":\""+address.getMonthTo()+"\",\"yearTo\":\""+address.getYearTo()+"\",\"type\":\"" + address.getType()+"\"},";
        }
        addressString = addressString.substring(0, addressString.length()-1);
        addressString=addressString+"]";

        return "{\"token\":\""+token+"\",\"checkType\":\""+direct.getCheckType()+"\",\"department\":\""+direct.getDepartment()+"\",\"empId\":\""+direct.getEmpId()+"\"," +
                "\"purchaseOrder\":\""+direct.getPurchaseOrder()+"\",\"names\":"+nameString+",\"gender\":\""+direct.getGender()+"\"," +
                "\"birthDay\":\""+direct.getBirthDay()+"\",\"birthMonth\":\""+direct.getBirthMonth()+"\",\"birthYear\":\""+direct.getBirthYear()+"\"," +
                "\"pobSubTown\":\""+direct.getPobSubTown()+"\",\"pobStateTerritory\":\""+direct.getPobStateTerritory()+"\"," +
                "\"country\":\""+direct.getCountry()+"\",\"pobStateTerritoryOther\":\""+direct.getPobStateTerritoryOther()+"\",\"addresses\":"+addressString+"," +
                "\"phoneHome\":\""+direct.getPhoneHome()+"\",\"phoneWork\":\""+direct.getPhoneWork()+"\",\"phoneMobile\":\""+direct.getPhoneMobile()+"\"," +
                "\"ozDriverLicense\":\""+direct.getOzDriverLicense()+"\",\"ozDlAgency\":\""+direct.getOzDlAgency()+"\",\"firearmsLicense\":\""+direct.getFirearmsLicense()+"\",\"flAgency\":\""+direct.getFlAgency()+"\"," +
                "\"childVul\":\""+direct.isChildVul()+"\",\"purpose\":\""+direct.getPurpose()+"\",\"userFullName\":\""+direct.getUserFullName()+"\"," +
                "\"note\":\""+direct.getNote()+"\",\"sightedDl\":\""+direct.isSightedDl()+"\",\"sightedFl\":\""+direct.isSightedFl()+"\",\"sightedPassport\":\""+direct.isSightedPassport()+"\",\"priority\":\""+direct.isPriority()+"\"," +
                "\"reason\":\""+direct.getReason()+"\",\"informedConsent\":\""+direct.isInformedConsent()+"\",\"identityConfirmed\":\""+direct.isIdentityConfirmed()+"\"}";
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
