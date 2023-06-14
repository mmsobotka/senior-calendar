package com.example.senior_calendar_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity {


    private Button button_calendar;
    private Button button_menu;
    private Button button_parametry;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        button_calendar = (Button) findViewById(R.id.calenButton);
        button_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarActivity();
            }
        });

        button_menu = (Button) findViewById(R.id.menuButton2);
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenuActivity();
            }
        });

        button_parametry = (Button) findViewById(R.id.pomiaryButton2);
        button_parametry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPomiaryActivity();
            }
        });
    }

    public void backToMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


    public void openCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void openPomiaryActivity() {
        Intent intent = new Intent(this, MeasuresActivity.class);
        startActivity(intent);
    }
}
