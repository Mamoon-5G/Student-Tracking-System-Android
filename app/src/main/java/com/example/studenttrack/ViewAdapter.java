package com.example.studenttrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.newViewHolder> {

    public Context context;
    public ArrayList student_id,student_name,student_department,student_semester,student_date,student_phone;

    public ViewAdapter(Context context, ArrayList student_id, ArrayList name, ArrayList student_department, ArrayList student_semester, ArrayList student_date, ArrayList student_phone) {
        this.context = context;
        this.student_id = student_id;
        this.student_name = name;
        this.student_department = student_department;
        this.student_semester = student_semester;
        this.student_date = student_date;
        this.student_phone = student_phone;
    }

    @NonNull
    @Override
    public newViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentryviewstudent,parent,false);

        return new newViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull newViewHolder holder, int position) {

        holder.student_id.setText(String.valueOf(student_id.get(position)));
        holder.student_name.setText(String.valueOf(student_name.get(position)));
        holder.student_department.setText(String.valueOf(student_department.get(position)));
        holder.student_semester.setText(String.valueOf(student_semester.get(position)));
        holder.student_date.setText(String.valueOf(student_date.get(position)));
        holder.student_phone.setText(String.valueOf(student_phone.get(position)));

    }

    @Override
    public int getItemCount() {
        return student_id.size();
    }

    public class newViewHolder extends RecyclerView.ViewHolder {
        TextView student_id,student_name, student_department,student_semester,student_date,student_phone;
        public newViewHolder(@NonNull View itemView) {
            super(itemView);
            student_id = itemView.findViewById(R.id.studentsetID);
            student_name = itemView.findViewById(R.id.StudentSetName);
            student_department = itemView.findViewById(R.id.studentsetDepart);
            student_semester = itemView.findViewById(R.id.studentsetSemester);
            student_date = itemView.findViewById(R.id.studentsetDate);
            student_phone = itemView.findViewById(R.id.studentsetPhone);

        }
    }
}
