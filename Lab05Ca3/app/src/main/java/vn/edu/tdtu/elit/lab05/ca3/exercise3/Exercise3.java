package vn.edu.tdtu.elit.lab05.ca3.exercise3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import vn.edu.tdtu.elit.lab05.ca3.R;
import vn.edu.tdtu.elit.lab05.ca3.model.Item;

public class Exercise3 extends AppCompatActivity {

  private GridView gvComputers;
  private ArrayList<Item> items;
  private ComputersAdapter computersAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_3);

    initView();
    initData();
    handleEvents();
  }

  private void handleEvents() {
    computersAdapter = new ComputersAdapter(Exercise3.this, items);
    gvComputers.setAdapter(computersAdapter);

    gvComputers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
  }

  private void initData() {
    items = new ArrayList<>();
    for (int i = 1; i < 30; i++) {
      String itemName = "PC " + i;
      Item item = new Item(itemName, true);
      items.add(item);
    }
  }

  private void initView() {
    gvComputers = (GridView) findViewById(R.id.gvComputers);
  }
}