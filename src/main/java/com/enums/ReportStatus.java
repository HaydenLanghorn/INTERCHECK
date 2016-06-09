package com.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yashar on 8/06/2016.
 */
public enum ReportStatus {

        ALL_APPLICANTS(0, "Get All Applicants Police Check Status"),
        INVITED_APPLICANTS(1, "Applicants have NOT started the application online"),
        INCOMPLETE_APPLICANTS(2, "The applicants have started the application online and saved it.Requires completion and submission online"),
        IN_PROGRESS_APPLICANTS(3, "The applications have been submitted into the CrimTrac system and is in progress. The result has not been returned at this stage"),
        ATTENTION_APPLICANTS(4, "The applicants are missing information and the check cannot be submitted until the extra information is provided "),
        RESULT_IN(8, "Result In"),
        APPLICATION_DELETED(24, "Only deleted Applicants");

        private final int id;
        private final String naturalName;
        private static Map<Integer, String> statusMap = new LinkedHashMap<Integer, String>(8);

        public int getId() {
            return id;
        }

        public String getNaturalName() {
            return naturalName;
        }

        ReportStatus(int id, String name) {
            this.id = id;
            this.naturalName = name;
        }

        static {
            for (ReportStatus status : ReportStatus.values()) {
                statusMap.put(status.getId(), status.getNaturalName());
            }
        }

        public static Map<Integer, String> getStatusMap() {
            return statusMap;
        }
}
