package com.tdtu.android.lab01ca4;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button btnClickMe;
    private EditText edtContent;
    private TextView txtContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise01);

        // initialize views
        btnClickMe = findViewById(R.id.activity_exercise01_btn_content);
        edtContent = findViewById(R.id.activity_exercise01_edt_content);
        txtContent = findViewById(R.id.activity_exercise01_txt_content);

        // event handlers
        btnClickMe.setOnClickListener(onBtnClickMeClicked);
        edtContent.addTextChangedListener(onEdtContentChanged);
    }

    private TextWatcher onEdtContentChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String content = edtContent.getText().toString();
            if (content.toUpperCase().equals("ON")) {
                btnClickMe.setEnabled(true);
            } else if (content.toUpperCase().equals("OFF")) {
                btnClickMe.setEnabled(false);
            }
        }
    };

    private View.OnClickListener onBtnClickMeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String content = edtContent.getText().toString();
            if (content.isEmpty()) {
                Toast.makeText(MainActivity.this, R.string.exercise01_err_content_required, Toast.LENGTH_SHORT).show();
            } else {
                txtContent.setText(content);
                edtContent.getText().clear();
            }
        }
    };
}
