package com.example.ar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.ArCoreApk;

public class MainActivity extends AppCompatActivity {

    private View mArButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArButton = findViewById(R.id.enable_ar);
        maybeEnableArButton();
    }

    private void maybeEnableArButton() {
        ArCoreApk.getInstance().checkAvailabilityAsync(this, availability -> {
            if (availability.isSupported()) {
                mArButton.setVisibility(View.VISIBLE);
                mArButton.setEnabled(true);
            }
        });
    }

    public void startARFrame(View view) {
        Intent myIntent = new Intent(this, ARFrame.class);
        startActivity(myIntent);
    }
}