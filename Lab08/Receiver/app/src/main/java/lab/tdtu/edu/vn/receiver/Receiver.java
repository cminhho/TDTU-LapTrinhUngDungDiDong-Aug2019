package lab.tdtu.edu.vn.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Receiver extends AppCompatActivity {

    private static final String TAG = Receiver.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register broadcast receiver in onResume method of the activity.
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.admin.vn.MY_NOTIFICATION");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // UnRegister the receiver when ever you pause the activity to avoid leak of receiver.
        unregisterReceiver(broadcastReceiver);
    }

    // Create a receiver that has to run on receiving WiFi state change
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public static final String CHANNEL_ID = "123";

        @Override
        public void onReceive(Context context, Intent intent) {
            String intentData = intent.getStringExtra("msg");

            Log.d(TAG, "Receiver: Anonymous class broadcast receiver");

            Toast.makeText(context, "Receiver: Received the Intent's message: " + intentData, Toast.LENGTH_LONG).show();

//            NotificationCompat.Builder builder = new NotificationCompat.Builder(Receiver.this, CHANNEL_ID)
//                    .setContentTitle("Hello")
//                    .setContentText(intentData)
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//            builder.build();
        }
    };
}