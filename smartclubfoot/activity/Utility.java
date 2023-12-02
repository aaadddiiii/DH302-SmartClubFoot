package com.ant.smartclubfoot.activity;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class Utility {

    public static CollectionReference getCollectionReferencefordata()
    {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("patients")
                .document(currentUser.getUid()).collection("data");
    }

    static String ts_to_string(Timestamp timestamp)
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(timestamp.toDate());
    }

}
