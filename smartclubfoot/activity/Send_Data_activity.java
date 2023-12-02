package com.ant.smartclubfoot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ant.smartclubfoot.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ant.smartclubfoot.datastore.DateHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.Timestamp;

public class Send_Data_activity extends AppCompatActivity {


    Button sendtocloudbtn;
    EditText leftleg;
    EditText rightleg;
    EditText leftshoe;
    EditText rightshoe;
    private DateHelper dateHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        dateHelper = new DateHelper();
        sendtocloudbtn = findViewById(R.id.send_to_cloud_btn2);

        leftleg = findViewById(R.id.leftlegtext);
        leftshoe = findViewById(R.id.leftshoetext);
        rightleg = findViewById(R.id.rightlegtext);
        rightshoe = findViewById(R.id.rightshoetext);

        sendtocloudbtn.setOnClickListener((v -> send_data_to_cloud(dateHelper.getCurrentDate())));
    }

    public void send_data_to_cloud(String date)
    {
        String date_send = date;

        String leftlsend = leftleg.getText().toString();
        String rightlsend = rightleg.getText().toString();
        String leftssend = leftshoe.getText().toString();
        String rightssend = rightshoe.getText().toString();

        // Sanity checks
        if(leftlsend == null || leftlsend.isEmpty())
        {
            leftleg.setError("Please Fill");
            return;
        }
        if(rightlsend == null || rightlsend.isEmpty())
        {
            rightleg.setError("Please Fill");
            return;
        }
        if(leftssend == null || leftssend.isEmpty())
        {
            leftshoe.setError("Please Fill");
            return;
        }
        if(rightssend == null || rightssend.isEmpty())
        {
            rightshoe.setError("Please Fill");
            return;
        }

        Cloud_Data cloud_data =new Cloud_Data();

        cloud_data.setLeftLeg(leftlsend);
        cloud_data.setRightLeg(rightlsend);
        cloud_data.setLeftShoe(leftssend);
        cloud_data.setRightShoe(rightssend);
        cloud_data.setTimestamp(Timestamp.now());

        save_to_firebase(cloud_data);
        finish();
    }

    public void save_to_firebase(Cloud_Data cloud_data)
    {
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferencefordata().document();
        documentReference.set(cloud_data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Send_Data_activity.this, "Upload Succesful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Send_Data_activity.this, "Failed to add note", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}