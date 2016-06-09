package com.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Atiqur Rahman
 * @since 11/06/2013 7:07 PM
 */
public enum Gender {
    MALE("M", "Male"), FEMALE("F", "Female"), UNKNOWN("U", "Unknown");

    private final String type;
    private final String naturalName;
    private static Map<String, String> genderMap = new HashMap<String, String>(3);

    public String getType() {
        return type;
    }

    public String getNaturalName() {
        return naturalName;
    }

    Gender(String type, String naturalName) {
        this.type = type;
        this.naturalName = naturalName;
    }

    static {
        for (Gender role : Gender.values()) {
            genderMap.put(role.getType(), role.getNaturalName());
        }
    }

    public static Map<String, String> getGenderMap() {
        return genderMap;
    }
}
