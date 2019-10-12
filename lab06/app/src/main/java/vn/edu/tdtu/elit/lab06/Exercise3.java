package vn.edu.tdtu.elit.lab06;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Exercise3 extends AppCompatActivity {

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

    // initialize adapter
    itemsAdapter = new ItemsAdapter(Exercise3.this, items);
    listView.setAdapter(itemsAdapter);

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
    int position = info.position;
    if (item.getItemId() == R.id.checkAndUncheck) {
      checkAndUncheck(position);
    } else if (item.getItemId() == R.id.delete) {
      deleteItem(position);
    }
    return super.onContextItemSelected(item);
  }

  private void checkAndUncheck(int position) {
    Item updatedItem = items.get(position);
    updatedItem.setSelected(!updatedItem.isSelected());
    items.set(position, updatedItem);
    itemsAdapter.notifyDataSetChanged();
  }

  private void deleteItem(int position) {
    items.remove(position);
    itemsAdapter.notifyDataSetChanged();
    Item updatedItem = items.get(position);
    Toast.makeText(this, updatedItem.getName() + " is deleted!", Toast.LENGTH_SHORT).show();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // show menu when menu button is pressed
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.context_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // display a message when a button was pressed
    String message = "";
    if (item.getItemId() == R.id.deleteAll) {
      items.clear();
      itemsAdapter.notifyDataSetChanged();
    } else if (item.getItemId() == R.id.about) {
      showAboutDialog();
    } else {
      message = "Why would you select that!?";
    }

    // show message via toast
    Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
    toast.show();

    return true;
  }

  private void showAboutDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("About");
    builder.setMessage("The application is created by thChung");
    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(Exercise3.this, "SEE YOU!", Toast.LENGTH_LONG).show();
      }
    });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }
}
