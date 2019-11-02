package com.example.admin.vn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.vn.R;

public class Lab02Sender extends AppCompatActivity {

  private Button btnSendNotification;
  private TextView txtMsg;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.lab02);

    initView();
    handleEvents();
    configureReceiver();
  }

  private void handleEvents() {
    btnSendNotification.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        broadcastIntent();
      }
    });

  }

  private void broadcastIntent() {
    Intent intent = new Intent();
    intent.setAction("com.example.CUSTOM_INTENT");
    intent.putExtra("msg", (CharSequence) txtMsg.getText().toString());
    sendBroadcast(intent);
  }

  private void initView() {
    btnSendNotification = (Button) findViewById(R.id.btnSendNotification);
    txtMsg = (TextView) findViewById(R.id.txtMsg);
  }

  private void configureReceiver() {
    IntentFilter filter = new IntentFilter();
    filter.addAction("com.example.admin.vn.sendbroadcast");
    registerReceiver(broadcastReceiver, filter);
  }

  // Create a receiver that has to run on receiving WiFi state change
  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      Toast.makeText(context, "Notification is sent!", Toast.LENGTH_LONG).show();
    }
  };

  @Override
  protected void onStop() {
    super.onStop();
    unregisterReceiver(broadcastReceiver);
  }
}
