package com.classy.class_2021a_and_10;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyService extends Service {

    private Context context;

    @Override
    public void onCreate() {
        //ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      //  Log.d("pttt", "onStartCommand Thread: " + Thread.currentThread().getName());
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        context = MainActivity.context;
        startUnlockListen();

//        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//        Sensor mySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//
//        mySensorManager.registerListener(
//                sensorEventListener,
//                mySensor,
//                SensorManager.SENSOR_DELAY_NORMAL);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

//    private SensorEventListener sensorEventListener = new SensorEventListener() {
//        @Override
//        public void onSensorChanged(SensorEvent event) {
//            float value = event.values[0];
//            Log.d("pttt", "V = " + value);
//
//            createBroadcastReceiver(value);
//        }
//
//        @Override
//        public void onAccuracyChanged(Sensor sensor, int accuracy) { }
//    };

//    private void createBroadcastReceiver(float val) {
//        Intent intent = new Intent(PhoneLockBroadcastReceiver.ACTION_UNLOCK);
//      //  intent.putExtra(PhoneLockBroadcastReceiver.EXTRA_LIGHT, val);
//        sendBroadcast(intent);
//    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("pttt", "onDestroy");
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    private void startUnlockListen(){
        PhoneLockBroadcastReceiver unlockReceiver = new PhoneLockBroadcastReceiver(new PhoneLockBroadcastReceiver.CallBack_unlocked() {
            @Override
            public void unlocked(int val) {
               // TODO: PHONE UNLOCKED!
                toast("UNLOCKED!");
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                Date date = new Date();
                String data = formatter.format(date)+ "\n";
                toast(data);
                write_to_storage(data);
                // main_LBL_info.setText(val + "\nlum");
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PhoneLockBroadcastReceiver.UNLOCK_ACTION);
        registerReceiver(unlockReceiver, intentFilter);
    }

    private void write_to_storage(String body) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(PhoneLockBroadcastReceiver.UNLOCK_FILENAME, Context.MODE_APPEND));
        //    outputStreamWriter.append(body);
            outputStreamWriter.write(body);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String read_from_storage(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = context.openFileInput(PhoneLockBroadcastReceiver.UNLOCK_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
