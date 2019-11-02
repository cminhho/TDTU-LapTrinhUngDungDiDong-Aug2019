package com.example.admin.vn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.vn.R;

public class Lab02Receiver extends BroadcastReceiver {

  private Button btnSendNotification;

  @Override
  public void onReceive(Context context, Intent intent) {
    CharSequence intentData = intent.getCharSequenceExtra("msg");
    Toast.makeText(context, "Received the Intent's message: " + intentData, Toast.LENGTH_LONG)
        .show();
  }

}
