package com.example.admin.lab09.exercise03;

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
import com.example.admin.lab09.exercise02.Exercise2Service;

public class Exercise03 extends AppCompatActivity {

  Intent intent;
  int i = 0;
  private TextView txtProgress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_03);

    Button btnCreateSyncTask = findViewById(R.id.btnCreateSyncTask);
    Button btnStopSyncTask = findViewById(R.id.btnStopSyncTask);
    txtProgress = findViewById(R.id.txtProgressResult);

    btnCreateSyncTask.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        doSyncTasks(v);
      }
    });

    btnStopSyncTask.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        stopService(intent);
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

  private BroadcastReceiver receiver = new BroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
      Bundle bundle = intent.getExtras();
      if (bundle != null) {
        String taskName = bundle.getString(Exercise3IntentService.TASK_NAME);
        String downloadProgress = bundle.getString(Exercise3IntentService.DOWNLOAD_PROGRESS);
        String resultCode = bundle.getString(Exercise3IntentService.RESULT);
        if (resultCode.equals("DOWNLOADING")) {
          Toast.makeText(Exercise03.this,
              taskName + " is downloading progress: " + downloadProgress, Toast.LENGTH_LONG).show();
          txtProgress.setText("Downloading progress: " + downloadProgress);
        } else if (resultCode.equals("DOWNLOADED")) {
          Toast.makeText(Exercise03.this, "Downloaded!",
              Toast.LENGTH_LONG).show();
          txtProgress.setText(resultCode);
        }
      }
    }
  };

  public void doSyncTasks(View view) {
    intent = new Intent(this, Exercise3IntentService.class);
    intent.putExtra(Exercise3IntentService.TASK_NAME, i++);
    startService(intent);
  }
}