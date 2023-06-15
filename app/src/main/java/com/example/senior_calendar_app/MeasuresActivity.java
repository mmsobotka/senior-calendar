package com.example.senior_calendar_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MeasuresActivity extends AppCompatActivity implements ProjectVariables {

    private String user_id = "user1";
    private EditText temperature, weight, blood_pressure, sugar_level, heart_rate, saturation;

    private Button button_notes;
    private Button button_backToMenu;
    private Button button_goToHistory;

    String error_msg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.measures_window);

        button_backToMenu = (Button) findViewById(R.id.button_backMenu);
        // Load user input from visual interface MainActivity3
        temperature = (EditText) findViewById(R.id.temperature_input);
        sugar_level = (EditText) findViewById(R.id.sugar_level_input);
        weight = (EditText) findViewById(R.id.weight_input);
        blood_pressure = (EditText) findViewById(R.id.bp_input);
        heart_rate = (EditText) findViewById(R.id.hr_input);
        saturation = (EditText) findViewById(R.id.saturation_input);
        //
        button_backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity();
            }
        });

        button_notes = (Button) findViewById(R.id.notesButton);
        button_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotesActivity();
            }
        });

        button_goToHistory = (Button) findViewById(R.id.pomiaryHistoria);
        button_goToHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryActivity();
            }
        });
    }

    public void openNotesActivity() {
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void openHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void save_user_data(View view) {
        String current_timestamp = this.get_timestamp();
        String temperature = this.temperature.getText().toString();
        String sugar_level = this.sugar_level.getText().toString();
        String weight = this.weight.getText().toString();
        String blood_pressure = this.blood_pressure.getText().toString();
        String heart_rate  = this.heart_rate .getText().toString();
        String saturation   = this.saturation  .getText().toString();
        if (validate_data(temperature, sugar_level, weight, blood_pressure, heart_rate, saturation)) {
            DataSender post_sender = new DataSender(this);
            post_sender.execute(this.URL, current_timestamp, this.user_id,
                    temperature, sugar_level, weight, blood_pressure, heart_rate, saturation);
            Log.i("Saved data:", temperature + " " + sugar_level + " " + weight + " " + blood_pressure + " " + heart_rate + " " + saturation);
        } else {
            Log.i("INVALID DATA ERROR", "Incorrect data input");
        }
    }

    private boolean validate_data(String t, String sl, String w, String bp, String hr, String sat){
        // Validation rules for an input
        String message = "";
        int temperature = Integer.valueOf(t);
        int sugar_level = Integer.valueOf(sl);
        int weight = Integer.valueOf(w);
        int blood_pressure = Integer.valueOf(bp);
        int heart_rate = Integer.valueOf(hr);
        int saturation = Integer.valueOf(sat);

        if(temperature > 37 || temperature < 34) {
            message = "Wartość temperatury poza granicami normy.";

        }
        else if (weight < 52 || weight > 72){
            message = "Wartość wagi poza granicami normy.";
            return false;
        }
        else if (blood_pressure < 120 || blood_pressure > 129) {
            message = "Wartość ciśnienia krwi poza granicami normy.";
            return false;
        }
        else if(sugar_level < 70 || sugar_level > 99){
            message = "Wartość poziomu cukru poza granicami normy.";
            return false;
        }
        this.error_msg = message;
        if (message.length() > 1) {
            message += " Skontaktuj się z lekarzem.";
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Ostrzeżenie")
                    .setMessage(message)
                    .setNegativeButton("Zamknij ostrzeżenie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(),"Powtórz pomiar",Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
            return false;
        } else {
            return true;
        }
    }

    private String get_timestamp(){
        return "2023-4-27";
    }
}