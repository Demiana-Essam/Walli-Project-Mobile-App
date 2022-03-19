package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class doctorMoreDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_more_details);

        //Declaring the variables
        final  DataBaseClass DataBaseObject = new DataBaseClass(this);
        String doctorEmail=getIntent().getStringExtra("email");
        Cursor doctorData=DataBaseObject.getDoctorInfo(doctorEmail);
        TextView doctorMoreInfoName=(TextView)findViewById(R.id.doctorMoreInfoName) ;
        TextView doctorMoreInfoSpecialty=(TextView)findViewById(R.id.doctorMoreInfoSpecialty) ;
        TextView doctorMoreInfoFees=(TextView)findViewById(R.id.doctorMoreInfoFees) ;
        TextView doctorMoreInfoLocation=(TextView)findViewById(R.id.doctorMoreInfoLocation) ;
        TextView doctorMoreInfoWorkingDates=(TextView)findViewById(R.id.doctorMoreInfoWorkingDates) ;
        RatingBar doctorMoreInfoRatingBar=(RatingBar)findViewById(R.id.doctorMoreInfoRatingBar);
        Button bookBtn=(Button) findViewById(R.id.doctorInfoBookBtn);

        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(doctorMoreDetails.this, R.color.modeDetailsStatusColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(doctorMoreDetails.this, R.color.AdminNavColor));

        //set the EditText Values
        doctorMoreInfoRatingBar.setRating(Float.parseFloat(doctorData.getString(6)));
        doctorMoreInfoName.setText(doctorData.getString(0));
        doctorMoreInfoSpecialty.setText("Specialty: "+doctorData.getString(1));
        doctorMoreInfoFees.setText("Fees: "+doctorData.getString(5)+"EGP");
        doctorMoreInfoLocation.setText("RGN: "+doctorData.getString(2));
        String dates="From "+doctorData.getString(3)+" To "+doctorData.getString(4);
        doctorMoreInfoWorkingDates.setText(dates);

        //Booking Button Action
        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),bookAnAppointment.class);
                intent.putExtra("doctorEmail",doctorEmail);
                intent.putExtra("workingHoursStart",doctorData.getString(3));
                intent.putExtra("workingHoursEnd",doctorData.getString(4));
                startActivity(intent);
            }
        });



    }
}