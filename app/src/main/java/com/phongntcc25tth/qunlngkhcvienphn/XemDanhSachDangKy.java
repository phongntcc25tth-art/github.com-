package com.phongntcc25tth.qunlngkhcvienphn;

public class XemDanhSachDangKy {
    package com.example.qldangkychp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

    public class sv_XemDanhSachDangKy extends AppCompatActivity {
        ListView lvDS;
        DBHelper db;

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.sv_xem_dadangky);
            db = new DBHelper(this);
            lvDS = findViewById(R.id.lvDaDangKy);

            ArrayList<String> ds = new ArrayList<>();
            Cursor c = db.layDSDangKySV(DangNhapActivity.MA_SV_DANGNHAP);
            if (c.moveToFirst()) {
                do {
                    String tt = c.getString(4);
                    String trangThai = tt.equals("ChoDuyet") ? " Chờ duyệt" :
                            tt.equals("DaDuyet") ? " Đã duyệt" : "❌ Từ chối";
                    ds.add(" " + c.getString(2) + " - " + c.getString(5) +
                            "\n   " + c.getString(6) + " tín chỉ | Ngày: " + c.getString(3) +
                            "\n   Trạng thái: " + trangThai);
                } while (c.moveToNext());
            }
            c.close();

            if (ds.isEmpty()) {
                Toast.makeText(this, "Bạn chưa đăng ký học phần nào!", Toast.LENGTH_SHORT).show();
            }
            lvDS.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ds));
        }
    }
}
