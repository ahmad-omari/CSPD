package com.gp.cspd.Startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gp.cspd.Database.AccountLoged;
import com.gp.cspd.MainActivity;
import com.gp.cspd.R;
import com.gp.cspd.login.login_page;

import static java.lang.Thread.sleep;

public class splashScreen extends AppCompatActivity {

    TextView c,s,p,d;
    Animation animationC,animationS,animationP,animationD;

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



/*

        c=findViewById(R.id.c);
        s=findViewById(R.id.s);
        p=findViewById(R.id.p);
        d=findViewById(R.id.d);

        animationC = AnimationUtils.loadAnimation(this,R.anim.translation_c);
        animationS = AnimationUtils.loadAnimation(this,R.anim.translation_s);
        animationP = AnimationUtils.loadAnimation(this,R.anim.translation_p);
        animationD = AnimationUtils.loadAnimation(this,R.anim.translation_d);

        c.setAnimation(animationC);
        s.setAnimation(animationS);
        p.setAnimation(animationP);
        d.setAnimation(animationD);


 */
        getSplash();

    }

    public void getSplash(){
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SharedPreferences sharedpreferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
                String rememberedSSN = sharedpreferences.getString("ssn","0");
                if (rememberedSSN.length()==10){
                    AccountLoged loged = AccountLoged.getInstance();
                    loged.setSsn(rememberedSSN);
                    startActivity(new Intent(splashScreen.this, MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(splashScreen.this, login_page.class));
                    finish();
                }
            }
        });
        thread.start();

    }

}
