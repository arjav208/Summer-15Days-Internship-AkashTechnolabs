package com.example.emi.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.example.emi.Api.Api;
import com.example.emi.Api.ApiServices;
import com.example.emi.Model.Result;
import com.example.emi.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.emi.Activity.LoginActivity.login_userdata;

public class AddReminder extends AppCompatActivity {

    EditText reminder_date,premium_date,company_name,primium_emi;
    final Calendar myCalendar = Calendar.getInstance();

    public static final String login_user_mobile = "login_user_mobile";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reminder_date = findViewById(R.id.reminder_date);
        premium_date = findViewById(R.id.premium_date);
        company_name = findViewById(R.id.company_name);
        primium_emi = findViewById(R.id.primium_emi);

        sharedPreferences = getSharedPreferences(login_userdata, MODE_PRIVATE);
        String user_mob = sharedPreferences.getString(login_user_mobile, "");

        Toast.makeText(AddReminder.this,""+user_mob, Toast.LENGTH_SHORT).show();



//        reminder_time = findViewById(R.id.reminder_time);
//        deadline_time = findViewById(R.id.deadline_time);

//        TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminder.this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
//
//                String amPm;
//                if (hourOfDay >= 12) {
//                    amPm = "PM";
//                } else {
//                    amPm = "AM";
//                }
//                reminder_time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
//            }
//        }, 0, 0, false);
//
//        reminder_time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timePickerDialog.show();
//
//            }
//        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//                updateLabel2();
            }
        };

        reminder_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(AddReminder.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                updateLabel();

            }
        });

        premium_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(AddReminder.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                updateLabel2();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        reminder_date.setText(sdf.format(myCalendar.getTime()));

    }
    private void updateLabel2() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        premium_date.setText(sdf.format(myCalendar.getTime()));

    }


    public void submit_reminder(View view){

        String emi_company = company_name.getText().toString();
        String emi_premium = primium_emi.getText().toString();
        String date_reminder = reminder_date.getText().toString();
        String date_premium = premium_date.getText().toString();

        if(emi_company.equals("") || emi_premium.equals("") || date_reminder.equals("") || date_premium.equals("")){

            Toast.makeText(AddReminder.this,"fill All Details perfectly", Toast.LENGTH_SHORT).show();
        }

        else{
            reminder_post_api();
        }
    }

    void reminder_post_api() {

        final ProgressDialog progressDialog = new ProgressDialog(AddReminder.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices apiServices = retrofit.create(ApiServices.class);


        Call<Result> call = apiServices.reminder_post(company_name.getText().toString(), primium_emi.getText().toString(), reminder_date.getText().toString(), premium_date.getText().toString(),sharedPreferences.getString(login_user_mobile, ""));


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().getSuccess()) {
                        Toast.makeText(AddReminder.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddReminder.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AddReminder.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddReminder.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AddReminder.this, "done" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}