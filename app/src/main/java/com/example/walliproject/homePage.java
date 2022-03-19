package com.example.walliproject;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class homePage extends AppCompatActivity {

    private homeFragment userHomeFragment;
    private doctorList DoctorInfoFragment;
    private ActionBarDrawerToggle drawerBtn;
    private DrawerLayout drawerLayout;
    private final DataBaseClass DataBaseObject = new DataBaseClass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //StatusBar set color
        getWindow().setStatusBarColor(ContextCompat.getColor(homePage.this,R.color.AdminHomeColor));
        getWindow().setNavigationBarColor(ContextCompat.getColor(homePage.this, R.color.AdminNavColor));

        DoctorInfoFragment = new doctorList();
        userHomeFragment = new homeFragment();
        Toolbar toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.menuDrawerLayout);
        drawerBtn = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerBtn);
        drawerBtn.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        NavigationView navView = findViewById(R.id.navView);
        TextView txt1 = navView.getHeaderView(0).findViewById(R.id.userFullName);
        txt1.setText(DataBaseObject.getLoggedInUser().getName());
        replaceFragment(userHomeFragment);



        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item1){
                    replaceFragment(userHomeFragment);
                }
                else if(item.getItemId() == R.id.item2){
                    replaceFragment(DoctorInfoFragment);
                }
                else if(item.getItemId() == R.id.appointments){
                    Intent intent = new Intent(getApplicationContext(),myAppointments.class);
                    startActivity(intent);
                }
                else if(item.getItemId() == R.id.credits){
                    Toast.makeText(homePage.this, "you are in 3", Toast.LENGTH_SHORT).show();
                }

                else if(item.getItemId() == R.id.accountSetting){
                    Intent intent = new Intent(getApplicationContext(),myAccountPage.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment,fragment);
        fragmentTransaction.commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerBtn.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}