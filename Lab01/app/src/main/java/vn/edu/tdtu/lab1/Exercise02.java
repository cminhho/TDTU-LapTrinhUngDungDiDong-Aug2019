package vn.edu.tdtu.lab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Exercise02 extends AppCompatActivity {
    CheckBox cbAndroid, cbIOS, cbWindows, cbRIM;
    RadioButton rbAndroid, rbIOS, rbWindows, rbRIM;
    TextView txtAndroid, txtIOS, txtWindows, txtRIM;
    Button btnSeeResult;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_exercise2);

        // init view
        cbAndroid = findViewById(R.id.cbAndroid);
        cbIOS = findViewById(R.id.cbIOS);
        cbWindows = findViewById(R.id.cbWindows);
        cbRIM = findViewById(R.id.cbRIM);

        txtAndroid = findViewById(R.id.txtAndroid);
        txtIOS = findViewById(R.id.txtIOS);
        txtWindows = findViewById(R.id.txtWindows);
        txtRIM = findViewById(R.id.txtRIM);

        btnSeeResult = findViewById(R.id.btnSeeResult);

        // handle events
        btnSeeResult.setOnClickListener(seeResultListener);
    }

    private View.OnClickListener seeResultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateTextResults();
        }
    };

    private void updateTextResults() {
        txtAndroid.setText(String.valueOf(cbAndroid.isChecked()));
        txtIOS.setText(String.valueOf(cbIOS.isChecked()));
        txtWindows.setText(String.valueOf(cbWindows.isChecked()));
        txtRIM.setText(String.valueOf(cbRIM.isChecked()));
    }
}
