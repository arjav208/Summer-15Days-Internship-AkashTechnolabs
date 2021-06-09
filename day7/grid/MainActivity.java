package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView news_show_recycler;
    RecyclerAdepter recyclerAdepter;
    ArrayList<String > details = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        details.add("Information Technology");
        details.add("Computer department");

        details.add("GEC MODASA");

        details.add("ACPC CENTRE");

        details.add("IOS Lab");



        news_show_recycler = findViewById(R.id.news_recycler_view);

        recyclerAdepter = new RecyclerAdepter( details, MainActivity.this);
        news_show_recycler.setLayoutManager(new GridLayoutManager(this, 2));
        news_show_recycler.setAdapter(recyclerAdepter);
    }
}