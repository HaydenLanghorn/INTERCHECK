package com.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Atiqur Rahman
 * @since 29/10/2014 03:19 PM
 */
public enum ConsentIdDocsDocType {

    CONSENT("1", "Consent Form"),

    IDENTITY_DOCUMENTS("2", "Identity Documents");

    private final String id;
    private final String naturalName;
    private static Map<String, String> map = new LinkedHashMap<String, String>(30);

    public String getId() {
        return id;
    }

    public String getNaturalName() {
        return naturalName;
    }

    ConsentIdDocsDocType(String id, String naturalName) {
        this.id = id;
        this.naturalName = naturalName;
    }

    static {
        for (ConsentIdDocsDocType obj : ConsentIdDocsDocType.values()) {
            map.put(obj.getId(), obj.getNaturalName());
        }
    }



}
