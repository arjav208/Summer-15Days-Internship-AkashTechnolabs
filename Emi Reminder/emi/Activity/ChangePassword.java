package com.example.emi.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.emi.Api.Api;
import com.example.emi.Api.ApiServices;
import com.example.emi.Model.Result;
import com.example.emi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.emi.Activity.LoginActivity.login_userdata;

public class ChangePassword extends AppCompatActivity {

    EditText old_password,newPassword,new_Repassword ;
    Button change_password;

    public static final String login_user_mobile = "login_user_mobile";
    public static final String login_user_password = "login_user_password";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.skyblue));
        }

        old_password = findViewById(R.id.old_password);
        newPassword = findViewById(R.id.newPassword);
        new_Repassword = findViewById(R.id.new_Repassword);

        change_password = findViewById(R.id.change_password);

        sharedPreferences = getSharedPreferences(login_userdata, MODE_PRIVATE);
        String user_password = sharedPreferences.getString(login_user_password, "");


        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user_password.equals(old_password.getText().toString()) && new_Repassword.getText().toString().equals(new_Repassword.getText().toString())){

                    if (user_password.equals(new_Repassword.getText().toString())){
                        Toast.makeText(ChangePassword.this,"something want wrong",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        change_password_api();
                    }



                }
                else{

                    Toast.makeText(ChangePassword.this,"something want wrong",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePassword.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    void change_password_api() {

        final ProgressDialog progressDialog = new ProgressDialog(ChangePassword.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices apiServices = retrofit.create(ApiServices.class);


        Call<Result> call = apiServices.change_password(sharedPreferences.getString(login_user_mobile, ""), new_Repassword.getText().toString());


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().getSuccess()) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();

                        Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(ChangePassword.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ChangePassword.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangePassword.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChangePassword.this, "done" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}