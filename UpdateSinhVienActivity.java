package com.example.dmx.usingwebservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateSinhVienActivity extends AppCompatActivity {
    String ma,ten,gioitinh;
    int namsinh;
    TextView tvmasv;
    EditText edttensv, edtgioitinhsv, edtnamsinhsv;
    Button btnDongY, btnHuy;
    String ip = "192.168.2.105:81";
    String urlupdate = "http://"+ip+"/webserviceandroid/updatesinhvien.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);
        ganData();
        suKien();

    }

    private void suKien() {

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoten = edttensv.getText().toString().trim();
                String gioitinh = edtgioitinhsv.getText().toString().trim();
                String namsinh = edtnamsinhsv.getText().toString().trim();

                if (hoten.matches("") || gioitinh.equals("") || namsinh.length()==0)
                {
                    Toast.makeText(UpdateSinhVienActivity.this,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();

                }
                else {
                    SuaSinhVien(urlupdate);
                }

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void ganData() {
        Intent intent = getIntent();
        ma = intent.getStringExtra("masv");
        tvmasv = (TextView)findViewById(R.id.txtMasv);
        tvmasv.setText(ma);

        ten = intent.getStringExtra("tensv");
         edttensv = (EditText) findViewById(R.id.editTextTensvUpdate);
        edttensv.setText(ten);

        gioitinh = intent.getStringExtra("gioitinhsv");
         edtgioitinhsv = (EditText) findViewById(R.id.editTextGioiTinhUpdate);
        edtgioitinhsv.setText(gioitinh);


        Intent intent1 = getIntent();
        namsinh = intent1.getIntExtra("namsinhsv",0);
        edtnamsinhsv = (EditText) findViewById(R.id.editTextNamSinhUpdate);
        edtnamsinhsv.setText(namsinh + " ");
        btnDongY = (Button)findViewById(R.id.buttonDongYUpdate);
        btnHuy = (Button)findViewById(R.id.buttonThoatUpdate);
    }

    private  void SuaSinhVien(String url)
    {

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals("thanh cong"))
                        {
                            Toast.makeText(UpdateSinhVienActivity.this, "Sửa Thành Công",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(UpdateSinhVienActivity.this,MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(UpdateSinhVienActivity.this,"Lỗi Update",Toast.LENGTH_LONG).show();
                        }

                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateSinhVienActivity.this,"Lỗi Hệ Thống" + error.toString(),Toast.LENGTH_LONG);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("masv",String.valueOf(ma));
                params.put("tensv",edttensv.getText().toString().trim());
                params.put("gioitinhsv",edtgioitinhsv.getText().toString().trim());
                params.put("namsinhsv",edtnamsinhsv.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
