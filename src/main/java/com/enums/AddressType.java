package com.enums;

/**
 * @author Atiqur Rahman
 * @since 07/09/2013 7:03 PM
 */
public enum AddressType {
    CURRENT("RESID", "Current"), PREVIOUS("PREV", "Previous");

    private final String type;
    private final String naturalName;

    public String getType() {
        return type;
    }

    public String getNaturalName() {
        return naturalName;
    }

    AddressType(String type, String naturalName) {
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
