package com.enums;

/**
 * @author Atiqur Rahman
 * @since 11/06/2013 7:03 PM
 */
public enum NameType {
    PRIMARY("PRIM", "Primary"), ALIAS("ALIAS", "Alias"), MARRIAGE("MAIDN", "Maiden"), PREVIOUS("PREVS", "Previous");

    private final String type;
    private final String naturalName;

    public String getType() {
        return type;
    }

    public String getNaturalName() {
        return naturalName;
    }

    NameType(String type, String naturalName) {
        this.type = type;
        this.naturalName = naturalName;
    }

    public static String getNaturalNameByType(String type) {
        for (NameType nt : NameType.values()) {
            if (nt.getType().equals(type)) {
                return nt.getNaturalName();
            }
        }
        return null;
    }
}
