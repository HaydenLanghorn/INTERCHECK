package com.domain;

import java.util.List;

/**
 * Created by yashar on 8/06/2016.
 */
public class ReportResults {
    boolean error;
    String errorMessage;
    List<ApplicantInformation> applicants;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ApplicantInformation> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<ApplicantInformation> applicants) {
        this.applicants = applicants;
    }
}
