package com.classy.class_2021a_and_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_UnlockTable extends AppCompatActivity {
    private TextView text_table_title;
    private Button button_reset;
    private Context context;
    private RecyclerView rvTable;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_table);

        findViews();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshViews();
    }

    private void findViews(){
        context = MainActivity.context;

        button_reset = findViewById(R.id.button_table_reset);
        text_table_title = findViewById(R.id.text_table_title);
        rvTable = findViewById(R.id.recyclerView_table);
    }

    private void initViews(){
        text_table_title.bringToFront();
        rvLayoutManager = new LinearLayoutManager(this);
        button_reset.setOnClickListener(v -> {
            MyService.reset_storage(context);

            refreshViews();
        });
    }

    private void refreshViews(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                String lines[] = MyService.get_file_lines_clean(context);
                ArrayList<UnlockItem> items = new ArrayList<>();
                for (int i = 0; i < lines.length; i++) {
                    items.add(new UnlockItem(lines[i]));
                }

                rvTable.setHasFixedSize(true);

                rvAdapter = new UnlockItemAdapter(items);

                rvTable.setLayoutManager(rvLayoutManager);
                rvTable.setAdapter(rvAdapter);
            }
        }, 250);

    }
}