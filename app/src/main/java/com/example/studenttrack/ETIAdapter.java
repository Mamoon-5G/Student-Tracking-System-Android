package com.example.studenttrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ETIAdapter extends RecyclerView.Adapter<ETIAdapter.ETIViewHolder> {

    private Context context;
    private ArrayList ETIut1,ETIut2,ETItheory;

    public ETIAdapter(Context context, ArrayList ETIut1, ArrayList ETIut2, ArrayList ETItheory) {
        this.context = context;
        this.ETIut1 = ETIut1;
        this.ETIut2 = ETIut2;
        this.ETItheory = ETItheory;
    }

    @NonNull
    @Override
    public ETIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentryview_eti,parent,false);
        return new ETIViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ETIViewHolder holder, int position) {

        holder.ETIunit1.setText(String.valueOf(ETIut1.get(position)));
        holder.ETIunit2.setText(String.valueOf(ETIut2.get(position)));
        holder.ETITheory.setText(String.valueOf(ETItheory.get(position)));

    }

    @Override
    public int getItemCount() {
        return ETIut1.size();
    }

    public class ETIViewHolder extends RecyclerView.ViewHolder {
        TextView ETIunit1,ETIunit2,ETITheory;
        public ETIViewHolder(@NonNull View itemView) {
            super(itemView);
            ETIunit1 = itemView.findViewById(R.id.setMarksETIUT1);
            ETIunit2 = itemView.findViewById(R.id.setMarksETIUT2);
            ETITheory = itemView.findViewById(R.id.setMarksETITHEROY);

        }
    }
}
