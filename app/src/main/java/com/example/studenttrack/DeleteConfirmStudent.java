package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteConfirmStudent extends AppCompatActivity {

    TextView id,name,department,sem,date,phone;
    Button Delete;
    DBhelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_confirm_student);

        db = new DBhelper(this);

        Bundle i = getIntent().getExtras();
        String studentId = i.getString("StudentID");
        String did_view = studentId;


        id = (TextView) findViewById(R.id.studentsetIDDel);
        name = (TextView) findViewById(R.id.StudentSetNameDel);
        department = (TextView) findViewById(R.id.studentsetDepartDel);
        sem = (TextView) findViewById(R.id.studentsetSemesterDel);
        date = (TextView) findViewById(R.id.studentsetDateDel);
        phone = (TextView) findViewById(R.id.studentsetPhoneDel);
        DisplayPortDel(did_view);
        Delete = (Button) findViewById(R.id.DeleteStudent_confirm);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteConfirm(did_view);
                Toast.makeText(DeleteConfirmStudent.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void DeleteConfirm(String search) {
        db.deleteData(search);
    }

    private void DisplayPortDel(String search) {

            Cursor cursor = db.searchData(String.valueOf(search)); // Pass the studentId parameter
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No Entry", Toast.LENGTH_SHORT).show();

            } else {
                while (cursor.moveToNext()) {
                    id.setText(cursor.getString(0));
                    name.setText(cursor.getString(1));
                    department.setText(cursor.getString(2));
                    sem.setText(cursor.getString(3));
                    date.setText(cursor.getString(4));
                    phone.setText(cursor.getString(5));
                }

            }

    }
}