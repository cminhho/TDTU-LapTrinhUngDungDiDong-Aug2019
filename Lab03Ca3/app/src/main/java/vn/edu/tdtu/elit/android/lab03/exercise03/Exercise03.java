package vn.edu.tdtu.elit.android.lab03.exercise03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.tdtu.elit.android.lab03.R;

public class Exercise03 extends AppCompatActivity {

  public static final int STATE_LINK_ID = 200;

  private EditText edtLink;
  private Button btnOpenLink;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise2);

    // lookup views
    edtLink = findViewById(R.id.activity_exercise1_edt_link);
    btnOpenLink = findViewById(R.id.activity_exercise1_btn_open_link);

    // event handlers
    btnOpenLink.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String url = edtLink.getText().toString();
        if (url.isEmpty()) {
          Toast.makeText(Exercise03.this, "URL cannot be empty!", Toast.LENGTH_LONG).show();
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
