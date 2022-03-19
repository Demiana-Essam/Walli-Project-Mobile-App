package com.example.walliproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class homeFragment extends Fragment {
    DataBaseClass dataBaseClass;
    TextView welcomingUser;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home,container,false);
        dataBaseClass = new DataBaseClass(getContext());
        welcomingUser=(TextView) view.findViewById(R.id.welcomingTheUser);
        welcomingUser.setText("Welcome "+ dataBaseClass.getLoggedInUser().getName());
        Button logout= (Button) view.findViewById(R.id.userLogoutBtn);
        Button credits=(Button) view.findViewById(R.id.aboutUsBtn);
        Button myAccount=(Button)view.findViewById(R.id.myAccountBtn);
        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),myAccountPage.class);
                startActivity(intent);
            }
        });


        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),creditsPage.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });



        // Inflate the layout for this fragment
        return view;
    }


}