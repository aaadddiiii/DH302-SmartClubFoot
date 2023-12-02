package com.ant.smartclubfoot.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.smartclubfoot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CreateAccountActivity extends AppCompatActivity {

    EditText emailedittext,passworedittext,fullnameedittext,mobileedittext;
    Button registerbutton;
    TextView loginnowtextview;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailedittext    = findViewById(R.id.ca_email);
        fullnameedittext = findViewById(R.id.ca_fullname);
        passworedittext = findViewById(R.id.ca_password);
        mobileedittext =  findViewById(R.id.ca_mobile);
        registerbutton   = findViewById(R.id.ca_registerbutton);
        loginnowtextview = findViewById(R.id.ca_loginnowtext);


        registerbutton.setOnClickListener(v -> registeruser());
        loginnowtextview.setOnClickListener((v -> loginnowclick()));

    }

    public void loginnowclick()
    {
        startActivity(new Intent(CreateAccountActivity.this,LoginActivityNew.class));
        finish();
    }
    public void registeruser()
    {
        String sfullname = fullnameedittext.getText().toString();
        String semail = emailedittext.getText().toString();
        String spassword = passworedittext.getText().toString();
        String smobile =  mobileedittext.getText().toString();

        if(sfullname.equals("mansi") || sfullname.equals("Mansi"))
        {
            Toast.makeText(this, "Drink Your Water Mansi", Toast.LENGTH_SHORT).show();
        }

        boolean isvalid = validatedata(sfullname,semail,smobile,spassword);
        if(!isvalid)
        {
            return;
        }

        CreateAccountInFireBase(sfullname,semail,smobile,spassword);
        startActivity(new Intent(CreateAccountActivity.this,LoginActivityNew.class));
        finish();

    }

    boolean validatedata(String name, String email, String mobile, String password)
    {
        if(name.isEmpty() || email.isEmpty() || mobile.isEmpty() || password.isEmpty())
        {

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailedittext.setError("Invalid Email");
            return false;
        }
        if(mobile.length()!=10)
        {
            mobileedittext.setError("Invalid Number");
            return false;
        }
        if(password.length() < 6)
        {
            passworedittext.setError("Invalid Password");
            //Toast.makeText(this, "Please enter password of length more than 6", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void CreateAccountInFireBase(String name, String semail, String mobile, String spassword)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(CreateAccountActivity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                    firebaseAuth.getCurrentUser().sendEmailVerification();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName("Jane Q. User")
                            .build();
                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CreateAccountActivity.this, "Update Sucess", Toast.LENGTH_SHORT).show();;
                                    }
                                }
                            });

                    firebaseAuth.signOut();
                }
                else
                {
                    System.out.print(task.getException().getLocalizedMessage());
                    Toast.makeText(CreateAccountActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}