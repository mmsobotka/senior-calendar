package com.example.senior_calendar_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class CalendarAdapter extends RecyclerView.Adapter<com.example.senior_calendar_app.CalendarViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final onItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> daysOfMonth, CalendarAdapter.onItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public com.example.senior_calendar_app.CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new com.example.senior_calendar_app.CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.senior_calendar_app.CalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface onItemListener {
        void onItemClick(int position, String dayText);
    }
}