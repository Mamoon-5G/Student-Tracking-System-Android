package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddStudent extends AppCompatActivity {



    final Calendar myCalendar= Calendar.getInstance();
    public EditText insert_id,insert_name,insert_date,insert_phone;
    public DBhelper Db;
    public AutoCompleteTextView insert_department;
    public AutoCompleteTextView insert_semester;

    public Button submit_button;
    // To give the data to the auto completes related to course and department
    String[] course = {"Computer Engineering", "Computer Hardware","Computer Science Engineering","Civil Engineering","Mechanical Engineering"};
    String[] semeseter = {"Semester 01", "Semester 02","Semester 03","Semester 04","Semester 05","Semester 06"};




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        getSupportActionBar().hide();


        // Auto Complete related to Course Selection
        ArrayAdapter<String> adapter_course = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, course);
        AutoCompleteTextView course = (AutoCompleteTextView) findViewById(R.id.department_input);
        course.setThreshold(1);
        course.setAdapter(adapter_course);


        // Auto Complete related to Semester Selection
        ArrayAdapter<String> adapter_semester = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, semeseter);
        AutoCompleteTextView semester = (AutoCompleteTextView) findViewById(R.id.semester_input);
        semester.setThreshold(1);
        semester.setAdapter(adapter_semester);


        //Creating a DatePicker for Date of Birth
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        insert_date = (EditText) findViewById(R.id.dob_input);
        insert_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddStudent.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });






        insert_id = findViewById(R.id.id_input);
        insert_name = findViewById(R.id.name_input);
        insert_department = findViewById(R.id.department_input);
        insert_semester = findViewById(R.id.semester_input);
        insert_date = findViewById(R.id.dob_input);
        submit_button = findViewById(R.id.submit_database);
        insert_phone = findViewById(R.id.phone_input);

        DBhelper db=new DBhelper(this);




        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dbid= insert_id.getText().toString();
                String dbname = insert_name.getText().toString();
                String department = insert_department.getText().toString();
                String dbSemester = insert_semester.getText().toString();
                String dbDate = insert_date.getText().toString();
                String dbPhone = insert_phone.getText().toString();
                OpenAddMarks();



                if(dbid.equals("")||dbname.equals("")||department.equals("")||dbSemester.equals("")||dbDate.equals("")){
                    Toast.makeText(AddStudent.this, "Please Enter Some data", Toast.LENGTH_SHORT).show();
                }else{


                    boolean checkstudent = db.CheckDuplicateID(dbid);
                    if(checkstudent==false){

                        boolean insert = db.insertStudent(dbid,dbname,department,dbSemester,dbDate,dbPhone);
                        Toast.makeText(AddStudent.this, "Inserted Succesfully", Toast.LENGTH_SHORT).show();
                        if(insert==true){
                            Toast.makeText(AddStudent.this, "User Entered Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddStudent.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
                        }

                    }else{

                        Toast.makeText(AddStudent.this, "Student is Already Exists", Toast.LENGTH_SHORT).show();

                    }

                }

                //Sends the Student ID to the Add Student Activity


            }












        });



    }

    private void OpenAddMarks() {
        Intent intent = new Intent ( getApplicationContext(), HomeActivity.class );
        startActivity(intent);
    }


    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        insert_date.setText(dateFormat.format(myCalendar.getTime()));
    }

}