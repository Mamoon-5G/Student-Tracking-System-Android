//package com.example.studenttrack;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class AttendenceAdapter extends RecyclerView.Adapter<AttendenceAdapter.MyViewHolder>{
//
//    private Context context;
//    private ArrayList idmarks;
//    private ArrayList marks1;
//    private ArrayList marks2;
//    private ArrayList marks3;
//    private ArrayList marks4;
//
//
//    public AttendenceAdapter(Context context, ArrayList id, ArrayList marks1, ArrayList marks2, ArrayList marks3, ArrayList marks4) {
//        this.context = context;
//        this.idmarks = idmarks;
//        this.marks1 = marks1;
//        this.marks2 = marks2;
//        this.marks3 = marks3;
//        this.marks4 = marks4;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.userentryviewmarks,parent,false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        holder.idmarks.setText(String.valueOf(idmarks.get(position)));
//        holder.subject1.setText(String.valueOf(marks1.get(position)));
//        holder.subject2.setText(String.valueOf(marks2.get(position)));
//        holder.subject3.setText(String.valueOf(marks3.get(position)));
//        holder.subject4.setText(String.valueOf(marks4.get(position)));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return idmarks.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView idmarks,subject1,subject2,subject3,subject4;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            idmarks = itemView.findViewById(R.id.studentsetIDmarks);
//            subject1 = itemView.findViewById(R.id.StudentSetMarks1);
//            subject1 = itemView.findViewById(R.id.studentsetMarks2);
//            subject1 = itemView.findViewById(R.id.studentsetMarks3);
//            subject1 = itemView.findViewById(R.id.studentsetMarks4);
//        }
//    }
//}
