package com.tdtu.exercise.lab01;

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

public class Exercise01 extends Activity {

    private Button btnClickMe;
    private EditText edtContent;
    private TextView txtContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab01_exercise01);

        // initialize views
        btnClickMe = findViewById(R.id.activity_exercise01_btn_clickme);
        edtContent = findViewById(R.id.activity_exercise01_et_content);
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
            String content = edtContent.getText().toString();
            switch (content.toUpperCase()) {
                case "ON":
                    btnClickMe.setEnabled(true);
                    break;
                case "OFF":
                    btnClickMe.setEnabled(false);
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private View.OnClickListener onBtnClickMeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String content = edtContent.getText().toString();
            if(content.isEmpty()){
                Toast.makeText(Exercise01.this, R.string.exercise01_error_content_require, Toast.LENGTH_LONG).show();
            } else {
                txtContent.setText(content);
                edtContent.getText().clear();
            }
        }
    };
}
