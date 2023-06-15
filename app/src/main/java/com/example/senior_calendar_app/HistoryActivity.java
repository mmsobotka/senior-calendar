package com.example.senior_calendar_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HistoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, ProjectVariables {

    private Button button_notes;
    private Button back_to_menu;
    private Button back_to_parametry;
    private String [] parameters_pl = {"Ciśnienie krwi", "Poziom cukru", "Waga", "Temperatura", "Tętno", "Saturacja"};
    private String [] parameter_eng = {"BLOOD_PRESSURE", "SUGAR_LEVEL", "WEIGHT", "TEMPERATURE", "HEART_RATE", "SATURATION"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_window);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        button_notes = (Button) findViewById(R.id.notesButton);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, parameters_pl);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected_column = this.parameter_eng[position];
        DataReceiver data_receiver = new DataReceiver(this);
        data_receiver.execute(this.GetDataURL, selected_column, this.RECEIVE_DATA_RANGE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}