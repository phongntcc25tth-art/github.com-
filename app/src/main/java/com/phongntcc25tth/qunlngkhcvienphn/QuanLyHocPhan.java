package com.phongntcc25tth.qunlngkhcvienphn;

public class QuanLyHocPhan {package com.example.qldangkychp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

    public class qtv_QuanLyHocPhan extends AppCompatActivity {
        EditText edtMaHP, edtTenHP, edtTC, edtNganh, edtHK;
        Button btnThem, btnSua, btnXoa;
        DBHelper db;

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.qtv_quanly_hp);
            db = new DBHelper(this);

            edtMaHP = findViewById(R.id.edtMaHP);
            edtTenHP = findViewById(R.id.edtTenHP);
            edtTC = findViewById(R.id.edtTinChi);
            edtNganh = findViewById(R.id.edtNganh);
            edtHK = findViewById(R.id.edtHocKy);
            btnThem = findViewById(R.id.btnThemHP);
            btnSua = findViewById(R.id.btnSuaHP);
            btnXoa = findViewById(R.id.btnXoaHP);

            btnThem.setOnClickListener(v -> {
                String ma = edtMaHP.getText().toString().trim();
                String ten = edtTenHP.getText().toString().trim();
                String nganh = edtNganh.getText().toString().trim();
                if (ma.isEmpty() || ten.isEmpty()) {
                    Toast.makeText(this, "⚠️ Nhập Mã và Tên học phần!", Toast.LENGTH_SHORT).show();
                    return;
                }
                long kq = db.themHP(ma, ten,
                        Integer.parseInt(edtTC.getText().toString().trim()),
                        nganh,
                        Integer.parseInt(edtHK.getText().toString().trim()));
                Toast.makeText(this, kq > 0 ? " Thêm thành công!" : "❌ Mã HP đã tồn tại!", Toast.LENGTH_SHORT).show();
            });

            btnSua.setOnClickListener(v -> {
                String ma = edtMaHP.getText().toString().trim();
                int kq = db.suaHP(ma,
                        edtTenHP.getText().toString().trim(),
                        Integer.parseInt(edtTC.getText().toString().trim()),
                        edtNganh.getText().toString().trim(),
                        Integer.parseInt(edtHK.getText().toString().trim()));
                Toast.makeText(this, kq > 0 ? " Cập nhật thành công!" : "❌ Không tìm thấy HP!", Toast.LENGTH_SHORT).show();
            });

            btnXoa.setOnClickListener(v -> {
                String ma = edtMaHP.getText().toString().trim();
                int kq = db.xoaHP(ma);
                Toast.makeText(this, kq > 0 ? " Xóa thành công!" : "❌ Không tìm thấy HP!", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
