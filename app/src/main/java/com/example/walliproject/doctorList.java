package com.example.walliproject;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class doctorList extends Fragment {
    private listAdapter.onItemClick listner;
    ArrayList<doctors> d;
    Cursor c;
    String searchedName="";
    String searchedSpecialty="All Specialties";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DataBaseClass DataBaseObject = new DataBaseClass(getContext());
        d = new ArrayList<>();
        c = DataBaseObject.getAllDoctorsInfo("","All Specialties");
        while(!c.isAfterLast()){
            doctors tmp = new doctors();
            tmp.setName(c.getString(0));
            tmp.setSpecialty(c.getString(1));
            tmp.setWorkingHoursStart(parseInt(c.getString(2)));
            tmp.setWorkingHoursEnd(parseInt(c.getString(3)));
            tmp.setUserEmail(c.getString(4));
            d.add(tmp);
            c.moveToNext();
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_doctor_list,container,false);
        EditText search = view.findViewById(R.id.search);
        RecyclerView RV = view.findViewById(R.id.recycleView);
        RV.setLayoutManager(new LinearLayoutManager(this.getContext()));
        setOnClickListner();
        listAdapter listAdapter = new listAdapter(d,listner);
        RV.setAdapter(listAdapter);
        Spinner specialty=(Spinner)view.findViewById(R.id.searchSpecialtySpinner);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchedName=charSequence.toString();
                Cursor c = DataBaseObject.getAllDoctorsInfo(charSequence.toString(),searchedSpecialty);
                d.clear();
                while(!c.isAfterLast()){
                    doctors tmp = new doctors();
                    tmp.setName(c.getString(0));
                    tmp.setSpecialty(c.getString(1));
                    tmp.setWorkingHoursStart(parseInt(c.getString(2)));
                    tmp.setWorkingHoursEnd(parseInt(c.getString(3)));
                    tmp.setUserEmail(c.getString(4));
                    d.add(tmp);
                    c.moveToNext();
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        specialty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchedSpecialty=specialty.getSelectedItem().toString();
                Cursor c = DataBaseObject.getAllDoctorsInfo(searchedName,specialty.getSelectedItem().toString());
                d.clear();
                while(!c.isAfterLast()){
                    doctors tmp = new doctors();
                    tmp.setName(c.getString(0));
                    tmp.setSpecialty(c.getString(1));
                    tmp.setWorkingHoursStart(parseInt(c.getString(2)));
                    tmp.setWorkingHoursEnd(parseInt(c.getString(3)));
                    tmp.setUserEmail(c.getString(4));
                    d.add(tmp);
                    c.moveToNext();
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void setOnClickListner() {
        listner = new listAdapter.onItemClick() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getContext(),doctorMoreDetails.class);
                intent.putExtra("email",d.get(position).getUserEmail());
                startActivity(intent);
            }
        };
    }
}