package com.phongntcc25tth.qunlngkhcvinphn;

public class DangNhap {
    package com.example.qldangkychp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

    public class DangNhapActivity extends AppCompatActivity {
        EditText edtTenTK, edtMatKhau;
        Button btnDangNhap;
        TextView tvDangKy;
        DBHelper db;

        public static String MA_SV_DANGNHAP = "";
        public static String HO_TEN = "";
        public static String VAITRO = "";

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.activity_dangnhap);
            db = new DBHelper(this);
            edtTenTK = findViewById(R.id.edtTenTK);
            edtMatKhau = findViewById(R.id.edtMatKhau);
            btnDangNhap = findViewById(R.id.btnDangNhap);
            tvDangKy = findViewById(R.id.tvDangKy);

            btnDangNhap.setOnClickListener(v -> {
                String ten = edtTenTK.getText().toString().trim();
                String mk = edtMatKhau.getText().toString().trim();
                if (ten.isEmpty() || mk.isEmpty()) {
                    Toast.makeText(this, "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = db.dangNhap(ten, mk);
                if (c.moveToFirst()) {
                    HO_TEN = c.getString(3);
                    MA_SV_DANGNHAP = c.getString(4);
                    VAITRO = c.getString(5);
                    Toast.makeText(this, " Xin chào " + HO_TEN + "!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, " Sai tài khoản/Mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            });

            tvDangKy.setOnClickListener(v -> startActivity(new Intent(this, DangKyActivity.class)));
        }
    }
}
