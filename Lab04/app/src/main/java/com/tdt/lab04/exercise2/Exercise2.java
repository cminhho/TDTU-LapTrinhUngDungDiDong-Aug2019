package com.tdt.lab04.exercise2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tdt.lab04.R;

import java.util.ArrayList;

public class Exercise2 extends AppCompatActivity {
    private ListView lvUsers;
    private ArrayList<String> items;
    private ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_2);

        // lookup views
        lvUsers = findViewById(R.id.lvUsers);

        initData();
        handleListViewEvents();
    }

    private void handleListViewEvents() {
        itemsAdapter = new ItemsAdapter(Exercise2.this, items);
        lvUsers.setAdapter(itemsAdapter);
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Exercise2.this, items.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        items = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String itemName = "item " + i;
            items.add(itemName);
        }
    }
}
