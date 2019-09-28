package vn.edu.tdtu.elit.lab05.ca3.exercise2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;
import java.util.ArrayList;

import vn.edu.tdtu.elit.lab05.ca3.R;
import vn.edu.tdtu.elit.lab05.ca3.model.Item;
import vn.edu.tdtu.elit.lab05.ca3.adapter.ItemsAdapter;

public class Exercise2 extends AppCompatActivity {

  private EditText edtItemName;
  private Button btnAdd;
  private ListView lvUsers;
  private ArrayList<Item> items;
  private ItemsAdapter itemsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_2);

    initView();
    populateData();
    handleEvents();
  }

  private void handleEvents() {
    itemsAdapter = new ItemsAdapter(Exercise2.this, items);
    lvUsers.setAdapter(itemsAdapter);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String itemName = edtItemName.getText().toString();
        Item newItem = new Item(itemName);
        items.add(newItem);
        itemsAdapter.notifyDataSetChanged();
      }
    });
  }

  private void populateData() {
    items = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      String itemName = "item " + i;
      Item item = new Item(itemName, true);
      items.add(item);
    }
  }

  private void initView() {
    edtItemName = findViewById(R.id.edtItemName);
    btnAdd = findViewById(R.id.btnAdd);
    lvUsers = findViewById(R.id.lvUsers);
  }
}