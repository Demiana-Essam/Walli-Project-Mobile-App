package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class pendingDoctors extends AppCompatActivity {

    private static pendingListAdapter pendingListAdapter;

    static public com.example.walliproject.pendingListAdapter getPendingListAdapter() {
        return pendingListAdapter;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_doctors);
        DataBaseClass dataBaseObject = new DataBaseClass(getApplicationContext());
        ArrayList<doctors> d = new ArrayList<>();
        Cursor c = dataBaseObject.getAllPendingDoctors();
        d.addAll(dataBaseObject.fetchDoctorsData(c));
        RecyclerView RV = findViewById(R.id.pendingDoctorsRecyclerView);
        RV.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        pendingListAdapter = new pendingListAdapter(d);
        RV.setAdapter(pendingListAdapter);
    }

}