package com.tdt.lab03.exercise02;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tdt.lab03.R;

public class Exercise02 extends AppCompatActivity{
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
                    Toast.makeText(Exercise02.this, "URL cannot be empty!", Toast.LENGTH_LONG).show();
                } else {
                    openBrowser(url);
                }
            }
        });
    }

    private void openBrowser(String url) {
        Uri targetPage = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, targetPage);
        startActivity(browserIntent);
    }
}
