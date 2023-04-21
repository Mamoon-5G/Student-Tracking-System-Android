package com.example.studenttrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WDPAdapter extends RecyclerView.Adapter<WDPAdapter.WDPViewHolder> {

    private Context context;
    private ArrayList WDPut1,WDPut2,WDPtheory;

    public WDPAdapter(Context context, ArrayList WDPut1, ArrayList WDPut2, ArrayList WDPtheory) {
        this.context = context;
        this.WDPut1 = WDPut1;
        this.WDPut2 = WDPut2;
        this.WDPtheory = WDPtheory;
    }

    @NonNull
    @Override
    public WDPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentryview_wdp,parent,false);
        return new WDPViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WDPViewHolder holder, int position) {

        holder.WDPunit1.setText(String.valueOf(WDPut1.get(position)));
        holder.WDPunit2.setText(String.valueOf(WDPut2.get(position)));
        holder.WDPTheory.setText(String.valueOf(WDPtheory.get(position)));

    }

    @Override
    public int getItemCount() {
        return WDPut1.size();
    }

    public class WDPViewHolder extends RecyclerView.ViewHolder {
        TextView WDPunit1,WDPunit2,WDPTheory;
        public WDPViewHolder(@NonNull View itemView) {
            super(itemView);
            WDPunit1 = itemView.findViewById(R.id.setMarksWDPUT1);
            WDPunit2 = itemView.findViewById(R.id.setMarksWDPUT2);
            WDPTheory = itemView.findViewById(R.id.setMarksWDPTHEROY);

        }
    }
}
