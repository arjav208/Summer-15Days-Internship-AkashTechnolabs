package com.example.custom_list_view;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> msg = new ArrayList<>();
    Custom_adapter custom_adapter;
    ListView listView;
    EditText add_name,add_msg;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        name.add("arjav");
        msg.add("hii");

        add_name = findViewById(R.id.add_name);
        add_msg = findViewById(R.id.add_msg);
        btn = findViewById(R.id.btn);
        listView = findViewById(R.id.list);

        custom_adapter = new Custom_adapter(MainActivity.this, name, msg);

        listView.setAdapter(custom_adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = add_name.getText().toString();
                String msg1 = add_msg.getText().toString();

                if (name1.equals("")){
                    add_name.setError("Enter Name");
                }
                else if (name.contains(name1)){
                    add_name.setError("Already in chat");
                }
                else {
                    name.add(name1);
                    msg.add(msg1);
                    add_name.setText("");
                    add_msg.setText("");
                    custom_adapter.notifyDataSetChanged();
                }
            }
        });


    }


}