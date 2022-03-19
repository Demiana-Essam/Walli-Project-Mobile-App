package com.example.walliproject;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class myAppointments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(myAppointments.this,R.color.AdminHomeColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(myAppointments.this, R.color.AdminNavColor));

        DataBaseClass dataBaseObject = new DataBaseClass(getApplicationContext());
        ArrayList<appointment> appointments = new ArrayList<>();
        Cursor c = dataBaseObject.getAllPatientAppointments(dataBaseObject.getLoggedInUser().getEmail());

        while(!c.isAfterLast()){
            appointment tmp = new appointment();
            tmp.setDoctorMail(c.getString(3));
            tmp.setUserMail(c.getString(2));
            tmp.setStart(parseInt(c.getString(0)));
            tmp.setEnd(parseInt(c.getString(1)));
            appointments.add(tmp);
            c.moveToNext();
        }
        RecyclerView RV = findViewById(R.id.myAppointmentsRecyclerView);
        RV.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        myAppointmentsAdapter appointmentAdapter = new myAppointmentsAdapter(appointments);
        RV.setAdapter(appointmentAdapter);
    }
}