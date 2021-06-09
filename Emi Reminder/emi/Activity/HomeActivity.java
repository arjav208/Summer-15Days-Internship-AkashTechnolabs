package com.example.emi.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.emi.Api.Api;
import com.example.emi.Api.ApiServices;
import com.example.emi.Model.ReminderdataModel;
import com.example.emi.Model.Result;
import com.example.emi.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.emi.Activity.LoginActivity.login_userdata;

public class HomeActivity extends AppCompatActivity implements Drawer_Adepter.selectdraweritem {

    Homepage_Recycler_Adepter homepage_recycler_adepter;
    RecyclerView order_recycler_view_home;
    ArrayList<ReminderdataModel> reminderdataModels = new ArrayList<>();

    LinearLayout linear;

    RecyclerView recycler_drawer;
    ArrayList<String> Drawer_item_name = new ArrayList<>();
    ArrayList<Integer> Drawer_item_icon = new ArrayList<Integer>();
    DrawerLayout drawerLayout;
    Drawer_Adepter drawer_adepter;

    public static final String login_user_mobile = "login_user_mobile";
    public static final String login_user_name = "login_user_name";
    SharedPreferences sharedPreferences;

    TextView user_name_show, user_phone_show;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlyout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.skyblue));
        }
        order_recycler_home();
        drawer();
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        sharedPreferences = getSharedPreferences(login_userdata, MODE_PRIVATE);
        user_reminder_get();

        linear = findViewById(R.id.linear);

        user_name_show = findViewById(R.id.user_name_show);
        user_phone_show = findViewById(R.id.user_phone_show);

        user_phone_show.setText(sharedPreferences.getString(login_user_mobile, ""));
        user_name_show.setText(sharedPreferences.getString(login_user_name, ""));

        dialog = new Dialog(HomeActivity.this, R.style.MyAlertDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        enableSwipeToDeleteAndUndo();
    }


    public void order_recycler_home() {

        order_recycler_view_home = findViewById(R.id.order_recycler_view_home);
        homepage_recycler_adepter = new Homepage_Recycler_Adepter(reminderdataModels, HomeActivity.this);
        order_recycler_view_home.setLayoutManager(new LinearLayoutManager(this));
        order_recycler_view_home.setAdapter(homepage_recycler_adepter);
    }


    public void drawer() {

        Drawer_item_name.add("New Reminder");
        Drawer_item_icon.add(R.drawable.ic_add_reminder);

        Drawer_item_name.add("Change Password");
        Drawer_item_icon.add(R.drawable.ic_padlock);

        Drawer_item_name.add("Notification");
        Drawer_item_icon.add(R.drawable.ic_notification);

        Drawer_item_name.add("Share Appliction");
        Drawer_item_icon.add(R.drawable.ic_share__1_);

        Drawer_item_name.add("About Us");
        Drawer_item_icon.add(R.drawable.ic_info);

        recycler_drawer = findViewById(R.id.recycler_drawer);
        drawerLayout = findViewById(R.id.drawerlayout);

        drawer_adepter = new Drawer_Adepter(Drawer_item_name, Drawer_item_icon, HomeActivity.this);
        recycler_drawer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_drawer.setAdapter(drawer_adepter);

    }

    @Override
    public void openselecteddraweritem(int pos) {
        switch (pos) {

            case 0:
                drawerLayout.close();
                Intent intent = new Intent(HomeActivity.this, AddReminder.class);
                startActivity(intent);
                break;

            case 1:
                drawerLayout.close();
                Intent intent1 = new Intent(HomeActivity.this, ChangePassword.class);
                startActivity(intent1);
                break;

            case 2:
                drawerLayout.close();
                Intent intent2 = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent2);
                break;

            case 3:
                drawerLayout.close();
                break;

            case 4:
                dialog.setContentView(R.layout.about_as_app_dialog);
                dialog.show();
                break;

            case 5:
                drawerLayout.close();
                break;
        }
    }

    public void notifiction_home(View view) {
        Intent intent2 = new Intent(HomeActivity.this, NotificationActivity.class);
        startActivity(intent2);
    }

    public void logout(View view) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Intent intent2 = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent2);
        finish();
    }

    void user_reminder_get() {

        ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServices apiServices = retrofit.create(ApiServices.class);

        Call<Result> call = apiServices.get_reminder(sharedPreferences.getString(login_user_mobile, ""));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess()) {

                        reminderdataModels.clear();
                        reminderdataModels.addAll(response.body().getReminderdataModels());
                        homepage_recycler_adepter.notifyDataSetChanged();


                    } else {
                        Toast.makeText(HomeActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                int position = viewHolder.getAdapterPosition();
//                String item = homepage_recycler_adepter.getData().get(reminderdataModels.get(position));

                homepage_recycler_adepter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(linear, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        homepage_recycler_adepter.restoreItem(item, position);
                        order_recycler_view_home.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(order_recycler_view_home);
    }
}