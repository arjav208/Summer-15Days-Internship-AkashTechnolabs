package com.example.emi.Model;

import com.google.gson.annotations.SerializedName;

public class ReminderdataModel {

    @SerializedName("reminder_id")
    private String reminder_id;

    @SerializedName("emi_company")
    private String emi_company;

    @SerializedName("emi_premium")
    private String emi_premium;

    @SerializedName("reminder_date")
    private String reminder_date;

    @SerializedName("premium_date")
    private String premium_date;

    @SerializedName("user_phone")
    private String user_phone;

    public String getEmi_company() {
        return emi_company;
    }

    public void setEmi_company(String emi_company) {
        this.emi_company = emi_company;
    }

    public String getReminder_id() {
        return reminder_id;
    }

    public void setReminder_id(String reminder_id) {
        this.reminder_id = reminder_id;
    }

    public String getEmi_premium() {
        return emi_premium;
    }

    public void setEmi_premium(String emi_premium) {
        this.emi_premium = emi_premium;
    }

    public String getReminder_date() {
        return reminder_date;
    }

    public void setReminder_date(String reminder_date) {
        this.reminder_date = reminder_date;
    }

    public String getPremium_date() {
        return premium_date;
    }

    public void setPremium_date(String premium_date) {
        this.premium_date = premium_date;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}
