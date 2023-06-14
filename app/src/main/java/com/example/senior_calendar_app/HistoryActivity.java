package com.example.senior_calendar_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    private Button button_notes;
    private Button back_to_menu;
    private Button back_to_parametry;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_window);

        button_notes = (Button) findViewById(R.id.notesButton);
        button_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotesActivity();
            }
        });

        back_to_menu = (Button) findViewById(R.id.menu_buuton2);
        back_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity();
            }
        });

        back_to_parametry = (Button) findViewById(R.id.pomiaryButton);
        back_to_parametry.setOnClickListener(new View.OnClickListener() {
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

    public void openPomiaryActivity() {
        Intent intent = new Intent(this, MeasuresActivity.class);
        startActivity(intent);
    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
