package com.onebill.bank.data.bank;

import java.util.Date;

public class HelpSupport {

    String complaint;
    String userName;
    Date date;
    String status;
    String adminUserName;
    Date dateResolved;

    public HelpSupport(String userName, String complaint, Date date) {
        this.userName = userName;
        this.complaint = complaint;
        this.date = date;
        this.status = "Open"; // Initial status is set to "Open"
    }

    public void resolve(String adminUserName) {
        this.status = "Resolved";
        this.adminUserName = adminUserName;
        this.dateResolved = new Date();
    }

    // Getter methods for accessing information
    public String getComplaint() {
        return complaint;
    }

    public String getUserName() {
        return userName;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public Date getDateResolved() {
        return dateResolved;
    }
}
