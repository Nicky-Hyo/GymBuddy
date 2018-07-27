package com.example.nicky.gymbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    String emailStored="",passwordStored="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref=getSharedPreferences("loginData",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();

                emailStored=pref.getString("email",null);
                passwordStored=pref.getString("password",null);

                if(emailStored==null){
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    String email=emailStored;
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
                StartActivity.this.finish();
            }
        },3000);
    }
}
