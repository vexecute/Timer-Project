package com.example.timerapplication;

public class TimerModel {
    private String duration;
    private String endTime;

    public TimerModel(String duration, String endTime) {
        this.duration = duration;
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getEndTime() {
        return endTime;
    }
}

