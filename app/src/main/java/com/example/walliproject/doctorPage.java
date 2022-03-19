package com.example.walliproject;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class doctorPage extends AppCompatActivity {

    private static doctorAdapterClass appointmentAdapter;

    public static com.example.walliproject.doctorAdapterClass getAppointmentAdapter() {
        return appointmentAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_page);
        DataBaseClass dataBaseObject = new DataBaseClass(getApplicationContext());
        ArrayList<appointment> appointments = new ArrayList<>();
        Cursor c = dataBaseObject.getAllDoctorAppointments(dataBaseObject.getLoggedInUser().getEmail());

        while(!c.isAfterLast()){
            appointment tmp = new appointment();
            tmp.setDoctorMail(c.getString(3));
            tmp.setUserMail(c.getString(2));
            tmp.setStart(parseInt(c.getString(0)));
            tmp.setEnd(parseInt(c.getString(1)));
            appointments.add(tmp);
            c.moveToNext();
        }
        RecyclerView RV = findViewById(R.id.doctorAppointmentsRecyclerView);
        RV.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        appointmentAdapter = new doctorAdapterClass(appointments);
        RV.setAdapter(appointmentAdapter);
    }
}