package com.example.admin.vn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by thChung on 3/30/2019.
 */
public class Lab01 extends AppCompatActivity {

    private TextView txtWifiStatus;
    private Button btnOpenWifi;
    private boolean isReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab01);

        initView();
        handleEvents();

        checkWifiConnectionState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register broadcast receiver in onResume method of the activity.
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void handleEvents() {
        btnOpenWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWifiSetting();
            }
        });
    }

    private void gotoWifiSetting() {
        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
    }

    private void initView() {
        txtWifiStatus = (TextView) findViewById(R.id.txtWifiStatus);
        btnOpenWifi = (Button) findViewById(R.id.btnOpenWifi);
    }

    private void checkWifiConnectionState() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;

        if (isWiFi) {
            txtWifiStatus.setText("Wifi is connected");
        } else {
            txtWifiStatus.setText("The application need wifi connection to play!");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // UnRegister the receiver when ever you pause the activity to avoid leak of receiver.
        unregisterReceiver(broadcastReceiver);
    }

    // Create a receiver that has to run on receiving WiFi state change
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkWifiConnectionState();
        }
    };
}
