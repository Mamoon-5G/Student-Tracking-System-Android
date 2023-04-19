package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subjects extends AppCompatActivity {
    
    public Button mad,wdp,pwp,eti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        getSupportActionBar().hide();
        
        
        mad=(Button)findViewById(R.id.mad);
        wdp=(Button)findViewById(R.id.wdp);
        pwp=(Button)findViewById(R.id.pwp);
        eti=(Button)findViewById(R.id.eti);
        
        mad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openmad(); }
        });
        wdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openwdp(); }
        });
        pwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openpwp(); }
        });
        eti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openeti(); }
        });


    }

    private void openeti() {
        Intent i = new Intent(this, ETIactivity.class);
        startActivity(i);
    }

    private void openpwp() {

        Intent i = new Intent(this, PwpActivity.class);
        startActivity(i);

    }

    private void openwdp() {

        Intent i = new Intent(this, WDPactivity.class);
        startActivity(i);
    }

    private void openmad() {
        Intent i = new Intent(this, MadActivity.class);
        startActivity(i);
    }
}