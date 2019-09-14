package com.tdt.lab03.exercise04;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.tdt.lab03.R;

/**
 * Created by thChung on 3/2/2019.
 */

public class Exercise04 extends AppCompatActivity {
    private static final String TAG = Exercise04.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogInfomation("=== ON CREATE ===!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLogInfomation("=== ON START ===");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLogInfomation("=== ON RESUME ===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLogInfomation("=== ON STOP ===");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLogInfomation("=== ON DESTROY ===");
    }

    private void showLogInfomation(String message) {
        Log.i(TAG, message);
    }
}
