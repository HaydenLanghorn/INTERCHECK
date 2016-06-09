package com.domain;

/**
 * Created by yashar on 8/06/2016.
 */
public class Invite {
    private int checkType;
    private int paidInvite;
    private String department;
    private String email;
    private String surname;
    private String givenName;
    private String phone;
    private String empId;
    private int reminderCount;
    private String purchaseOrder;
    private String purpose;
    private Boolean childVul;
    private String userFullname;

    public int getCheckType() {
        return checkType;
    }

    public void setCheckType(int checkType) {
        this.checkType = checkType;
    }

    public int getPaidInvite() {
        return paidInvite;
    }

    public void setPaidInvite(int paidInvite) {
        this.paidInvite = paidInvite;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getReminderCount() {
        return reminderCount;
    }

    public void setReminderCount(int reminderCount) {
        this.reminderCount = reminderCount;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Boolean getChildVul() {
        return childVul;
    }

    public void setChildVul(Boolean childVul) {
        this.childVul = childVul;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }
}
