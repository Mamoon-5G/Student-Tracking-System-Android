package com.example.studenttrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MADAdapter extends RecyclerView.Adapter<MADAdapter.MADViewHolder> {

    private Context context;
    private ArrayList MADut1,MADut2,MADtheory;

    public MADAdapter(Context context, ArrayList MADut1, ArrayList MADut2, ArrayList MADtheory) {
        this.context = context;
        this.MADut1 = MADut1;
        this.MADut2 = MADut2;
        this.MADtheory = MADtheory;
    }

    @NonNull
    @Override
    public MADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentryview_mad,parent,false);
        return new MADViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MADViewHolder holder, int position) {

        holder.MADunit1.setText(String.valueOf(MADut1.get(position)));
        holder.MADunit2.setText(String.valueOf(MADut2.get(position)));
        holder.MADTheory.setText(String.valueOf(MADtheory.get(position)));

    }

    @Override
    public int getItemCount() {
        return MADut1.size();
    }

    public class MADViewHolder extends RecyclerView.ViewHolder {
        TextView MADunit1,MADunit2,MADTheory;
        public MADViewHolder(@NonNull View itemView) {
            super(itemView);
            MADunit1 = itemView.findViewById(R.id.setMarksMADUT1);
            MADunit2 = itemView.findViewById(R.id.setMarksMADUT2);
            MADTheory = itemView.findViewById(R.id.setMarksMADTHEROY);

        }
    }
}
