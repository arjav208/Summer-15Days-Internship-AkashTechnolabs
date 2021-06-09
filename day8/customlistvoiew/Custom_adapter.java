package com.example.custom_list_view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Custom_adapter extends ArrayAdapter {
    ArrayList<String> name;
    ArrayList<String> msg;
    Activity activity;

    public Custom_adapter(Activity activity, ArrayList<String> name, ArrayList<String> msg) {
        super(activity, R.layout.item_chat, name);
        this.name = name;
        this.msg = msg;
        this.activity = activity;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.item_chat, null, true);

        TextView tname = rowView.findViewById(R.id.name);
        TextView tmsg = rowView.findViewById(R.id.msg);

        tname.setText(name.get(position));
        tmsg.setText(msg.get(position));

        return rowView;
    }
}
