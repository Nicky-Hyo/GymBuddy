package com.example.nicky.gymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.md5simply.MD5;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class SessionActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "SessionActivity";
    TextView emailtxt;
    EditText locationtxt,datetxt,exercisetxt,setstxt,repstxt;
    Button addsession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        Intent i=getIntent();
        String email=i.getExtras().getString("email","");
        emailtxt=(TextView)findViewById(R.id.txtEmail);
        emailtxt.setText(email);

        locationtxt=(EditText)findViewById(R.id.edtLocation);
        datetxt=(EditText)findViewById(R.id.edtDate);
        exercisetxt=(EditText)findViewById(R.id.edtExercise);
        setstxt=(EditText)findViewById(R.id.edtSets);
        repstxt=(EditText)findViewById(R.id.edtReps);

        addsession=(Button)findViewById(R.id.addSession);
        addsession.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!emptyValidate( locationtxt,datetxt,exercisetxt, setstxt, repstxt)){
            {
                String email=emailtxt.getText().toString();
                String location=locationtxt.getText().toString();
                String date=datetxt.getText().toString();
                String exercise = exercisetxt.getText().toString();
                String sets = setstxt.getText().toString();
                String reps=repstxt.getText().toString();

                HashMap<String, String> postData = new HashMap<>();
                postData.put("email",email);
                postData.put("location",location);
                postData.put("date",date);
                postData.put("exercise", exercise);
                postData.put("sets", sets);
                postData.put("reps",reps);
                PostResponseAsyncTask task1 = new PostResponseAsyncTask(this,
                        postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        Log.d(TAG, s);
                        if(s.contains("ErrorInsert")){
                            Toast.makeText(SessionActivity.this,
                                    "Something went wrong. Data was not inserted.",
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Intent in = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(in);
                        }
                    }
                });
                task1.execute("https://bnatalia45.000webhostapp.com/092209_Api/session.php");
            }

        } else{
            Toast.makeText(getApplicationContext(), "Fill out all the fields",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean emptyValidate(EditText locationtxt,EditText datetxt,EditText exercisetxt,
                                  EditText setstxt,
                                  EditText repstxt){
        String location=locationtxt.getText().toString();
        String date=datetxt.getText().toString();
        String exercise = exercisetxt.getText().toString();
        String sets = setstxt.getText().toString();
        String reps = repstxt.getText().toString();
        return (location.isEmpty()&&date.isEmpty()&&exercise.isEmpty() && sets.isEmpty() && reps.isEmpty());
    }
}
