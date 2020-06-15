package com.gp.cspd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gp.cspd.Database.AccountLoged;
import com.gp.cspd.Database.ImageHandler;
import com.gp.cspd.login.login_page;
import com.gp.cspd.mainFragments.forms;
import com.gp.cspd.mainFragments.mapLocation;
import com.gp.cspd.mainFragments.profile;
import com.gp.cspd.mainFragments.services;
import com.gp.cspd.signUp.signUp;

import java.io.File;
import java.io.IOException;

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

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        Toolbar toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
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

        getImageDB();
        getUserNameDB();
        getEmailDB();
/*
        ImageHandler handler = ImageHandler.getInstance();
        //profile_image.setImageResource(R.drawable.michael);
        if (handler.getBitmap() != null) {
            profile_image.setImageBitmap(handler.getBitmap());
            Log.e("LOG3","Image value");
        }else {
            Log.e("LOG4","Image Null");
        }
        headerUserName.setText("username");
        headerEmail.setText("email@example.com");

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Profile Image",Toast.LENGTH_SHORT).show();
            }
        });
        // end header


 */
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
                displayInfo();
                break;

            case R.id.about:
                displayAbout();
                break;

            case R.id.logout:
                SharedPreferences sharedpreferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ssn", "0");
                editor.commit();
                logOutUser();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayAbout() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.about_dialog);
        Button button=dialog.findViewById(R.id.retok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void displayInfo() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.info_dialog);
        Button button=dialog.findViewById(R.id.retok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void logOutUser() {
        startActivity(new Intent(MainActivity.this,login_page.class));
        finish();
    }

    public void getImageDB(){
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "UserImageFolder/"+al.getSsn()+"profilePicture.jpg";
        StorageReference reference = FirebaseStorage.getInstance().getReference().child(strRef);
        try {
            final File file = File.createTempFile(al.getSsn()+"profilePicture","jpg");
            reference.getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.e("LOG1","Success retival image");
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            if (bitmap != null){
                                profile_image.setImageBitmap(bitmap);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("LOG2","Filed retival image");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getEmailDB() {
        DatabaseReference mDatabase;
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/email";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String email = (String) dataSnapshot.getValue();

                if (email != null){
                    headerEmail.setText(email);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getUserNameDB() {
        DatabaseReference mDatabase;
        AccountLoged al = AccountLoged.getInstance();
        String strRef = "users/"+al.getSsn()+"/englishName";
        mDatabase = FirebaseDatabase.getInstance().getReference(strRef);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = (String) dataSnapshot.getValue();

                if (username != null){
                    headerUserName.setText(username);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Error try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
