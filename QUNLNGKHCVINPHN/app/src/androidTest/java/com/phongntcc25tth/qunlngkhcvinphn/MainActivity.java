package com.phongntcc25tth.qunlngkhcvinphn;

public class MainActivity {
    package com.example.qldangkychp;
    setContentView(R.layout.activity_main);
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {
        TextView tvXinChao;
        Button btnDSHP, btnDangKy, btnDSDaDK, btnQuanLyHP, btnDuyetDK, btnDangXuat;

        @Override
        protected void onCreate(Bundle b) {
            super.onCreate(b);
            setContentView(R.layout.activity_main);
            tvXinChao = findViewById(R.id.tvXinChao);
            btnDSHP = findViewById(R.id.btnDSHP);
            btnDangKy = findViewById(R.id.btnDangKyHP);
            btnDSDaDK = findViewById(R.id.btnDSDaDK);
            btnQuanLyHP = findViewById(R.id.btnQuanLyHP);
            btnDuyetDK = findViewById(R.id.btnDuyetDK);
            btnDangXuat = findViewById(R.id.btnDangXuat);

            tvXinChao.setText(" Xin chào, " + DangNhapActivity.HO_TEN + "!");
            if (DangNhapActivity.VAITRO.equals("QTV")) {
                btnQuanLyHP.setVisibility(android.view.View.VISIBLE);
                btnDuyetDK.setVisibility(android.view.View.VISIBLE);
            } else {
                btnQuanLyHP.setVisibility(android.view.View.GONE);
                btnDuyetDK.setVisibility(android.view.View.GONE);
            }

            btnDSHP.setOnClickListener(v -> startActivity(new Intent(this, sv_DanhSachHocPhan.class)));
            btnDangKy.setOnClickListener(v -> startActivity(new Intent(this, sv_DangKyHocPhan.class)));
            btnDSDaDK.setOnClickListener(v -> startActivity(new Intent(this, sv_XemDangKy.class)));
            btnQuanLyHP.setOnClickListener(v -> startActivity(new Intent(this, qtv_QuanLyHocPhan.class)));
            btnDuyetDK.setOnClickListener(v -> startActivity(new Intent(this, qtv_DuyetDangKy.class)));
            btnDangXuat.setOnClickListener(v -> {
                DangNhapActivity.MA_SV_DANGNHAP = "";
                DangNhapActivity.HO_TEN = "";
                DangNhapActivity.VAITRO = "";
                finish();
                startActivity(new Intent(this, DangNhapActivity.class));
            });
        }
    }
}
