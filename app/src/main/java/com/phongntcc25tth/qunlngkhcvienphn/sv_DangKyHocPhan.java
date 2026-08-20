package com.phongntcc25tth.qunlngkhcvienphn;

public class sv_DangKyHocPhan {
    package com.example.qldangkychp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

    public class sv_DangKyHocPhan extends AppCompatActivity {
        Spinner spHP;
        Button btnDangKy;
        DBHelper db;
        ArrayList<String> dsMaHP, dsTenHP;

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.sv_dangky_hp);
            db = new DBHelper(this);
            spHP = findViewById(R.id.spinnerHP);
            btnDangKy = findViewById(R.id.btnXacNhanDK);

            // Tải danh sách học phần vào Spinner
            dsMaHP = new ArrayList<>();
            dsTenHP = new ArrayList<>();
            Cursor c = db.layDSHP();
            if (c.moveToFirst()) {
                do {
                    dsMaHP.add(c.getString(0));
                    dsTenHP.add(c.getString(0) + " - " + c.getString(1));
                } while (c.moveToNext());
            }
            c.close();

            ArrayAdapter<String> ad = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, dsTenHP);
            spHP.setAdapter(ad);

            // Nút đăng ký
            btnDangKy.setOnClickListener(v -> {
                int vt = spHP.getSelectedItemPosition();
                if (vt < 0) {
                    Toast.makeText(this, " Chọn học phần cần đăng ký!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String maHP = dsMaHP.get(vt);
                String maSV = DangNhapActivity.MA_SV_DANGNHAP;
                String ngayDK = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                long kq = db.dangKyHP(maSV, maHP, ngayDK);
                if (kq > 0) {
                    Toast.makeText(this, " Đăng ký thành công! Chờ duyệt.", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (kq == -1) {
                    Toast.makeText(this, " Bạn đã đăng ký học phần này rồi!", Toast.LENGTH_SHORT).show();
                } else if (kq == -3) {
                    Toast.makeText(this, " Học phần đã đủ số lượng!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Lỗi đăng ký!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
