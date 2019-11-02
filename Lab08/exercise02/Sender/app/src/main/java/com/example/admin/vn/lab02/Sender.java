package com.example.admin.vn.lab02;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.vn.R;


public class Sender extends Activity implements OnClickListener {

    private static final String TAG = Sender.class.getSimpleName();
    IntentFilter intentFilter;
    EditText etReceivedBroadcast;
    Button btnSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab02);

        etReceivedBroadcast = findViewById(R.id.etReceivedBroadcast);
        btnSendBroadcast = findViewById(R.id.btnSendBroadcast);

        btnSendBroadcast.setOnClickListener(this);

        intentFilter = new IntentFilter("com.example.admin.vn.MY_NOTIFICATION");
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle results = getResultExtras(true);
                String hierarchy = results.getString("hierarchy");

                results.putString("hierarchy", hierarchy + "->" + TAG);

                Log.d(TAG, "Sender: Anonymous class broadcast receiver");
            }
        }, intentFilter);
    }


    @Override
    public void onClick(View view) {
        String msg = etReceivedBroadcast.getText().toString().trim();

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.setAction("com.example.admin.vn.MY_NOTIFICATION");
        intent.putExtra("msg", msg);
        sendBroadcast(intent);
    }

}