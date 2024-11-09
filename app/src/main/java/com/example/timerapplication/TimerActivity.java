package com.example.timerapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {

    private Button changeSoundButton, viewHistoryButton;
    private MediaPlayer mediaPlayer;

    private static final String PREFS_NAME = "SoundSettingsPrefs";
    private static final String SELECTED_SOUND_KEY = "selectedSound";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Initialize buttons
        changeSoundButton = findViewById(R.id.soundSettingsButton);
        viewHistoryButton = findViewById(R.id.historyButton);

        // Play the default sound when the activity starts
        playDefaultSound();

        // Set up button click listeners
        changeSoundButton.setOnClickListener(v -> navigateToSoundSettings());
        viewHistoryButton.setOnClickListener(v -> navigateToHistory());
    }

    private void playDefaultSound() {
        // Retrieve the sound setting from SharedPreferences, default to sound1
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int selectedSound = sharedPreferences.getInt(SELECTED_SOUND_KEY, R.raw.sound1); // Default is sound1

        // Play the selected sound
        playSound(selectedSound);
    }

    private void playSound(int soundResource) {
        // Stop any currently playing sound
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, soundResource);
        mediaPlayer.start();
    }

    private void navigateToSoundSettings() {
        // Start the SoundSettingsActivity for changing sound
        Intent intent = new Intent(TimerActivity.this, SoundSettingsActivity.class);
        startActivity(intent);
    }

    private void navigateToHistory() {
        // Start the HistoryActivity to view the history
        Intent intent = new Intent(TimerActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the media player resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
