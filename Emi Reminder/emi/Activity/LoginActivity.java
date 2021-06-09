package com.example.emi.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.emi.Api.Api;
import com.example.emi.Api.ApiServices;
import com.example.emi.Model.ProfiledataModel;
import com.example.emi.Model.Result;
import com.example.emi.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText user_id, user_password;

    ProfiledataModel profiledataModel = new ProfiledataModel();

    public static final String login_userdata = "user_data";
    public static final String login_user_id = "login_user_id";
    public static final String login_user_name = "login_user_name";
    public static final String login_user_email = "login_user_email";
    public static final String login_user_mobile = "login_user_mobile";
    public static final String login_user_password = "login_user_password";
    public static final String login_status = "login_status";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        init();

        sharedPreferences = getSharedPreferences(login_userdata, MODE_PRIVATE);
        check_login();

    }

    public void init(){

        user_id = findViewById(R.id.user_id);
        user_password = findViewById(R.id.user_password);
    }

    public void check_login() {
        if (sharedPreferences.getBoolean(login_status, false)) {

            Intent j = new Intent(LoginActivity.this, HomeActivity.class);
            finish();
            startActivity(j);
        }
    }


    public void login_success(View view){
        login_api();
    }

    void login_api() {

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices apiServices = retrofit.create(ApiServices.class);


        Call<Result> call = apiServices.user_login(user_id.getText().toString(), user_password.getText().toString());

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {

                        profiledataModel = response.body().getData();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(login_status, true);
                        editor.putString(login_user_id,profiledataModel.getUser_id());
                        editor.putString(login_user_name, profiledataModel.getUser_name());
                        editor.putString(login_user_email,profiledataModel.getUser_email());
                        editor.putString(login_user_mobile, profiledataModel.getUser_phone());
                        editor.putString(login_user_password,profiledataModel.getUser_password());
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}