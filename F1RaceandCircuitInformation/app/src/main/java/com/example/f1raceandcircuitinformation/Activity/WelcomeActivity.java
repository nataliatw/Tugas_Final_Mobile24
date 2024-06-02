package com.example.f1raceandcircuitinformation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.f1raceandcircuitinformation.R;

public class WelcomeActivity extends AppCompatActivity {

    TextView tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        tv_next = findViewById(R.id.tv_welcome);

        tv_next.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}