package com.phongntcc25tth.qunlngkhcvienphn;

public class DangKyActivity {
    package com.example.qldangkychp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

    public class DangKyActivity extends AppCompatActivity {
        EditText edtHoTen, edtMaSV, edtTenTK, edtMK, edtNhapLaiMK;
        Button btnDangKy;
        DBHelper db;

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.activity_dangky);
            db = new DBHelper(this);

            edtHoTen = findViewById(R.id.edtHoTen);
            edtMaSV = findViewById(R.id.edtMaSV);
            edtTenTK = findViewById(R.id.edtTenTK);
            edtMK = findViewById(R.id.edtMatKhau);
            edtNhapLaiMK = findViewById(R.id.edtNhapLaiMK);
            btnDangKy = findViewById(R.id.btnDangKy);

            btnDangKy.setOnClickListener(v -> {
                String hoTen = edtHoTen.getText().toString().trim();
                String maSV = edtMaSV.getText().toString().trim();
                String tenTK = edtTenTK.getText().toString().trim();
                String mk = edtMK.getText().toString().trim();
                String nl = edtNhapLaiMK.getText().toString().trim();

                if (hoTen.isEmpty() || maSV.isEmpty() || tenTK.isEmpty() || mk.isEmpty()) {
                    Toast.makeText(this, " Điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!mk.equals(nl)) {
                    Toast.makeText(this, " Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                long kq = db.dangKySV(tenTK, mk, hoTen, maSV);
                if (kq > 0) {
                    Toast.makeText(this, " Đăng ký thành công! Đăng nhập nhé", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, " Mã SV hoặc Tên TK đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
