package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginBtn=(Button) findViewById(R.id.loginPageBtn);
        Button registerBtn=(Button) findViewById(R.id.registerPageBtn);
        final  DataBaseClass DataBaseObject = new DataBaseClass(this);

        //status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.purple_500));
        getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity.this, R.color.WelcomeNavColor));

        //Navigate to Login Page
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginForm.class);
                startActivity(intent);
            }
        });

        //Navigate to Register Page
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),registerForm.class);
                startActivity(intent);
            }

        });
    }
}