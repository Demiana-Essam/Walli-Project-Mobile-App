package com.example.walliproject;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class bookAnAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_an_appointment);

        ArrayList<Integer> timesAvailable = new ArrayList<>();
        DataBaseClass dataBaseClass = new DataBaseClass(getApplicationContext());

        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(bookAnAppointment.this, R.color.modeDetailsStatusColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(bookAnAppointment.this, R.color.AdminNavColor));

        //getting the variables from previous activity
        String doctorEmail=getIntent().getStringExtra("doctorEmail");
        String workingHoursStart=getIntent().getStringExtra("workingHoursStart");
        String workingHoursEnd=getIntent().getStringExtra("workingHoursEnd");
        //define the layout
        Button confirmButton = findViewById(R.id.conferm);
        Spinner fromSpinner = findViewById(R.id.appointmentTimeSpinner);
        for(int i = parseInt(workingHoursStart);i <= parseInt(workingHoursEnd);i++){
            timesAvailable.add(i);
        }
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,timesAvailable);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(spinnerArrayAdapter);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataBaseClass.addNewAppointment(parseInt(fromSpinner.getSelectedItem().toString()),
                        parseInt(fromSpinner.getSelectedItem().toString())+1,
                        dataBaseClass.getLoggedInUser().getEmail(),
                        doctorEmail,
                        0);
                Intent intent = new Intent(getApplicationContext(),homePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}