package com.domain;

import java.util.Date;

/**
 * Created by yashar on 8/06/2016.
 */
public class ApplicantInformation {
    private int referenceNo;
    private String name;
    private String department;
    private String statusDescription;
    private String checkMethodDescription;
    private boolean paidInvite;
    private String dco;
    private boolean resultDestroyed;
    private String resultDate;
    private String empId;
    private String purchaseOrder;

    public int getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(int referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getCheckMethodDescription() {
        return checkMethodDescription;
    }

    public void setCheckMethodDescription(String checkMethodDescription) {
        this.checkMethodDescription = checkMethodDescription;
    }

    public boolean isPaidInvite() {
        return paidInvite;
    }

    public void setPaidInvite(boolean paidInvite) {
        this.paidInvite = paidInvite;
    }

    public String getDco() {
        return dco;
    }

    public void setDco(String dco) {
        this.dco = dco;
    }

    public boolean isResultDestroyed() {
        return resultDestroyed;
    }

    public void setResultDestroyed(boolean resultDestroyed) {
        this.resultDestroyed = resultDestroyed;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
