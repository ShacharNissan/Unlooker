package com.classy.class_2021a_and_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    private TextView text_title;
    private TextView main_LBL_info;
    private Button main_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        text_title = findViewById(R.id.text_mainTitle);
        main_LBL_info = findViewById(R.id.text_main_info);
        main_button = findViewById(R.id.button_main_table);

        text_title.bringToFront();

        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        main_button.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Activity_UnlockTable.class);
            startActivity(myIntent);
        });
    }




}