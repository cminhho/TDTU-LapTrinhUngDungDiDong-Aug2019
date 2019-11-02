package com.example.admin.vn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

public class Lab02BroadcastReceiverOne extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {

    String action = intent.getAction();
    if(Lab02BroadcastActivity.CUSTOM_BROADCAST_ACTION.equals(action)) {
      // When receive it will show an toast popup message.
      Toast.makeText(context, "Custom Broadcast Receiver One Receive Message.", Toast.LENGTH_SHORT).show();

      // If the broadcast contains abort command.
      String abortBroadcast = intent.getStringExtra(Lab02BroadcastActivity.ABORT_ORDERED_CUSTOM_BROADCAST);
      if (!TextUtils.isEmpty(abortBroadcast)) {
        // Abort the broadcast, posterior broadcast receiver will not receive.
        abortBroadcast();
      }
    }
  }

}
