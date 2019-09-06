package vn.edu.tdtu.lab02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Exercise1Activity extends AppCompatActivity {
    private EditText edtPassword;
    private EditText edtUsername;
    private CheckBox cbRememberPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);

        // Setup UI
        edtUsername = findViewById(R.id.activity_exercise1_edt_username);
        edtPassword = findViewById(R.id.activity_exercise1_edt_password);
        cbRememberPassword = findViewById(R.id.activity_exercise1_cb_remember_password);
        btnLogin = findViewById(R.id.activity_exercise1_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                boolean isRememberPasswordChecked = cbRememberPassword.isChecked();

                // Xuất ra thông báo bằng Toast cho người dùng nếu username hoặc password rỗng
                if (username.isEmpty() || password.isEmpty()) {
                    showToast("Username hoặc password rỗng");
                }

                // Nếu mật khẩu và tên đăng nhập giống nhau thì xuất ra thông báo đăng nhập thành công, ngược lại thì thông báo đăng nhập thất bại.
                if(username.equals(password)){
                    showToast("Đăng nhập thành công");
                } else {
                    showToast("Đăng nhập thất bại");
                }

                // Nếu người dùng chọn “Remember Password” thì xuất thêm thông báo “Mật khẩu đã được lưu”.
                if(isRememberPasswordChecked){
                    showToast("Mật khẩu đã được lưu!");
                }
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(Exercise1Activity.this, message, Toast.LENGTH_LONG).show();
    }
}