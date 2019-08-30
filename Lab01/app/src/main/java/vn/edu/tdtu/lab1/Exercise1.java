package vn.edu.tdtu.lab1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise1 extends Activity {
    private Button btnClickMe;
    private EditText edtContent;
    private TextView txtContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);

        // initialize view
        btnClickMe = findViewById(R.id.btnClickMe);
        edtContent = findViewById(R.id.edtContent);
        txtContent = findViewById(R.id.txtContent);

        // event handlers
        edtContent.addTextChangedListener(onEdtContentChanged);
        btnClickMe.setOnClickListener(onBtnClickMeClicked);
    }

    private TextWatcher onEdtContentChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String contentChanged = edtContent.getText().toString();
            switch (contentChanged.toUpperCase()){
                case "ON":
                    btnClickMe.setEnabled(true);
                case "OFF":
                    btnClickMe.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private View.OnClickListener onBtnClickMeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String edtContentContent = edtContent.getText().toString();
            if(edtContentContent.isEmpty()){
                Toast.makeText(Exercise1.this, R.string.message_text_require, Toast.LENGTH_SHORT).show();
            } else {
                txtContent.setText(edtContentContent);
                edtContent.getText().clear();
            }
        }
    };
}
