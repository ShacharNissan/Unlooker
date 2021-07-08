package com.classy.class_2021a_and_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_UnlockTable extends AppCompatActivity {
    private TextView text_table_title;

    private RecyclerView rvTable;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_table);

        ArrayList<UnlockItem> items = new ArrayList<>();
        items.add(new UnlockItem("08/07/2021"));
        items.add(new UnlockItem("09/07/2021"));
        items.add(new UnlockItem("10/07/2021"));
        items.add(new UnlockItem("11/07/2021"));
        items.add(new UnlockItem("12/07/2021"));

        text_table_title = findViewById((R.id.text_table_title));
        text_table_title.bringToFront();
        rvTable = findViewById(R.id.recyclerView_table);
        rvTable.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        rvAdapter = new UnlockItemAdapter(items);

        rvTable.setLayoutManager(rvLayoutManager);
        rvTable.setAdapter(rvAdapter);
    }
}