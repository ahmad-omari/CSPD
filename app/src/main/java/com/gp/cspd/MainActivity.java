package com.gp.cspd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.gp.cspd.mainFragments.forms;
import com.gp.cspd.mainFragments.mapLocation;
import com.gp.cspd.mainFragments.profile;
import com.gp.cspd.mainFragments.services;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    private TextView headerUserName;
    private TextView headerEmail;
    private CircleImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new services()).commit();

            navigationView.setCheckedItem(R.id.servises);
        }


        // navigation header view
        View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header);
        profile_image =(CircleImageView)navHeaderView.findViewById(R.id.profile_image);
        headerUserName = (TextView) navHeaderView.findViewById(R.id.header_username);
        headerEmail = (TextView) navHeaderView.findViewById(R.id.header_email);

        profile_image.setImageResource(R.drawable.michael);
        headerUserName.setText("username");
        headerEmail.setText("email@example.com");

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Profile Image",Toast.LENGTH_SHORT).show();
            }
        });
        // end header

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.servises:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new services()).commit();
                break;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new profile()).commit();
                break;

            case R.id.forms:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new forms()).commit();
                break;

            case R.id.locations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new mapLocation()).commit();
                break;


            case R.id.info:
                Toast.makeText(getApplicationContext(),"Info",Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
