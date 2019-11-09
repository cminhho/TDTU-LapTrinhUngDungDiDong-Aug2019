package com.example.admin.lab09.exercise01;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.lab09.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercise01 extends AppCompatActivity {

  private static final String DEFAULT_DOWNLOAD_LINK = "https://www.nicepng.com/png/full/18-180932_android-png-images-picture-transparent-stock-android-jetpack.png";
  private URL downloadUrl;
  private InputStream is;
  private Bitmap bmImg;
  private Button btnDownloadSyncTask;
  private ImageView imageView;
  private ProgressDialog progressDialog;
  private EditText edtLink;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exercise_01);

    btnDownloadSyncTask = findViewById(R.id.btnDownloadSyncTask);
    edtLink = findViewById(R.id.edtLink);
    imageView = findViewById(R.id.image);

    edtLink.setText(DEFAULT_DOWNLOAD_LINK);

    btnDownloadSyncTask.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String downloadUrl = edtLink.getText().toString().trim();
        if (downloadUrl.isEmpty()) {
          Toast.makeText(Exercise01.this, "Download link cannot be empty!!!", Toast.LENGTH_LONG)
              .show();
          return;
        }
        AsyncTaskExample asyncTask = new AsyncTaskExample();
        asyncTask.execute(downloadUrl);
      }
    });
  }

  private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progressDialog = new ProgressDialog(Exercise01.this);
      progressDialog.setMessage("Downloading...");
      progressDialog.setIndeterminate(false);
      progressDialog.setCancelable(false);
      progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
      try {
        downloadUrl = new URL(strings[0]);
        HttpURLConnection httpURLConnection = createHttpConnection(downloadUrl);

        for (int i = 1; i <= 5; i++) {
          publishProgress(i * 20 + " %");
        }

        displayDownloadedImage(httpURLConnection);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return bmImg;
    }

    @Override
    protected void onProgressUpdate(String... values) {
      Log.i(Exercise01.class.getName(), values[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      super.onPostExecute(bitmap);
      if (imageView != null) {
        progressDialog.hide();
        imageView.setImageBitmap(bitmap);
      } else {
        progressDialog.show();
      }
    }
  }

  private HttpURLConnection createHttpConnection(URL downloadUrl) throws IOException {
    HttpURLConnection httpURLConnection = (HttpURLConnection) downloadUrl.openConnection();
    httpURLConnection.setDoInput(true);
    httpURLConnection.connect();
    return httpURLConnection;
  }

  private void displayDownloadedImage(HttpURLConnection httpURLConnection) throws IOException {
    is = httpURLConnection.getInputStream();
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.RGB_565;
    bmImg = BitmapFactory.decodeStream(is, null, options);
  }

}