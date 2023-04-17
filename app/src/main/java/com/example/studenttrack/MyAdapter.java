package com.example.studenttrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList stud_id,stud_name,stud_dep;

    public MyAdapter(Context context, ArrayList stud_id, ArrayList stud_name, ArrayList stud_dep) {
        this.context = context;
        this.stud_id = stud_id;
        this.stud_name = stud_name;
        this.stud_dep = stud_dep;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stud_id.setText(String.valueOf(stud_id.get(position)));
        holder.stud_name.setText(String.valueOf(stud_name.get(position)));
        holder.stud_dep.setText(String.valueOf(stud_dep.get(position)));

    }

    @Override
    public int getItemCount() {
        return stud_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
      TextView stud_id,stud_name,stud_dep;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stud_id = itemView.findViewById(R.id.textid);
            stud_name = itemView.findViewById(R.id.textname);
            stud_dep = itemView.findViewById(R.id.depname);




        }
    }
}
