package vn.edu.tdtu.lab1;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise01 extends Activity {

    Button btnClickMe;
    TextView txtContent;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init view
        btnClickMe = findViewById(R.id.btn_clickMe);
        txtContent = findViewById(R.id.txt_content);
        editText = findViewById(R.id.editText);

        // handle events
        btnClickMe.setOnClickListener(mClickMeListener);
        editText.addTextChangedListener(mTextWatcherListener);
    }

    private View.OnClickListener mClickMeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String inputText = editText.getText().toString();

            if(inputText.isEmpty()) {
                handleEmptyEditText();
                return;
            }

            clearInputText();
            setTextContent(inputText);
        }
    };

    private TextWatcher mTextWatcherListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String inputText = editText.getText().toString();
            handleTextOnChange(inputText);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void handleTextOnChange(String editText) {
        switch (editText){
            case "OFF":
                // Disable click-me button
                btnClickMe.setEnabled(false);
                break;
            case "ON":
                // Enable click-me button
                btnClickMe.setEnabled(true);
                break;
            default:
                break;
        }
    }

    private void handleEmptyEditText() {
        Toast.makeText(Exercise01.this, "Vui lòng nhập thông tin", Toast.LENGTH_LONG).show();
    }

    private void setTextContent(String content) {
        txtContent.setText(content);
    }

    private void clearInputText() {
        editText.setText("");
    }
}
