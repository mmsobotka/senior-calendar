package com.example.senior_calendar_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    private Button button_calendr;
    private Button button_pomiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button_calendr = (Button) findViewById(R.id.calendarButton);
        button_calendr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarActivity();
            }
        });

        button_pomiar = (Button) findViewById(R.id.pomiaryButton);
        button_pomiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPomiaryActivity();
            }
        });
    }

    public void openPomiaryActivity() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void openCalendarActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}