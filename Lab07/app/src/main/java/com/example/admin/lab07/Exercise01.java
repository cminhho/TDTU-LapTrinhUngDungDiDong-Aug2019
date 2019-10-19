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
    private static final String SHARED_APP_STARTUP_TIMES = "APP_STARTUP_TIMES";
    private static final String SHARED_USER_NAME = "USER_NAME";
    private static final String PREFERENCE_NAME = "LAP07_APP_COUNTER";
    private TextView txtOpenTimes;
    private int appStartupTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_01);

        // init view
        txtOpenTimes = (TextView) findViewById(R.id.txtOpenTimes);

        getPreferences();
        storePreferences();

        displayValues();
    }

    private void displayValues() {
        txtOpenTimes.setText(getString(R.string.app_startup_times) + appStartupTimes);
        Toast.makeText(this,"App startup times: " + appStartupTimes, Toast.LENGTH_LONG).show();
    }

    private void storePreferences() {
        SharedPreferences pre = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putInt(SHARED_APP_STARTUP_TIMES, ++appStartupTimes);
        editor.apply();
        editor.commit();
    }

    private void getPreferences() {
        SharedPreferences pre = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        appStartupTimes = pre.getInt(SHARED_APP_STARTUP_TIMES, 0);
    }

}
