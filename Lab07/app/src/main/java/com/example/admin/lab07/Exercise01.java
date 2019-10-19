package com.example.admin.lab07;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by thChung on 3/23/2019.
 */
public class Exercise01 extends AppCompatActivity {

  private static final String SHARED_APP_STARTUP_TIMES = "APP_STARTUP_TIMES_2";
  private static final String PREFERENCE_NAME = "LAB07_APP_COUNTER_2";
  private TextView txtOpenTimes;
  private int appStartupTimes = 0;
  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_01);

    // init view
    txtOpenTimes = (TextView) findViewById(R.id.txtOpenTimes);

    sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
    getStartupTimeNumber();
    increaseStartupTimeNumber();

    displayValues();
  }

  private void displayValues() {
    txtOpenTimes.setText(getString(R.string.app_startup_times) + appStartupTimes);
    Toast.makeText(this, "App startup times: " + appStartupTimes, Toast.LENGTH_LONG).show();
  }

  private void increaseStartupTimeNumber() {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(SHARED_APP_STARTUP_TIMES, ++appStartupTimes);
    editor.apply();
    editor.commit();
  }

  private void getStartupTimeNumber() {
    appStartupTimes = sharedPreferences.getInt(SHARED_APP_STARTUP_TIMES, 0);
  }

}
