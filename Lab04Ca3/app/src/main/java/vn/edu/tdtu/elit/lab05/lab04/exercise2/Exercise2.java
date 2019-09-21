package vn.edu.tdtu.elit.lab05.lab04.exercise2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.tdtu.elit.lab05.lab04.R;

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

    populateUserData();
    handleListViewEvents();
  }

  private void handleListViewEvents() {
    itemsAdapter = new ItemsAdapter(Exercise2.this, items);
    lvUsers.setAdapter(itemsAdapter);
  }

  private void populateUserData() {
    items = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      String itemName = "item " + i;
      items.add(itemName);
    }
  }
}
