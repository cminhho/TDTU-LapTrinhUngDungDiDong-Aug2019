package com.example.admin.lab09.exercise03;

import android.os.AsyncTask;
import android.util.Log;

public class Exercise3AsyncTask extends AsyncTask<Integer, Integer, String> {

  private static final String TAG = Exercise3AsyncTask.class.getName();

  @Override
  protected String doInBackground(Integer... params) {

    int startId = params[0];
    int i = 0;
    while (i <= 3) {
      publishProgress(startId, i);
      try {
        Thread.sleep(1000);
        i++;
        Log.i(TAG, "SLEEP: " + i);
      } catch (Exception e) {
      }
    }
    return ("Service complete " + startId);
  }

  @Override
  protected void onPostExecute(String result) {
    Log.i(TAG, result);
  }

  @Override
  protected void onPreExecute() {

  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    Log.i(TAG, "ServiceID " + values[0] + " : Service Progress " + values[1]);
  }
}