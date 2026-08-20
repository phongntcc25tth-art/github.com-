package com.phongntcc25tth.qunlngkhcvienphn;

public class DuyetDangKy {
    package com.example.qldangkychp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

    public class qtv_DuyetDangKy extends AppCompatActivity {
        ListView lvPhieu;
        Button btnDongY, btnTuChoi;
        DBHelper db;
        ArrayList<Integer> dsMaPhieu;
        ArrayList<String> dsHienThi;
        int viTriChon = -1;

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.qtv_duyet_dangky);
            db = new DBHelper(this);
            lvPhieu = findViewById(R.id.lvPhieuDK);
            btnDongY = findViewById(R.id.btnDongY);
            btnTuChoi = findViewById(R.id.btnTuChoi);

            loadDanhSach();

            lvPhieu.setOnItemClickListener((parent, view, position, id) -> viTriChon = position);

            btnDongY.setOnClickListener(v -> {
                if (viTriChon < 0) { Toast.makeText(this, "Chọn phiếu cần duyệt!", Toast.LENGTH_SHORT).show(); return; }
                int kq = db.duyetPhieu(dsMaPhieu.get(viTriChon), "DaDuyet");
                Toast.makeText(this, kq > 0 ? " Đã duyệt!" : "Lỗi!", Toast.LENGTH_SHORT).show();
                loadDanhSach();
            });

            btnTuChoi.setOnClickListener(v -> {
                if (viTriChon < 0) { Toast.makeText(this, "Chọn phiếu!", Toast.LENGTH_SHORT).show(); return; }
                int kq = db.duyetPhieu(dsMaPhieu.get(viTriChon), "TuChoi");
                Toast.makeText(this, kq > 0 ? " Đã từ chối!" : "Lỗi!", Toast.LENGTH_SHORT).show();
                loadDanhSach();
            });
        }

        void loadDanhSach() {
            dsMaPhieu = new ArrayList<>();
            dsHienThi = new ArrayList<>();
            Cursor c = db.layDSChờDuyet();
            if (c.moveToFirst()) {
                do {
                    dsMaPhieu.add(c.getInt(0));
                    dsHienThi.add(" Phiếu #" + c.getInt(0) +
                            "\n   SV: " + c.getString(5) +
                            "\n   HP: " + c.getString(6) +
                            "\n   Ngày: " + c.getString(3));
                } while (c.moveToNext());
            }
            c.close();
            ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsHienThi);
            lvPhieu.setAdapter(ad);
            viTriChon = -1;
        }
    }
}
