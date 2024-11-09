package com.example.timerapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimerHistoryAdapter extends RecyclerView.Adapter<TimerHistoryAdapter.ViewHolder> {
    private List<TimerModel> timerList;

    public TimerHistoryAdapter(List<TimerModel> timerList) {
        this.timerList = timerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timer_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimerModel timer = timerList.get(position);
        holder.durationTextView.setText("Duration: " + timer.getDuration());
        holder.endTimeTextView.setText("Ended: " + timer.getEndTime());
    }

    @Override
    public int getItemCount() {
        return timerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView durationTextView, endTimeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            durationTextView = itemView.findViewById(R.id.textViewDuration);
            endTimeTextView = itemView.findViewById(R.id.textViewEndTime);
        }
    }
}

