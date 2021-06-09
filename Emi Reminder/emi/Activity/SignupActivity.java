package com.example.emi.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emi.Api.Api;
import com.example.emi.Api.ApiServices;
import com.example.emi.Model.Result;
import com.example.emi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignupActivity extends AppCompatActivity {

    EditText user_name, user_email, user_phone, user_password, user_Repassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        init();
    }

    public void init() {

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_phone = findViewById(R.id.user_mobile);
        user_password = findViewById(R.id.user_password);
        user_Repassword = findViewById(R.id.user_Repassword);

    }

    public void goto_homeactivity(View view) {


        String name = user_name.getText().toString();
        String email = user_email.getText().toString();
        String mobile = user_phone.getText().toString();
        String password = user_password.getText().toString();
        String Repassword = user_Repassword.getText().toString();


        if (name.equals("")) {
            user_name.setError("Enter your name");
        } else if (email.equals("")) {
            user_email.setError("Enter email Number");
        } else if (mobile.length() != 10) {
            user_phone.setError("Enter Valid Mobile Number");
        } else if (password.equals("")) {
            user_password.setError("Enter password");
        } else if (Repassword.equals("")) {
            user_Repassword.setError("Enter your repassword");
        } else {

            if (password.equals(Repassword) == true) {

                String Expn =
                        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

                if (email.matches(Expn) && email.length() > 0) {
                    signup_api();

                } else {
                    user_email.setError("Email not valid");
                }


            } else {
                user_Repassword.setError("Check your password");
                user_Repassword.setText("");
            }
        }
    }


    void signup_api() {

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices apiServices = retrofit.create(ApiServices.class);


        Call<Result> call = apiServices.create_account(user_name.getText().toString(), user_email.getText().toString(), user_phone.getText().toString(), user_password.getText().toString());


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().getSuccess()) {
                        Toast.makeText(SignupActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignupActivity.this, "done" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}