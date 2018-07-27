package com.example.nicky.gymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button profile,session,instructors,gyms;
    TextView emailtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i=getIntent();
        String email=i.getExtras().getString("email","");
        emailtxt=(TextView)findViewById(R.id.txtEmail);
        emailtxt.setText(email);

        profile=(Button)findViewById(R.id.profile);
        session=(Button)findViewById(R.id.session);
        instructors=(Button)findViewById(R.id.instructors);
        gyms=(Button)findViewById(R.id.gyms);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                String email=emailtxt.getText().toString();
                intent.putExtra("email",email);
                startActivity(intent);

            }
        });

        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SessionActivity.class);
                String email=emailtxt.getText().toString();
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

        instructors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InstructorActivity.class);
                startActivity(intent);
            }
        });

        gyms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsFragment.class);
                startActivity(intent);
            }
        });
    }



}
