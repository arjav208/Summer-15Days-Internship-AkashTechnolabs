package com.example.emi.Api;


import com.example.emi.Model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("create_account.php")
    Call<Result> create_account(
            @Field("user_name") String user_name,
            @Field("user_email") String user_email,
            @Field("user_phone") String user_phone,
            @Field("user_password") String user_password

    );

    @FormUrlEncoded
    @POST("user_login.php")
    Call<Result> user_login (
            @Field("user_phone") String user_phone,
            @Field("user_password") String user_password

    );

    @FormUrlEncoded
    @POST("reminder_post.php")
    Call<Result> reminder_post (
            @Field("emi_company") String emi_company,
            @Field("emi_premium") String emi_premium,
            @Field("reminder_date") String reminder_date,
            @Field("premium_date") String premium_date,
            @Field("user_phone") String user_phone
    );

    @FormUrlEncoded
    @POST("get_reminder.php")
    Call<Result> get_reminder(
            @Field("user_phone") String user_phone
    );

    @FormUrlEncoded
    @POST("change_password.php")
    Call<Result> change_password (
            @Field("user_phone") String user_phone,
            @Field("user_newpassword") String user_password

    );

}
