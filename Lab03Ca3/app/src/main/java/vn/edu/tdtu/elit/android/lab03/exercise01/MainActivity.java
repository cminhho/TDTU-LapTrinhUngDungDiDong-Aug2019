package vn.edu.tdtu.elit.android.lab03.exercise01;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import vn.edu.tdtu.elit.android.lab03.R;

public class MainActivity extends AppCompatActivity {
    public static final String USER_NAME_INTENT = "USER_NAME";
    public static final String USER_EMAIL_INTENT = "USER_EMAIL";
    public static final int WELCOME_CODE = 0x9345;

    private EditText edtEmail;
    private Button btnLogin;
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // lookup views
        edtEmail = findViewById(R.id.edtEmail);
        btnLogin = findViewById(R.id.btnLogin);
        txtMessage = findViewById(R.id.txtMessage);

        // event handlers
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWelcomeActivity();
            }
        });
    }

    private void openWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        String userName = edtEmail.getText().toString();
        intent.putExtra(USER_EMAIL_INTENT, userName);
        startActivityForResult(intent, WELCOME_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == WELCOME_CODE) {
            if(resultCode == Activity.RESULT_OK){
                String studentName = data.getStringExtra(USER_NAME_INTENT);
                edtEmail.setText(studentName);
                txtMessage.setText("Hẹn gặp lại!");
                btnLogin.setVisibility(View.GONE);
            }
        }
    }
}
