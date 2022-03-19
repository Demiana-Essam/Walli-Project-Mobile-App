package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        Button loginBtn=(Button) findViewById(R.id.loginBtn);
        EditText email=(EditText) findViewById(R.id.loginEmailinput);
        EditText password=(EditText) findViewById(R.id.loginPasswordinput);
        final  DataBaseClass DataBaseObject = new DataBaseClass(this);

        //NavBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(LoginForm.this, R.color.LoginstatColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(LoginForm.this, R.color.LoginNavColor));

        //login check
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().equals("admin") & password.getText().toString().equals("admin")){
                    email.getText().clear();
                    password.getText().clear();
                    Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                    startActivity(intent);

                }
                else {
                    int isLogin = DataBaseObject.loginCheck(email.getText().toString(), password.getText().toString());
                    if (isLogin == 1) {
                        Toast.makeText(LoginForm.this, "Login done, User Type", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), homePage.class);
                        startActivity(intent);
                        email.getText().clear();
                        password.getText().clear();
                    } else if (isLogin == 2) {
                        email.getText().clear();
                        password.getText().clear();
                        Intent intent = new Intent(getApplicationContext(), doctorPage.class);
                        startActivity(intent);

                    } else if (isLogin == 3) {
                        email.getText().clear();
                        password.getText().clear();
                        Toast.makeText(LoginForm.this, "Your account is Pending to be accepted by one of our admins", Toast.LENGTH_LONG).show();
                    } else if (isLogin == 4) {
                        email.getText().clear();
                        password.getText().clear();
                        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginForm.this, "invalid Email or Password", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }
}