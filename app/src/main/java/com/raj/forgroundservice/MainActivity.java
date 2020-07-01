package com.raj.forgroundservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService();
    }

    public void startService() {
        String input = "Input";
        Intent serviceIntent = new Intent(this, ForgroundExampleService.class);
        serviceIntent.putExtra("inputExtra", input);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ContextCompat.startForegroundService(this, serviceIntent);
        }
        else{
            startService(serviceIntent);
        }
    }
    public void stopService() {
        Intent serviceIntent = new Intent(this, ForgroundExampleService.class);
        stopService(serviceIntent);
    }
}