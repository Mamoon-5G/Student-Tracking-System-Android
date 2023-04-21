package com.example.studenttrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PWPAdapter extends RecyclerView.Adapter<PWPAdapter.PWPViewHolder> {

    private Context context;
    private ArrayList PWPut1,PWPut2,PWPtheory;

    public PWPAdapter(Context context, ArrayList PWPut1, ArrayList PWPut2, ArrayList PWPtheory) {
        this.context = context;
        this.PWPut1 = PWPut1;
        this.PWPut2 = PWPut2;
        this.PWPtheory = PWPtheory;
    }

    @NonNull
    @Override
    public PWPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentryview_pwp,parent,false);
        return new PWPViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PWPViewHolder holder, int position) {

        holder.PWPunit1.setText(String.valueOf(PWPut1.get(position)));
        holder.PWPunit2.setText(String.valueOf(PWPut2.get(position)));
        holder.PWPTheory.setText(String.valueOf(PWPtheory.get(position)));

    }

    @Override
    public int getItemCount() {
        return PWPut1.size();
    }

    public class PWPViewHolder extends RecyclerView.ViewHolder {
        TextView PWPunit1,PWPunit2,PWPTheory;
        public PWPViewHolder(@NonNull View itemView) {
            super(itemView);
            PWPunit1 = itemView.findViewById(R.id.setMarksPWPUT1);
            PWPunit2 = itemView.findViewById(R.id.setMarksPWPUT2);
            PWPTheory = itemView.findViewById(R.id.setMarksPWPTHEROY);

        }
    }
}
