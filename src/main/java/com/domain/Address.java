package com.domain;


/**
 * @author Atiqur Rahman
 * @since 13/06/2013 6:54 PM
 */

public class Address {



    private String streetNo;
    private String streetName;
    private String suburb;
    private String stateTerritory;
    private String stateTerritoryOther;
    private String postCode;
    private String country;
    private int dayFrom;
    private int monthFrom;
    private int yearFrom;
    private int dayTo;
    private int monthTo;
    private int yearTo;
    private String type;



    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getStateTerritory() {
        return stateTerritory;
    }

    public void setStateTerritory(String stateTerritory) {
        this.stateTerritory = stateTerritory;
    }

    public String getStateTerritoryOther() {
        return stateTerritoryOther;
    }

    public void setStateTerritoryOther(String stateTerritoryOther) {
        this.stateTerritoryOther = stateTerritoryOther;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(int dayFrom) {
        this.dayFrom = dayFrom;
    }

    public int getMonthFrom() {
        return monthFrom;
    }

    public void setMonthFrom(int monthFrom) {
        this.monthFrom = monthFrom;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getDayTo() {
        return dayTo;
    }

    public void setDayTo(int dayTo) {
        this.dayTo = dayTo;
    }

    public int getMonthTo() {
        return monthTo;
    }

    public void setMonthTo(int monthTo) {
        this.monthTo = monthTo;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
