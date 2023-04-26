package com.example.senior_calendar_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    private Button button_backToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button_backToMenu = (Button) findViewById(R.id.backFromPomiaryToMenu);
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
}