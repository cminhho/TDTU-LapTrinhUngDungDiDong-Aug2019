package vn.edu.tdtu.elit.android.lab03.exercise01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.edu.tdtu.elit.android.lab03.R;

public class WelcomeActivity extends AppCompatActivity {
    private TextView tvMessage;
    private EditText edtName;
    private Button btnSaveAndQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // lookup views
        tvMessage = findViewById(R.id.tvMessage);
        edtName = findViewById(R.id.edtName);
        btnSaveAndQuit = findViewById(R.id.btnSaveAndQuit);

        onActivityResult();
        // event handlers
        btnSaveAndQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String studentName = edtName.getText().toString();
                intent.putExtra(MainActivity.USER_NAME_INTENT, studentName);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void onActivityResult() {
        String studentEmail = getIntent().getStringExtra(MainActivity.USER_EMAIL_INTENT);
        tvMessage.setText("Xin chào, " + studentEmail + ". Vui lòng nhập tên:");
    }
}
