package com.ar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.ar.core.ArCoreApk;


public class MainActivity extends AppCompatActivity {

    private View mArButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArButton = findViewById(R.id.enable_ar);
        maybeEnableArButton();
    }

    void maybeEnableArButton() {
        ArCoreApk.getInstance().checkAvailabilityAsync(this, availability -> {
            if (availability.isSupported()) {
                mArButton.setVisibility(View.VISIBLE);
                mArButton.setEnabled(true);
            } else { // The device is unsupported or unknown.
                mArButton.setVisibility(View.INVISIBLE);
                mArButton.setEnabled(false);
            }
        });
    }

    public void startARFrame(View view) {
        Intent myIntent = new Intent(this, ARFrame.class);
        startActivity(myIntent);
    }
}