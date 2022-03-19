package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class registerForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        Button registerBtn=(Button) findViewById(R.id.registerBtn);
        EditText fName=(EditText) findViewById(R.id.registerNameinput);
        EditText email=(EditText) findViewById(R.id.registerEmailinput);
        EditText password=(EditText) findViewById(R.id.registerPasswordinput);
        EditText rePassword=(EditText) findViewById(R.id.registerRePasswordinput);
        CheckBox isDoctor=(CheckBox) findViewById(R.id.isDoctorinput);
        final  DataBaseClass DataBaseObject = new DataBaseClass(this);

        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(registerForm.this,R.color.registerPageColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(registerForm.this, R.color.registerNavColor));

        //get the input data and store it in the database
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().equals("")|| password.getText().toString().equals("") || rePassword.getText().toString().equals("")||fName.getText().toString().equals("")){
                    Toast.makeText(registerForm.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
                }
                else if (!password.getText().toString().equals(rePassword.getText().toString())){
                    Toast.makeText(registerForm.this,"password doesn't match",Toast.LENGTH_LONG).show();
                }
                else if (DataBaseObject.exist(email.getText().toString())){
                    Toast.makeText(registerForm.this,"this Email already exist, Try to login",Toast.LENGTH_LONG).show();
                }
                else{
                    if(isDoctor.isChecked()){
                        Intent intent= new Intent(getApplicationContext(),doctorRegisterForm.class);
                        intent.putExtra("fname",fName.getText().toString());
                        intent.putExtra("userEmail",email.getText().toString());
                        intent.putExtra("password",password.getText().toString());
                        startActivity(intent);
                    }
                    else{
                        DataBaseObject.AddNewUser(fName.getText().toString(),email.getText().toString(),password.getText().toString(),0);
                        Toast.makeText(registerForm.this,"Register done successfully",Toast.LENGTH_LONG).show();

                        fName.getText().clear();
                        email.getText().clear();
                        password.getText().clear();
                        rePassword.getText().clear();
                    }

                }
            }
        });

        //change button text
        isDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDoctor.isChecked()){
                    registerBtn.setText("Fill the information");
                }
                else if (!isDoctor.isChecked()){
                    registerBtn.setText("Register");
                }

            }
        });
    }
}