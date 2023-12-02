package com.ant.smartclubfoot.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ant.smartclubfoot.R;

public class ViewData_activity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        recyclerView = findViewById(R.id.recyclerview);
        setupRecyclerView();

    }


    void setupRecyclerView()
    {

    }

}