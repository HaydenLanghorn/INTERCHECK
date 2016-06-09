package com.domain;

import java.io.Serializable;

/**
 * Created by yashar on 3/06/2016.
 */
public class ApplicantNames implements Serializable {

    private String surname;
    private String firstGivenName;
    private String otherGivenNames;
    private Boolean singleNameOnly;
    private String type;


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstGivenName() {
        return firstGivenName;
    }

    public void setFirstGivenName(String firstGivenName) {
        this.firstGivenName = firstGivenName;
    }

    public String getOtherGivenNames() {
        return otherGivenNames;
    }

    public void setOtherGivenNames(String otherGivenNames) {
        this.otherGivenNames = otherGivenNames;
    }


    public Boolean getSingleNameOnly() {
        return singleNameOnly;
    }

    public void setSingleNameOnly(Boolean singleNameOnly) {
        this.singleNameOnly = singleNameOnly;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
