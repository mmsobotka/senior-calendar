package com.example.senior_calendar_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.onItemListener {

    private TextView monthYearText;
    private RecyclerView recyclerViewCalendar;
    private LocalDate selectDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets() {
        recyclerViewCalendar = findViewById(R.id.recyclerViewCalendar);
        monthYearText = findViewById(R.id.textViewMonthYear);

    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,
                this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager
                (getApplicationContext(), 7);
        recyclerViewCalendar.setLayoutManager(layoutManager);
        recyclerViewCalendar.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;

    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);

    }

    public void previousMonthAction(View view) {
        selectDate = selectDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectDate = selectDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (dayText.equals("")) {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

    }
}