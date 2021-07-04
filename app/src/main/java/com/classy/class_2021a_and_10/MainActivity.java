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

    private TextView main_LBL_info;
    private Button main_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        main_LBL_info = findViewById(R.id.main_LBL_info);
        main_button = findViewById(R.id.main_button);

        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        main_button.setOnClickListener(v -> {
            main_LBL_info.setText(MyService.read_from_storage(context));
        });
    }




}