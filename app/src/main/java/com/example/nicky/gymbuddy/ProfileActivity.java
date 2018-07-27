package com.example.nicky.gymbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.md5simply.MD5;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView emailtxt;
    final String TAG = "RegisterActivity";
    EditText etEmail, etPassword, etConfirmPassword,etFirstName,etLastName;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent i=getIntent();
        String email=i.getExtras().getString("email","");
        emailtxt=(TextView)findViewById(R.id.txtEmail);
        emailtxt.setText(email);



        etFirstName=(EditText)findViewById(R.id.etFirstName);
        etLastName=(EditText)findViewById(R.id.etLastName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!emptyValidate( etFirstName, etLastName, etEmail, etPassword, etConfirmPassword)){

                String firstname=etFirstName.getText().toString();
                String lastname=etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                HashMap<String, String> postData = new HashMap<>();
                postData.put("firstname",firstname);
                postData.put("lastname",lastname);
                postData.put("email", email);
                postData.put("password", password);

                PostResponseAsyncTask task1 = new PostResponseAsyncTask(this,
                        postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        Log.d(TAG, s);
                        if(s.contains("ErrorInsert")){
                            Toast.makeText(ProfileActivity.this,
                                    "Something went wrong. Data was not inserted.",
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Intent in = new Intent(getApplicationContext(),
                                    LoginActivity.class);
                            startActivity(in);
                        }
                    }
                });
                task1.execute("https://bnatalia45.000webhostapp.com/092209_Api/profile.php");
            }

         else{
            Toast.makeText(getApplicationContext(), "Fill out all the fields",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean emptyValidate(EditText etFirstName,EditText etLastName,EditText etEmail,
                                  EditText etPassword,
                                  EditText etConfirmPassword){
        String firstname=etFirstName.getText().toString();
        String lastname=etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();
        return (firstname.isEmpty()&&lastname.isEmpty()&&email.isEmpty() && password.isEmpty() && confirm.isEmpty());
    }


}


