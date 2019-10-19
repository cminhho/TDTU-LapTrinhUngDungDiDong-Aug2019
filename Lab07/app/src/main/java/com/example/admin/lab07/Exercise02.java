package com.example.admin.lab07;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by thChung on 3/23/2019.
 */
public class Exercise02 extends AppCompatActivity implements View.OnClickListener {

  private Button btnReadInternal;
  private Button btnReadExternal;
  private Button btnWriteInternal;
  private Button btnWriteExternal;
  private EditText edtContent;

  private final String EXTERNAL_FILE_NAME = "ExternalSampleFile.txt";
  private final String INTERNAL_FILE_NAME = "InternalSampleFile.txt";

  private File myExternalFile;
  private File myInternalFile;
  private String myData = "";
  private String TAG = Exercise02.class.getName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_02);

    initView();
    createInternalFile();
    createExternalFile();
    handleEvents();
  }

  private void handleEvents() {
    btnReadInternal.setOnClickListener(this);
    btnWriteInternal.setOnClickListener(this);
    btnReadExternal.setOnClickListener(this);
    btnWriteExternal.setOnClickListener(this);
  }

  private void createInternalFile() {
    File dir = getApplication().getFilesDir();

    myInternalFile = new File(dir, INTERNAL_FILE_NAME);
  }

  private void createExternalFile() {
    if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
      btnWriteInternal.setEnabled(false);
    } else {
      String filepath = "MyFileStorage";
      Log.d(TAG, "the path of external file is " + getExternalFilesDir(filepath));
      myExternalFile = new File(getExternalFilesDir(filepath), EXTERNAL_FILE_NAME);
    }
  }

  private void readFileToStorage(File file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      DataInputStream in = new DataInputStream(fis);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      Log.d(TAG, "The file path = " + file.getAbsolutePath());
      String strLine;
      StringBuilder contentStringBuilder = new StringBuilder();
      while ((strLine = br.readLine()) != null) {
        contentStringBuilder.append(strLine);
      }
      myData = contentStringBuilder.toString();
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeFileToStorage(File file) {
    try {
      FileOutputStream fos = new FileOutputStream(file);
      Log.d(TAG, "The file path = " + file.getAbsolutePath());
      fos.write(edtContent.getText().toString().getBytes());
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static boolean isExternalStorageReadOnly() {
    String extStorageState = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
  }

  private static boolean isExternalStorageAvailable() {
    String extStorageState = Environment.getExternalStorageState();
    return Environment.MEDIA_MOUNTED.equals(extStorageState);
  }

  private void initView() {
    btnReadInternal = findViewById(R.id.btnReadInternal);
    btnReadExternal = findViewById(R.id.btnReadExternal);
    btnWriteInternal = findViewById(R.id.btnWriteInternal);
    btnWriteExternal = findViewById(R.id.btnWriteExternal);
    edtContent = findViewById(R.id.edtContent);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnReadInternal:
        readFileToStorage(myInternalFile);
        displayValue();
        break;
      case R.id.btnReadExternal:
        readFileToStorage(myExternalFile);
        displayValue();
        break;
      case R.id.btnWriteInternal:
        writeFileToStorage(myInternalFile);
        resetValue(INTERNAL_FILE_NAME);
        break;
      case R.id.btnWriteExternal:
        writeFileToStorage(myExternalFile);
        resetValue(EXTERNAL_FILE_NAME);
        break;
    }
  }

  private void resetValue(String fileName) {
    edtContent.setText("");
    Toast.makeText(Exercise02.this, fileName + " saved to External Storage...", Toast.LENGTH_LONG)
        .show();
  }

  private void displayValue() {
    edtContent.setText(myData);
  }
}
