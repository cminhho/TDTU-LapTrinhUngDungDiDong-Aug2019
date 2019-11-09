package com.example.admin.lab09.exercise02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Exercise2Service extends Service {

  public static final String TAG = Exercise2Service.class.getName();

  public static final String RESULT = "result";
  public static final String NOTIFICATION = "com.example.admin.lab09.exercise02";
  public static final String TASK_NAME = "TASK_NAME";
  public static final String PROGRESS_VALUE = "PROGRESS_VALUE";

  public Exercise2Service() {
  }

  @Override
  public void onCreate() {
    Log.i(TAG, "Service onCreate");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    String taskName = intent.getStringExtra(TASK_NAME);
    Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

    int i = 0;
    while (i <= 5) {
      try {
        Thread.sleep(1000);
        i++;
        Log.i(TAG, taskName + ": " + i);
        publishResults(taskName, "DOWNLOADING", i + " %");
      } catch (Exception e) {
      }
    }
    publishResults(taskName, "DOWNLOADED", "100 %");
    return Service.START_NOT_STICKY;
  }

  private void publishResults(String taskName, String resultStatus, String progressValue) {
    Intent intent = new Intent(NOTIFICATION);
    intent.putExtra(TASK_NAME, taskName);
    intent.putExtra(RESULT, resultStatus);
    intent.putExtra(PROGRESS_VALUE, progressValue);
    sendBroadcast(intent);
  }

  @Override
  public IBinder onBind(Intent arg0) {
    Log.i(TAG, "Service onBind");
    return null;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
  }


}