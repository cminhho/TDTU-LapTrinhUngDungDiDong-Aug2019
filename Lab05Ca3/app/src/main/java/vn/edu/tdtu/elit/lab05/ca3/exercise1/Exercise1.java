package vn.edu.tdtu.elit.lab05.ca3.exercise1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;
import vn.edu.tdtu.elit.lab05.ca3.model.Item;
import vn.edu.tdtu.elit.lab05.ca3.adapter.ItemsAdapter;
import vn.edu.tdtu.elit.lab05.ca3.R;

public class Exercise1 extends AppCompatActivity {

  private ListView lvItems;
  private List<Item> items = new ArrayList<>();
  private ItemsAdapter itemsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_1);

    initView();
    initData();
    bindListViewEvents();
  }

  private void initView() {
    lvItems = findViewById(R.id.lvItems);
  }

  private void initData() {
    for (int i = 1; i <= 10; i++) {
      Item item = new Item("item " + i, false);
      items.add(item);
    }
  }

  private void bindListViewEvents() {
    itemsAdapter = new ItemsAdapter(Exercise1.this, items);
    lvItems.setAdapter(itemsAdapter);
  }
}
