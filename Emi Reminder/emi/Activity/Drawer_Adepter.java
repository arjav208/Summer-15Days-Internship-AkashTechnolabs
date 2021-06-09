package com.example.emi.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emi.R;

import java.util.ArrayList;

public class Drawer_Adepter extends RecyclerView.Adapter<Drawer_Adepter.ViewHolder> {

    ArrayList<Integer> Drawer_item_icon;
    ArrayList<String> Drawer_item_name;
    Context context;
    selectdraweritem selectdraweritem;


    public Drawer_Adepter(ArrayList<String> Drawer_item_name, ArrayList<Integer> Drawer_item_icon, Context context) {
        this.Drawer_item_name = Drawer_item_name;
        this.Drawer_item_icon = Drawer_item_icon;
        this.context = context;

        try {
            this.selectdraweritem = ((selectdraweritem) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Error");
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.drawer_recycler_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.drawer_item_name.setText(Drawer_item_name.get(position));
        holder.drawer_item_icon.setImageResource(Drawer_item_icon.get(position));
    }

    @Override
    public int getItemCount() {
        return Drawer_item_icon.size();
    }

    public interface selectdraweritem {

        public void openselecteddraweritem(int pos);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView drawer_item_icon;
        TextView drawer_item_name;
        LinearLayout drawer_menu_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drawer_item_icon = itemView.findViewById(R.id.drawer_item_icon);
            drawer_item_name = itemView.findViewById(R.id.drawer_item_name);
            drawer_menu_item = itemView.findViewById(R.id.drawer_menu_item);

            drawer_menu_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectdraweritem.openselecteddraweritem(getAdapterPosition());

                }
            });
        }
    }
}
