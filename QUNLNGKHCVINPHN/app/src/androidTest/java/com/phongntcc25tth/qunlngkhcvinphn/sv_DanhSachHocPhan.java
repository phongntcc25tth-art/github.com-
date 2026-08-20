package com.phongntcc25tth.qunlngkhcvinphn;

public class sv_DanhSachHocPhan {
    package com.example.qldangkychp;

import android.database.Cursor;
import android.os.Bundle;
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

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.sv_danhsach_hp);
            db = new DBHelper(this);
            edtTim = findViewById(R.id.edtTimKiem);
            btnTim = findViewById(R.id.btnTim);
            lvHP = findViewById(R.id.lvHocPhan);
            loadDanhSach("");
            btnTim.setOnClickListener(v -> loadDanhSach(edtTim.getText().toString().trim()));
        }

        void loadDanhSach(String tuKhoa) {
            dsHP = new ArrayList<>();
            Cursor c = tuKhoa.isEmpty() ? db.layDSHP() : db.timHP(tuKhoa);
            if (c.moveToFirst()) {
                do {
                    dsHP.add(" " + c.getString(0) + " - " + c.getString(1) +
                            "\n   " + c.getInt(2) + " tín chỉ | HK" + c.getInt(4) +
                            "\n   Sĩ số: " + c.getInt(6) + "/" + c.getInt(5));
                } while (c.moveToNext());
            }
            c.close();
            if (dsHP.isEmpty()) Toast.makeText(this, "Không tìm thấy!", Toast.LENGTH_SHORT).show();
            lvHP.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsHP));
        }
    }
}
