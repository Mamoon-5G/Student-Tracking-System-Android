package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WDPactivity extends AppCompatActivity {

    public Button logVerfiy;
    public EditText getpasscode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdpactivity);
        getSupportActionBar().hide();

        logVerfiy = (Button) findViewById(R.id.verify_wdp);
        getpasscode = (EditText) findViewById(R.id.getpasscode_wdp);


        logVerfiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSearchStudent();
            }
        });

    }

    private void OpenSearchStudent() {

        String passcode = getpasscode.getText().toString();
        if (passcode.equals("")) {
            Toast.makeText(this, "Please Enter pass-code", Toast.LENGTH_SHORT).show();
        } else {
            if (passcode.equals("123456")) {
                Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                OpenWDP();
            } else {
                if (passcode != "123456") {
                    Toast.makeText(this, "Wrong Pass-code", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    private void OpenWDP() {
        Intent i = new Intent(this,SearchWDP.class);
        startActivity(i);
    }
}