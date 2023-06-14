package com.example.senior_calendar_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button button_notes;
    private Button button_calendar;
    private Button button_parametry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        button_notes = (Button) findViewById(R.id.notesButton);
        button_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotesActivity();
            }
        });

        button_calendar = (Button) findViewById(R.id.calendarButton);
        button_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarActivity();
            }
        });

        button_parametry = (Button) findViewById(R.id.pomiaryButton);
        button_parametry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPomiaryActivity();
            }
        });
    }

    public void openNotesActivity() {
        Intent intent = new Intent(this, NotesActivity.class);
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