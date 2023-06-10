package com.example.senior_calendar_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity implements ProjectVariables {
    private Button button_backToMenu;
    private String user_id = "user1";
    private EditText temperature, weight, blood_pressure, sugar_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button_backToMenu = (Button) findViewById(R.id.backFromPomiaryToMenu);
        // Load user input from visual interface MainActivity3
        temperature = (EditText) findViewById(R.id.temperature_input);
        weight = (EditText) findViewById(R.id.weight_input);
        blood_pressure = (EditText) findViewById(R.id.bp_input);
        sugar_level = (EditText) findViewById(R.id.sugar_level);
        //
        button_backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity();
            }
        });
    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void save_user_data(View view) {
        String current_timestamp = this.get_timestamp();
        String temperature = this.temperature.getText().toString();
        String weight = this.weight.getText().toString();
        String blood_pressure = this.blood_pressure.getText().toString();
        String sugar_level = this.sugar_level.getText().toString();
        if (validate_data(temperature, weight, blood_pressure, sugar_level)){
            DataSender post_sender = new DataSender(this);
            post_sender.execute(this.URL, current_timestamp, this.user_id,
                    temperature, weight, blood_pressure, sugar_level);
            Log.i("Saved data:", temperature + " " + weight + " " + blood_pressure + " " + sugar_level);
        } else {
            Log.i("INVALID DATA ERROR", "Incorrect data input");
        }
    }

    private boolean validate_data(String t, String w, String bp, String sl){
        // Validation rules for an input
        int temperature = Integer.valueOf(t);
        int weight = Integer.valueOf(w);
        int blood_pressure = Integer.valueOf(bp);
        int sugar_level = Integer.valueOf(sl);
        if(temperature > 50 || temperature < 10) {
            return false;
        }
        else if (weight < 15){
            return false;
        }
        else if (blood_pressure < 30) {
            return false;
        }
        else if(sugar_level < 10 || sugar_level > 300){
            return false;
        }
        return true;
    }

    private String get_timestamp(){
//        to BE IMPLEMENTED
        return "2023-4-27";
    }
}