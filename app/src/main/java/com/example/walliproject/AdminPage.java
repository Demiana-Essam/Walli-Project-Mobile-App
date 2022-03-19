package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        initialize_the_design();
        Button pending = findViewById(R.id.pendingBtn);
        Button logout=(Button)findViewById(R.id.adminLogoutBtn);
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),pendingDoctors.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }


    public void initialize_the_design(){
        //initializing the variables
        final  DataBaseClass DataBaseObject = new DataBaseClass(this);
        TextView users=(TextView)findViewById(R.id.usersNum);
        TextView doctors=(TextView)findViewById(R.id.doctorsNum);


        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(AdminPage.this,R.color.AdminHomeColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(AdminPage.this, R.color.AdminNavColor));

        //set values
        //Toast.makeText(AdminPage.this, "Login done, Doctor Type", Toast.LENGTH_LONG).show();
        users.setText(String.valueOf(DataBaseObject.getAdminStatictics()[0]));
        doctors.setText(String.valueOf(DataBaseObject.getAdminStatictics()[1]));

    }
}