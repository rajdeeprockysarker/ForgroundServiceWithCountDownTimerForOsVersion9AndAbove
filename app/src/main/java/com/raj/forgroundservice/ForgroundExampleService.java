package com.raj.forgroundservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.raj.forgroundservice.ApplictionClass.CHANNEL_ID;

public class ForgroundExampleService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Forground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        heavyWork();
        return START_NOT_STICKY;
    }

//    public void heavyWork(){
//        while(true){
//
//            try {
//                Thread.sleep(1000);
//                Log.v("1","Test");
//                Toast.makeText(getApplicationContext(),"dfdfdf",Toast.LENGTH_LONG).show();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void heavyWork(){
        new CountDownTimer(50000,5000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Toast.makeText(getApplicationContext(),"Toast",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFinish() {
                heavyWork();
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
