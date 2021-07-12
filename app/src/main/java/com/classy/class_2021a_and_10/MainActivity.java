package com.classy.class_2021a_and_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    private TextView text_title;
    private TextView main_LBL_unlock_sum;
    private TextView main_LBL_day_sum;
    private Button main_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();

        Intent intent = new Intent(this, MyService.class);
        startService(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshViews();
    }

    private void findViews(){
        context = getApplicationContext();
        text_title = findViewById(R.id.text_mainTitle);
        main_LBL_day_sum = findViewById(R.id.main_LBL_day_sum);
        main_LBL_unlock_sum = findViewById(R.id.main_LBL_unlock_sum);
        main_button = findViewById(R.id.button_main_table);
    }

    private void initViews(){
        text_title.bringToFront();

        main_button.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Activity_UnlockTable.class);
            startActivity(myIntent);
        });
    }

    private void refreshViews(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                main_LBL_unlock_sum.setText(Integer.toString(MyService.get_file_lines_count(context)));
                main_LBL_day_sum.setText(Integer.toString(MyService.get_file_days_count(context)));
            }
        }, 250);

    }
}