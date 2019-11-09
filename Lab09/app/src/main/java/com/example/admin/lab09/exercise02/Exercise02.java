package com.example.admin.lab09.exercise02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.lab09.R;

public class Exercise02 extends AppCompatActivity {

  Intent intent;
  private TextView txtProgress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_02);

    Button btnCreateSyncTask = findViewById(R.id.btnCreateSyncTask);
    Button btnStopSyncTask = findViewById(R.id.btnStopSyncTask);
    txtProgress = findViewById(R.id.txtProgress);

    btnCreateSyncTask.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        doSyncTasks(v);
      }
    });

    btnStopSyncTask.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        stopService(v);
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    registerReceiver(receiver, new IntentFilter(Exercise2Service.NOTIFICATION));
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterReceiver(receiver);
  }

  public void doSyncTasks(View view) {
    intent = new Intent(this, Exercise2Service.class);
    intent.putExtra(Exercise2Service.TASK_NAME, "HELLO");
    startService(intent);
  }

  // Method to stop the service
  public void stopService(View view) {
    stopService(new Intent(getBaseContext(), Exercise2Service.class));
  }

  private BroadcastReceiver receiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
      Bundle bundle = intent.getExtras();
      if (bundle != null) {
        String taskName = bundle.getString(Exercise2Service.TASK_NAME);
        String resultCode = bundle.getString(Exercise2Service.RESULT);
        String progressValue = bundle.getString(Exercise2Service.PROGRESS_VALUE);
        if (resultCode.equals("DOWNLOADING")) {
          Toast.makeText(Exercise02.this,
              "Downloading: ", Toast.LENGTH_LONG).show();
          txtProgress.setText("Progress : " + progressValue);
        } else {
          Toast.makeText(Exercise02.this, "Downloaded",
              Toast.LENGTH_LONG).show();
          txtProgress.setText("Downloaded!");
        }
      }
    }
  };
}