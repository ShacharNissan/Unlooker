package com.classy.class_2021a_and_10;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class  MyService extends Service {
    private static final String DATE_FORMAT = "HH:mm:ss dd/MM/yyyy";

    private Context context;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        context = MainActivity.context;
        startUnlockListen();

        return START_STICKY;
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }

    private void startUnlockListen(){
        PhoneLockBroadcastReceiver unlockReceiver = new PhoneLockBroadcastReceiver(new PhoneLockBroadcastReceiver.CallBack_unlocked() {
            @Override
            public void unlocked(int val) {
                toast("UNLOCKED!");
                SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                Date date = new Date();
                String data = formatter.format(date)+ "\n";
                write_to_storage(data);
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PhoneLockBroadcastReceiver.UNLOCK_ACTION);
        registerReceiver(unlockReceiver, intentFilter);
    }

    private void write_to_storage(String body) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(PhoneLockBroadcastReceiver.UNLOCK_FILENAME, Context.MODE_APPEND));
            outputStreamWriter.write(body);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static void reset_storage(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(PhoneLockBroadcastReceiver.UNLOCK_FILENAME, Context.MODE_PRIVATE));
            outputStreamWriter.write("");
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

    public static String[] get_file_lines_clean(Context context){
        String data = read_from_storage(context);
        String lines[] = data.split("\n");
        ArrayList<String> new_lines = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            if(isValidDate(lines[i])){
                new_lines.add(lines[i]);
            }
        }
        return new_lines.toArray(new String[0]);
    }

    public static int get_file_lines_count(Context context){
        String data[] = get_file_lines_clean(context);
        return data.length;
    }

    public static int get_file_days_count(Context context){
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String data[] = get_file_lines_clean(context);
        Date firstDate, lastDate;

        try {
            firstDate = format.parse(data[0]);
            lastDate = format.parse(data[data.length - 1]);
        }catch (Exception e){
            return 0;
        }
        long daysInMillis = lastDate.getTime() - firstDate.getTime();
        return ((int) (daysInMillis / (1000*60*60*24))) + 1;
    }

    public static boolean isValidDate(String date){
        if(date == null)
            return false;

        if(date.isEmpty())
            return false;

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date temp = format.parse(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
