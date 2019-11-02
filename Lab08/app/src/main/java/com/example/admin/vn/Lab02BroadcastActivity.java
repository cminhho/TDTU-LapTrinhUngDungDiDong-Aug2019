package com.example.admin.vn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lab02BroadcastActivity extends AppCompatActivity {


  // This is the custom intent-filter action value.
  public static final String CUSTOM_BROADCAST_ACTION = "com.example.admin.vn.CUSTOM_BROADCAST";

  public static final String ABORT_ORDERED_CUSTOM_BROADCAST = "ABORT_ORDERED_CUSTOM_BROADCAST";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lab02_custom_broadcast);

    setTitle("Send Custom Broadcast Example.");

    // When click it will send a normal custom broadcast.
    Button sendNormalCustomBroadcastButton = (Button)findViewById(R.id.send_normal_custom_braodcast_button);
    sendNormalCustomBroadcastButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // Send a normal broadcast when being clicked.
        // The broadcast will be received by all the registered broadcast receivers at same time.
        Intent intent = new Intent(CUSTOM_BROADCAST_ACTION);
        sendBroadcast(intent);
      }
    });

    // When click it will send an ordered broadcast.
    Button sendOrderedCustomBroadcastButton = (Button)findViewById(R.id.send_ordered_custom_braodcast_button);
    sendOrderedCustomBroadcastButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // Send an ordered broadcast when being clicked.
        // The broadcast will be received by the registered broadcast receiver in order.
        // The receiver receive order id defined by the intent-filter priority attribute value.
        // The bigger the value, the first to receive.
        Intent intent = new Intent(CUSTOM_BROADCAST_ACTION);
        sendOrderedBroadcast(intent, null);
      }
    });

    // When click it will send an ordered broadcast also,
    // but the broadcast will be aborted by the first broadcast receiver.
    Button abortOrderedCustomBroadcastButton = (Button)findViewById(R.id.abort_ordered_custom_braodcast_button);
    abortOrderedCustomBroadcastButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(CUSTOM_BROADCAST_ACTION);
        // This String extra value will indicate the broadcast receiver to abort the broadcast.
        // So the posterior broadcast receiver will not receive it.
        intent.putExtra(ABORT_ORDERED_CUSTOM_BROADCAST, ABORT_ORDERED_CUSTOM_BROADCAST);
        sendOrderedBroadcast(intent, null);
      }
    });
  }
}
