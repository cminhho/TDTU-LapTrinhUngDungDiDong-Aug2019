package com.tdt.lab03.exercise03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tdt.lab03.R;

/**
 * Created by thChung on 3/2/2019.
 */

public class Exercise03 extends AppCompatActivity {
    public static final int STATE_LINK_ID = 200;

    private EditText edtLink;
    private Button btnOpenLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise02);

        // lookup views
        edtLink = findViewById(R.id.edtLink);
        btnOpenLink = findViewById(R.id.btnOpenLink);

        // event handlers
        btnOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtLink.getText().toString();
                if(url.isEmpty()){
                    Toast.makeText(Exercise03.this, "URL cannot be empty!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Exercise03.this, WebViewActivity.class);
                    intent.putExtra("URL", url);
                    startActivity(intent);
                }
            }
        });
    }
}
