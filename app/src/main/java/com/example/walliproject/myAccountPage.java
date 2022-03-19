package com.example.walliproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class myAccountPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_page);

        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(myAccountPage.this, R.color.modeDetailsStatusColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(myAccountPage.this, R.color.AdminNavColor));

        //variables
        final  DataBaseClass DataBaseObject = new DataBaseClass(this);
        EditText fullName=(EditText) findViewById(R.id.myaccountFullName);
        EditText oldPassword=(EditText) findViewById(R.id.myaccountOldPassword);
        EditText newPassword=(EditText) findViewById(R.id.myaccountNewPassword);
        Button updateData =(Button) findViewById(R.id.updateYourDataBtn);

        fullName.setText(DataBaseObject.getLoggedInUser().getName());
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(oldPassword.getText().toString().equals("") & newPassword.getText().toString().equals("")){
                    DataBaseObject.UpdatePersonalData(fullName.getText().toString(),DataBaseObject.getLoggedInUser().getPassword());
                    //makeText(myAccountPage.this,"Your Name Updated Successfully",Toast.LENGTH_LONG).show();
                    openDialog("Your Name Updated Successfully");
                }
                else if (!oldPassword.getText().toString().equals(DataBaseObject.getLoggedInUser().getPassword())){
                    Toast.makeText(myAccountPage.this,"Old password does not match, Please try again",Toast.LENGTH_LONG).show();
                }
                else{
                    DataBaseObject.UpdatePersonalData(fullName.getText().toString(),newPassword.getText().toString());
                    //Toast.makeText(myAccountPage.this,"Your Data Updated Successfully",Toast.LENGTH_LONG).show();
                    openDialog("Your Data Updated Successfully");
                }
            }
        });

    }
    public void openDialog(String massage){
        PopUpMessage dialog =new PopUpMessage("Personal Information",massage,"Sign Out",getApplicationContext());
        dialog.show(getSupportFragmentManager(),"Update");
    }

}