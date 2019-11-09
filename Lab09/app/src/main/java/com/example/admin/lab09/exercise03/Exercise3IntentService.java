package com.example.admin.lab09.exercise03;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class Exercise3IntentService extends IntentService {

  private static final String TAG = Exercise3IntentService.class.getName();

  public static final String RESULT = "result";
  public static final String NOTIFICATION = "com.example.admin.lab09.exercise02";
  public static final String TASK_NAME = "TASK_NAME";
  public static final String DOWNLOAD_PROGRESS = "DOWNLOAD_PROGRESS";

  public Exercise3IntentService() {
    super("Exercise3IntentService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    String taskName = intent.getStringExtra("TASK_NAME");
    int i = 0;
    while (i <= 3) {
      try {
        Thread.sleep(1000);
        i++;
        Log.i(TAG, taskName + ": " + i);
        publishResults(taskName, "DOWNLOADING", i + " %");
      } catch (Exception e) {
      }
    }
    publishResults(taskName, "DOWNLOADED", i + " %");
  }

  private void publishResults(String taskName, String resultStatus, String progress) {
    Intent intent = new Intent(NOTIFICATION);
    intent.putExtra(TASK_NAME, taskName);
    intent.putExtra(DOWNLOAD_PROGRESS, progress);
    intent.putExtra(RESULT, resultStatus);
    sendBroadcast(intent);
  }
}