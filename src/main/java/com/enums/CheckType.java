package com.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Atiqur Rahman
 * @since 24/07/2013 11:32 AM
 */
public enum CheckType {

    STANDARD(1, "Employment"),
    VOLUNTEER(2, "Volunteer");

    private final int id;
    private final String naturalName;
    private static Map<Integer, String> map = new LinkedHashMap<Integer, String>(7);

    public int getId() {
        return id;
    }

    public String getNaturalName() {
        return naturalName;
    }

    CheckType(int id, String naturalName) {
        this.id = id;
        this.naturalName = naturalName;
    }

    static {
        for (CheckType role : CheckType.values()) {
            map.put(role.getId(), role.getNaturalName());
        }
    }

    public static Map<Integer, String> getMap() {
        return map;
    }


}
