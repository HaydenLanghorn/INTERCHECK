package com.enums;

/**
 * Created by yashar on 9/06/2016.
 */
public enum State {
    Australian_Capital_Territory("ACT", "Australian Capital Territory"), New_South_Wales("NSW", "New South Wales"), Northern_Territory("NT", "Northern Territory")
    ,Queensland("QLD", "Queensland"), South_Australia("SA", "South Australia"), Tasmania("TAS", "Tasmania"), Victoria("VIC", "Victoria"), Western_Australia("WA", "Western Australia")
    ;

    private final String type;
    private final String naturalName;

    public String getType() {
        return type;
    }

    public String getNaturalName() {
        return naturalName;
    }
    State(String type, String naturalName) {
        this.type = type;
        this.naturalName = naturalName;
    }
    public static String getNaturalNameByType(String type) {
        for (AddressType nt : AddressType.values()) {
            if (nt.getType().equals(type)) {
                return nt.getNaturalName();
            }
        }
        return null;
    }
}
