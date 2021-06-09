package com.example.emi.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emi.Model.ReminderdataModel;
import com.example.emi.R;

import java.util.ArrayList;

public class Homepage_Recycler_Adepter extends RecyclerView.Adapter<Homepage_Recycler_Adepter.ViewHolder> {

    ArrayList<ReminderdataModel> reminderdataModels;
    Context context;

    public Homepage_Recycler_Adepter(ArrayList<ReminderdataModel> reminderdataModels, Context context) {

        this.reminderdataModels = reminderdataModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listitem = layoutInflater.inflate(R.layout.home_recycler_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.order_id.setText(reminderdataModels.get(position).getReminder_id());
        holder.emi_company.setText(reminderdataModels.get(position).getEmi_company());
        holder.premium_date.setText(reminderdataModels.get(position).getPremium_date());
        holder.premium_price.setText(reminderdataModels.get(position).getEmi_premium());

    }


    @Override
    public int getItemCount() {
        return reminderdataModels.size();
    }

    public void removeItem(int position) {
        reminderdataModels.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {
        reminderdataModels.add(reminderdataModels.get(position));
        notifyItemInserted(position);
    }

    public ArrayList<ReminderdataModel> getData() {
        return reminderdataModels;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView order_id,emi_company,premium_date,premium_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            order_id = itemView.findViewById(R.id.order_id);
            emi_company = itemView.findViewById(R.id.emi_company);
            premium_date = itemView.findViewById(R.id.premium_date);
            premium_price = itemView.findViewById(R.id.premium_price);

        }
    }
}
