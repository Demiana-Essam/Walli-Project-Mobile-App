package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class creditsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_page);

        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(creditsPage.this, R.color.modeDetailsStatusColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(creditsPage.this, R.color.AdminNavColor));




    }
}