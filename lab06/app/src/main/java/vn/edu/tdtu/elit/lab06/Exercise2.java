package vn.edu.tdtu.elit.lab06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Exercise2 extends AppCompatActivity {

  private ListView listView;
  private ArrayList<Item> items;
  private ItemsAdapter itemsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise2);

    // init view
    listView = findViewById(R.id.lvMenuItems);

    // populate items
    items = new ArrayList<>();
    for (int i = 1; i <= 30; i++) {
      Item item = new Item("Item " + i);
      items.add(item);
    }

    // initialize the adapter
    itemsAdapter = new ItemsAdapter(Exercise2.this, items);
    listView.setAdapter(itemsAdapter);

    // Registers a context menu to be shown for the given view.
    registerForContextMenu(listView);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    getMenuInflater().inflate(R.menu.listview_context_menu, menu);

    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
    Item updatedItem = items.get(info.position);
    menu.setHeaderTitle(updatedItem.getName());
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
        .getMenuInfo();
    int selected = info.position;
    if (item.getItemId() == R.id.checkAndUncheck) {
      Item updatedItem = items.get(selected);
      updatedItem.setSelected(!updatedItem.isSelected());
      items.set(selected, updatedItem);
      itemsAdapter.notifyDataSetChanged();
    } else if (item.getItemId() == R.id.delete) {
      items.remove(selected);
      itemsAdapter.notifyDataSetChanged();
      Item updatedItem = items.get(selected);
      Toast.makeText(this, updatedItem.getName() + " is deleted!", Toast.LENGTH_SHORT).show();
    }
    return super.onContextItemSelected(item);
  }
}
