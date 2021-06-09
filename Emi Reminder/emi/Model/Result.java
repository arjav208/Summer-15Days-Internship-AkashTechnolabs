package com.example.emi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private ProfiledataModel data;

    @SerializedName("reminder_data")
    private ArrayList<ReminderdataModel> reminderdataModels;


    public ArrayList<ReminderdataModel> getReminderdataModels() {
        return reminderdataModels;
    }

    public void setReminderdataModels(ArrayList<ReminderdataModel> reminderdataModels) {
        this.reminderdataModels = reminderdataModels;
    }





    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProfiledataModel getData() {
        return data;
    }

    public void setData(ProfiledataModel data) {
        this.data = data;
    }
}
