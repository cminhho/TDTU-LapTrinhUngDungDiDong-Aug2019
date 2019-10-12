package vn.edu.tdtu.elit.lab06;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Exercise1 extends AppCompatActivity implements View.OnClickListener {

  private Button btnSimpleDialog;
  private Button btnSingleChoiceDialog;
  private Button btnMultipleChoiceDialog;
  String selectedValue;
  private boolean[] selectedItems = new boolean[4];
  final String[] choices = new String[]{"Coke", "Pepsi", "Sprite", "Seven up"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise1);

    // init view
    btnSimpleDialog = (Button) findViewById(R.id.btnSimpleDialog);
    btnSingleChoiceDialog = (Button) findViewById(R.id.btnSingleChoiceDialog);
    btnMultipleChoiceDialog = (Button) findViewById(R.id.btnMultipleChoiceDialog);

    // handle events
    btnSimpleDialog.setOnClickListener(this);
    btnSingleChoiceDialog.setOnClickListener(this);
    btnMultipleChoiceDialog.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnSimpleDialog:
        showAlertDialog();
        break;
      case R.id.btnSingleChoiceDialog:
        showSingleChoiceDialog();
        break;
      case R.id.btnMultipleChoiceDialog:
        showMultipleChoiceDialog();
        break;
    }
  }

  private void showMultipleChoiceDialog() {
    // 1. Instantiate an AlertDialog.Builder with its constructor
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    // 2. Chain together various setter methods to set the dialog characteristics
    builder.setTitle("Single Choice Dialog");

    builder.setMultiChoiceItems(choices, selectedItems,
        new DialogInterface.OnMultiChoiceClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            selectedItems[which] = isChecked;
          }
        });
    builder.setNegativeButton("Cancel", null);
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        String value = "";
        for (int i = 0; i < choices.length; i++) {
          if (selectedItems[i]) {
            String drink = choices[i];
            value += drink + " ";
          }
          Toast.makeText(Exercise1.this, "You chose " + value, Toast.LENGTH_SHORT).show();
        }
      }
    });
    // 3. Get the AlertDialog from create()
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }

  private void showSingleChoiceDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Single Choice Dialog");
    builder.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        selectedValue = choices[which];
      }
    });
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(Exercise1.this, "You chose " + selectedValue, Toast.LENGTH_LONG).show();
      }
    });
    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(Exercise1.this, "You chose CANCEL", Toast.LENGTH_LONG).show();
      }
    });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }

  private void showAlertDialog() {
    // 1. Instantiate an AlertDialog.Builder with its constructor
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    // 2. Chain together various setter methods to set the dialog characteristics
    builder.setTitle("Task");
    builder.setMessage("Dress up like Mario and throw mushrooms at people");
    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(Exercise1.this, "User selects DELETE", Toast.LENGTH_LONG).show();
      }
    });
    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(Exercise1.this, "User selects CANCEL", Toast.LENGTH_LONG).show();
      }
    });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();
  }

  private void showSimpleDialog() {
    Dialog simpleDialog = new Dialog(this);
    simpleDialog.setTitle("Task");
    simpleDialog.setContentView(R.layout.simple_dialog);
    simpleDialog.show();
  }
}
