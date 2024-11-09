package com.example.timerapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SoundSettingsActivity extends AppCompatActivity {

    private RadioButton radioSound1, radioSound2, radioSound3;
    private Button previewSound1Button, previewSound2Button, previewSound3Button, saveButton;
    private MediaPlayer mediaPlayer;
    private int selectedSound;

    private static final String PREFS_NAME = "SoundSettingsPrefs";
    private static final String SELECTED_SOUND_KEY = "selectedSound";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings);

        radioSound1 = findViewById(R.id.radioSound1);
        radioSound2 = findViewById(R.id.radioSound2);
        radioSound3 = findViewById(R.id.radioSound3);
        previewSound1Button = findViewById(R.id.previewSound1Button);
        previewSound2Button = findViewById(R.id.previewSound2Button);
        previewSound3Button = findViewById(R.id.previewSound3Button);
        saveButton = findViewById(R.id.saveButton);

        // Load the saved sound setting
        loadSelectedSound();

        // Set up preview buttons
        previewSound1Button.setOnClickListener(v -> playSound(R.raw.sound1));
        previewSound2Button.setOnClickListener(v -> playSound(R.raw.sound2));
        previewSound3Button.setOnClickListener(v -> playSound(R.raw.sound3));

        // Save button
        saveButton.setOnClickListener(v -> saveSelectedSound());
    }

    private void playSound(int soundResource) {
        // Stop any currently playing sound
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, soundResource);
        mediaPlayer.start();
    }

    private void loadSelectedSound() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        selectedSound = sharedPreferences.getInt(SELECTED_SOUND_KEY, R.raw.sound1);

        // Set the radio button based on the saved sound setting
        if (selectedSound == R.raw.sound1) {
            radioSound1.setChecked(true);
        } else if (selectedSound == R.raw.sound2) {
            radioSound2.setChecked(true);
        } else if (selectedSound == R.raw.sound3) {
            radioSound3.setChecked(true);
        }
    }

    private void saveSelectedSound() {
        // Save the selected sound based on the checked radio button
        if (radioSound1.isChecked()) {
            selectedSound = R.raw.sound1;
        } else if (radioSound2.isChecked()) {
            selectedSound = R.raw.sound2;
        } else if (radioSound3.isChecked()) {
            selectedSound = R.raw.sound3;
        }

        // Save the selected sound in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SELECTED_SOUND_KEY, selectedSound);
        editor.apply();

        // Notify the user that the sound has been saved
        Toast.makeText(this, "Sound saved successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
