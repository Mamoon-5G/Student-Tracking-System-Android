package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewStudent extends AppCompatActivity {

    public TextView ShowAttendance,ShowPercentage,ShowResult,ShowGrade;
    public DBhelper db;

    public ProgressBar studentPercent,studentAttendence;
    RecyclerView recyclerViewETI,recyclerViewMAD,recyclerViewPWP,recyclerViewWDP;
    TextView id,name,department,sem,date,phone;
    ArrayList<String> ut1ETI,ut2ETI,theoryETI;
    ArrayList<String> ut1MAD,ut2MAD,theoryMAD;
    ArrayList<String> ut1PWP,ut2PWP,theoryPWP;
    ArrayList<String> ut1WDP,ut2WDP,theoryWDP;
    ETIAdapter etiAdapter;
    MADAdapter madAdapter;
    PWPAdapter pwpAdapter;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        getSupportActionBar().hide();

        db = new DBhelper(this); // Initialize the db object

        Bundle i = getIntent().getExtras();
        String studentId = i.getString("StudentID");
        String did_view = studentId;

        //Students Personal Details

        id = (TextView) findViewById(R.id.studentsetID);
        name = (TextView) findViewById(R.id.StudentSetName);
        department = (TextView) findViewById(R.id.studentsetDepart);
        sem = (TextView) findViewById(R.id.studentsetSemester);
        date = (TextView) findViewById(R.id.studentsetDate);
        phone = (TextView) findViewById(R.id.studentsetPhone);



        // Student Performance Attributes
        ShowAttendance = (TextView)  findViewById(R.id.showAttendences);
        ShowPercentage = (TextView) findViewById(R.id.showpercentages);
        ShowGrade = (TextView) findViewById(R.id.showGrade);
        ShowResult = (TextView) findViewById(R.id.showResult);
        studentPercent = (ProgressBar) findViewById(R.id.studentPercentage);
        studentAttendence = (ProgressBar)findViewById(R.id.studentAttendencer);



        ut1ETI = new ArrayList<>();
        ut2ETI = new ArrayList<>();
        theoryETI = new ArrayList<>();
        ut1MAD = new ArrayList<>();
        ut2MAD = new ArrayList<>();
        theoryMAD = new ArrayList<>();
        ut1PWP = new ArrayList<>();
        ut2PWP = new ArrayList<>();
        theoryPWP = new ArrayList<>();
        ut1WDP = new ArrayList<>();
        ut2WDP = new ArrayList<>();
        theoryWDP = new ArrayList<>();

        recyclerViewETI = findViewById(R.id.RecycleViewETIMarks);
        recyclerViewMAD = findViewById(R.id.RecycleViewMADMarks);
        recyclerViewPWP = findViewById(R.id.RecycleViewPWPMarks);
        recyclerViewWDP = findViewById(R.id.RecycleViewWDPMarks);

        etiAdapter = new ETIAdapter(this,ut1ETI,ut2ETI,theoryETI);//ETI
        madAdapter = new MADAdapter(this,ut1MAD,ut2MAD,theoryMAD);//MAD
        pwpAdapter = new PWPAdapter(this,ut1PWP,ut2PWP,theoryPWP);//PWP

        recyclerViewETI.setAdapter(etiAdapter);
        recyclerViewETI.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMAD.setAdapter(madAdapter);
        recyclerViewMAD.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPWP.setAdapter(pwpAdapter);
        recyclerViewPWP.setLayoutManager(new LinearLayoutManager(this));
        DisplayPort(did_view);
        DisplayETI(did_view);
        DisplayMAD(did_view);
        DisplayPWP(did_view);
        CalculatePercentage(did_view);
        CalculateAttendence(did_view);

    }

    private void CalculateAttendence(String search) {


        Cursor cursor = db.searchPWPAttendence(String.valueOf(search));
        int py_attendence = 0 ;
        if (cursor.getCount() == 0) {

        } else {

            while (cursor.moveToNext()){

                py_attendence = Integer.parseInt(cursor.getString(0));

            }
        }

        Cursor cursor2 = db.searchMADAttendence(String.valueOf(search));
        int mad_attendence = 0 ;
        if (cursor2.getCount() == 0) {

        } else {

            while (cursor2.moveToNext()){

                mad_attendence = Integer.parseInt(cursor2.getString(0));

            }
        }

        Cursor cursor3 = db.searchETIAttendence(String.valueOf(search));
        int eti_attendence = 0 ;
        if (cursor3.getCount() == 0) {
        } else {

            while (cursor3.moveToNext()){

                eti_attendence = Integer.parseInt(cursor3.getString(0));

            }
        }
        float subtotal = mad_attendence + eti_attendence + py_attendence;
        float total = subtotal/8*100;
        int attendence = (int)total;

        DecimalFormat dfx = new DecimalFormat("###.#");
        String per = dfx.format(total);
        ShowAttendance.setText(per+"%");
        studentAttendence.setProgress(attendence);
    }














    private void CalculatePercentage(String search) {


        // Get Python Marks
        Cursor cursor = db.searchPWP(String.valueOf(search));
        float total_python = 0;
        if (cursor.getCount() == 0) {

        } else {
            float py1 = 0;
            float py2 = 0;
            float pyfinal = 0;
            while (cursor.moveToNext()) {
                py1 = Float.parseFloat(cursor.getString(0));
                py2 = Float.parseFloat(cursor.getString(1));
                pyfinal = Float.parseFloat(cursor.getString(2));

            }
            total_python = (py1 + py2 + pyfinal) / 110 * 100;

        }
        // Get Mobile Devops marks
        Cursor cursormad = db.searchMAD(String.valueOf(search));
        float total_mad = 0;
        if (cursormad.getCount() == 0) {

        } else {
            float mad1 = 0 ;
            float mad2 = 0 ;
            float madfinal = 0;
            while (cursormad.moveToNext()) {
                mad1 = Float.parseFloat(cursormad.getString(0));
                mad2 = Float.parseFloat(cursormad.getString(1));
                madfinal = Float.parseFloat(cursormad.getString(2));
            }
            total_mad = (mad1+mad2+madfinal)/110*100;

        }
        //Get ETI subjects Marks
        Cursor cursoreti = db.searchETI(String.valueOf(search));
        float eti_total = 0;
        if (cursor.getCount() == 0) {

        } else {

            float eti1 = 0;
            float eti2 = 0;
            float etifinal = 0;
            while (cursoreti.moveToNext()) {
                eti1 = Float.parseFloat(cursoreti.getString(0));
                eti2 = Float.parseFloat(cursoreti.getString(1));
                etifinal = Float.parseFloat(cursoreti.getString(2));
            }
            eti_total = (eti1+eti2+etifinal)/110*100;
        }
        // Lets calculate percentage of each subject

        float MainTotal = (eti_total + total_mad + total_python) / 3;
        DecimalFormat df = new DecimalFormat("##.#");
        int percent_progress = (int)MainTotal;
        studentPercent.setProgress(percent_progress);
        String percenttt = df.format(MainTotal);
        ShowPercentage.setText(percenttt+"%");

        //Calculate result

        if(percent_progress < 35){
            ShowResult.setText("Result : FAIL");
        }
        else{
            ShowResult.setText("Result : PASS");
        }

        // Calculating Grade
        if(percent_progress < 40 && percent_progress > 30){
            ShowGrade.setText("Grade : E");
        }
        if(percent_progress < 50 && percent_progress > 40){
            ShowGrade.setText("Grade : D");
        }
        if(percent_progress < 60 && percent_progress > 50){
            ShowGrade.setText("Grade : C");
        }
        if(percent_progress < 70 && percent_progress > 60){
            ShowGrade.setText("Grade : B");
        }
        if(percent_progress < 80 && percent_progress > 70){
            ShowGrade.setText("Grade : A");
        }
        if(percent_progress < 90 && percent_progress > 80){
            ShowGrade.setText("Grade : A+");
        }



    }








    private void DisplayPWP(String search) {
        Cursor cursor = db.searchPWP(String.valueOf(search));
        if (cursor.getCount() == 0) {
            //Set text view

        } else {
            while (cursor.moveToNext()) {
                ut1PWP.add(cursor.getString(0));
                ut2PWP.add(cursor.getString(1));
                theoryPWP.add(cursor.getString(2));

            }
            // Notify the adapter after adding data
        }
    }



    private void DisplayMAD(String search){
        Cursor cursor = db.searchMAD(String.valueOf(search));
        if (cursor.getCount() == 0) {
            //Set text view

        } else {
            while (cursor.moveToNext()) {
                ut1MAD.add(cursor.getString(0));
                ut2MAD.add(cursor.getString(1));
                theoryMAD.add(cursor.getString(2));

            }
           // Notify the adapter after adding data
        }

    }
    private void DisplayETI(String search){
        Cursor cursor = db.searchETI(String.valueOf(search));
        if (cursor.getCount() == 0) {
            //Set text view

        } else {
            while (cursor.moveToNext()) {
                ut1ETI.add(cursor.getString(0));
                ut2ETI.add(cursor.getString(1));
                theoryETI.add(cursor.getString(2));

            }
            // Notify the adapter after adding data
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private void DisplayPort(String search) {
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