package com.example.admin.lab07.exercise03;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.lab07.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by thChung on 3/23/2019.
 */
public class Exercise03 extends AppCompatActivity {

    private static final String TAG = Exercise03.class.getName();
    private ListView lvItems;
    private ArrayList<Contact> items;
    private ItemPhoneItemAdapter adapter;
    private EditText edtNewItem;
    private Button btnAdd;
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;
    private ContactDatabaseHelper contactDatabaseHelper;
    private EditText edtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_03);

        contactDatabaseHelper = new ContactDatabaseHelper(this);

        // populate values
        contactDatabaseHelper.createDefaultContacts();

        // init view
        lvItems = (ListView) findViewById(R.id.lvItems);
        edtNewItem = (EditText) findViewById(R.id.edtNewItem);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        // get all contacts
        items = new ArrayList<Contact>();
        List<Contact> contacts = contactDatabaseHelper.getAllContacts();
        this.items.addAll(contacts);

        // create adapter
        adapter = new ItemPhoneItemAdapter(this, items);
        lvItems.setAdapter(adapter);

        // handle add button events
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItemName = edtNewItem.getText().toString();
                String newPhoneNumber = edtPhoneNumber.getText().toString();
                if (newItemName.isEmpty()) {
                    Toast.makeText(Exercise03.this, "Name cannot be empty!", Toast.LENGTH_LONG).show();
                } else {
                    Contact newItem = new Contact(newItemName, newPhoneNumber, 0);
                    contactDatabaseHelper.addContact(newItem);
                    items.add(newItem);
                    adapter.notifyDataSetChanged();
                }
                // reset input text
                edtNewItem.setText("");
                edtPhoneNumber.setText("");
            }
        });

        registerForContextMenu(this.lvItems);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Select The Action");

        menu.add(0, MENU_ITEM_VIEW, 0, "View Contact");
        menu.add(0, MENU_ITEM_EDIT, 2, "Edit Contact");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Contact");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Contact selectedContact = (Contact) this.lvItems.getItemAtPosition(info.position);

        if (item.getItemId() == MENU_ITEM_VIEW) {
            Toast.makeText(getApplicationContext(), selectedContact.getName(), Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == MENU_ITEM_EDIT) {
            showUpdateDialog(selectedContact, info.position);
        } else if (item.getItemId() == MENU_ITEM_DELETE) {
            showConfirmDeleteDialog(selectedContact, info.position);
        } else {
            return false;
        }
        return true;
    }

    private void showConfirmDeleteDialog(final Contact contact, final int position) {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete " + contact.getName() + " ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteContact(contact, position);
                    }
                })
                .setNegativeButton("No", null)
                .show();
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
            deleteAllContacts();
            adapter.notifyDataSetChanged();
        } else if (item.getItemId() == R.id.about) {
            showAboutDialog();
        }
        return true;
    }

    private void deleteAllContacts() {
        Log.i(TAG, "ContactDatabaseHelper.deleteAllContacts ... ");
        contactDatabaseHelper.deleteAllContacts();
        items.clear();
        adapter.notifyDataSetChanged();
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage("The application is created by thChung");
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Exercise03.this, "SEE YOU!", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteContact(Contact contact, int position) {
        Log.i(TAG, "ContactDatabaseHelper.deleteNote ... " + contact.getName());
        contactDatabaseHelper.deleteContact(contact);
        items.remove(position);
        adapter.notifyDataSetChanged();
    }

    private void showUpdateDialog(final Contact contact, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.contact_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Exercise03.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText edtContactName = view.findViewById(R.id.edtContactName);
        final EditText edtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(getString(R.string.lbl_edit_contact_title));

        edtContactName.setText(contact.getName());
        edtPhoneNumber.setText(contact.getPhoneNumber());

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // Show toast message when no text is entered
                        if (TextUtils.isEmpty(edtContactName.getText().toString())) {
                            Toast.makeText(Exercise03.this, "Enter note!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // check if user updating note
                        Contact contact = new Contact();
                        contact.setContactId(contact.getContactId());
                        contact.setName(edtContactName.getText().toString());
                        contact.setPhoneNumber(edtPhoneNumber.getText().toString());
                        updateContact(contact, position);
                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
    }

    private void updateContact(Contact contact, int position) {
        // updating note in db
        contactDatabaseHelper.updateContact(contact);

        // refreshing the list
        items.set(position, contact);
        adapter.notifyDataSetChanged();
    }
}
