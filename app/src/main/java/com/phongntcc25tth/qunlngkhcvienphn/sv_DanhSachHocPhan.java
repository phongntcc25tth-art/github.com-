package com.phongntcc25tth.qunlngkhcvienphn;

public class sv_DanhSachHocPhan {
    package com.example.qldangkychp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

    public class sv_DanhSachHocPhan extends AppCompatActivity {
        EditText edtTim;
        Button btnTim;
        ListView lvHP;
        DBHelper db;
        ArrayList<String> dsHP;
        ArrayAdapter<String> adapter;

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.sv_danhsach_hp);
            db = new DBHelper(this);

            edtTim = findViewById(R.id.edtTimKiem);
            btnTim = findViewById(R.id.btnTim);
            lvHP = findViewById(R.id.lvHocPhan);

            loadDanhSach(""); // Tải tất cả

            btnTim.setOnClickListener(v -> {
                String tk = edtTim.getText().toString().trim();
                loadDanhSach(tk);
            });
        }

        void loadDanhSach(String tuKhoa) {
            dsHP = new ArrayList<>();
            Cursor c = tuKhoa.isEmpty() ? db.layDSHP() : db.timHP(tuKhoa);
            if (c.moveToFirst()) {
                do {
                    dsHP.add(" " + c.getString(0) + " - " + c.getString(1) +
                            "\n   " + c.getInt(2) + " tín chỉ | HK" + c.getInt(4) +
                            " | Sĩ số: " + c.getInt(6) + "/" + c.getInt(5));
                } while (c.moveToNext());
            }
            c.close();

            if (dsHP.isEmpty()) {
                Toast.makeText(this, "Không tìm thấy học phần nào!", Toast.LENGTH_SHORT).show();
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsHP);
            lvHP.setAdapter(adapter);
        }
    }
}
