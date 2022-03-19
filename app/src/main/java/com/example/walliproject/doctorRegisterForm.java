package com.example.walliproject;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class doctorRegisterForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register_form);

        final  DataBaseClass DataBaseObject = new DataBaseClass(this);
        Button registerBtn=(Button)findViewById(R.id.registerDoctorBtn);
        Spinner specialty=(Spinner)findViewById(R.id.registerSpecialtySpinner);
        Spinner fromTime=(Spinner)findViewById(R.id.fromTimeSpinner);
        Spinner toTime=(Spinner)findViewById(R.id.toTimeSpinner);
        Spinner region=(Spinner)findViewById(R.id.regionSpinner);
        EditText fees=(EditText)findViewById(R.id.feesinput);

        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(doctorRegisterForm.this,R.color.registerPageColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(doctorRegisterForm.this, R.color.registerNavColor));

        //getting data from previous Activity
        String fname= getIntent().getStringExtra("fname");
        String userEmail= getIntent().getStringExtra("userEmail");
        String password= getIntent().getStringExtra("password");

        //Register Button Action
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromTimeDate = fromTime.getSelectedItem().toString().replaceAll("\\D+","");
                String toTimeDate = toTime.getSelectedItem().toString().replaceAll("\\D+","");

                if(fees.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter your Fees",Toast.LENGTH_LONG).show();
                }
                else{
                    DataBaseObject.AddNewUser(fname,userEmail,password,2);
                    DataBaseObject.AddNewDoctor(specialty.getSelectedItem().toString(),region.getSelectedItem().toString(),fromTimeDate,toTimeDate,userEmail,Integer.parseInt(fees.getText().toString()),String.valueOf(3));
                    openDialog();
                }
            }
        });
    }
    public void openDialog(){
        PopUpMessage dialog =new PopUpMessage("Registration Done","You are pending now until the Admin Approve your request","Home",getApplicationContext());
        dialog.show(getSupportFragmentManager(),"Adnan");
    }
}