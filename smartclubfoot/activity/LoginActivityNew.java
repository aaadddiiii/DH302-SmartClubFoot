package com.ant.smartclubfoot.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.smartclubfoot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivityNew extends AppCompatActivity {

    EditText emailedittext,passwordedittext;
    Button loginbutton;
    Button skiploginbtn;
    TextView createacctext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        emailedittext    = findViewById(R.id.email);
        passwordedittext = findViewById(R.id.password_login);
        loginbutton   = findViewById(R.id.loginbutton);
        createacctext = findViewById(R.id.createacc);
        skiploginbtn = findViewById(R.id.skipbutton);

        loginbutton.setOnClickListener((v -> loginuser()));
        createacctext.setOnClickListener((v -> createacctextclick()));
        skiploginbtn.setOnClickListener((v -> skiploginfn()));
    }

    public void createacctextclick()
    {
        startActivity(new Intent(LoginActivityNew.this,CreateAccountActivity.class));
        //Toast.makeText(this, "Work in Progress :)", Toast.LENGTH_SHORT).show();
    }

    public void loginuser()
    {

        String semail = emailedittext.getText().toString();
        String spassword = passwordedittext.getText().toString();

        boolean isvalid = validatedata(semail,spassword);
        if(!isvalid) {return;}

        loginAccountinFirebase(semail,spassword);

    }
    public void skiploginfn()
    {
        Toast.makeText(LoginActivityNew.this, "Logged In Without User", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivityNew.this,ConnectDeviceActivity.class));
        finish();
    }

    public void loginAccountinFirebase(String email, String password)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    // TODO the follwing was changed so that user need not be verified
//                     if(firebaseAuth.getCurrentUser().isEmailVerified())
//                     {
//                         //go to main
//                         Toast.makeText(LoginActivity.this, "Sucessful1", Toast.LENGTH_SHORT).show();
//                         startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                         finish();
//                     }
//                     else{
//                         Toast.makeText(LoginActivity.this, "Email Not Verified", Toast.LENGTH_SHORT).show();
//                     }
                    Toast.makeText(LoginActivityNew.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivityNew.this,ConnectDeviceActivity.class));
                    finish();
                }
                else
                {
                    //login failed
                    Toast.makeText(LoginActivityNew.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    boolean validatedata(String email, String password)
    {
        if(email.isEmpty()  || password.isEmpty())
        {

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailedittext.setError("Invalid Email");
            return false;
        }
        if(password.length() < 6)
        {
            passwordedittext.setError("Invalid Password");
            //Toast.makeText(this, "Please enter password of length more than 6", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}