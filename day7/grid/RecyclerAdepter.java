package com.example.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdepter extends RecyclerView.Adapter<RecyclerAdepter.ViewHolder> {

    ArrayList<String> details;

    Context context;

    public RecyclerAdepter(ArrayList<String> details, Context context) {

        this.details = details;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listitem = layoutInflater.inflate(R.layout.recycler_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(listitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.news_heading.setText(details.get(position));
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView news_heading;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            news_heading = itemView.findViewById(R.id.newsheading);
        }
    }
}
