package vn.edu.tdtu.elit.lab05.lab04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Exercise1 extends AppCompatActivity {

  private ListView lvUsers;
  private List<String> items;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_1);

    // lookup views
    lvUsers = findViewById(R.id.lvUsers);

    // event handlers
    populateUserData();
    handleListViewEvents();
  }

  private void handleListViewEvents() {
    ArrayAdapter itemsAdapter = new ArrayAdapter(Exercise1.this, android.R.layout.simple_list_item_1, items);
    lvUsers.setAdapter(itemsAdapter);
    lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(Exercise1.this, items.get(position), Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void populateUserData() {
    items = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      items.add("item " + i);

    }
  }
}
