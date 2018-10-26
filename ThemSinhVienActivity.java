package com.example.dmx.usingwebservice;

import android.content.Intent;
import android.net.http.RequestQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ThemSinhVienActivity extends AppCompatActivity {
    EditText edtmasv,edttensv,edtgioitinhsv,edtnamsinhsv;
    Button btnThem,btnHuy;
    String ip = "192.168.2.105:81";
    String urlinsert = "http://"+ip+"/webserviceandroid/insertsinhvien.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinh_vien);
        anhXa();
        suKien();
    }

    private void suKien() {

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String masv = edtmasv.getText().toString().trim();
                    String tensv = edttensv.getText().toString().trim();
                    String gtsv = edtgioitinhsv.getText().toString().trim();
                    String nssv = edtnamsinhsv.getText().toString().trim();
                if (masv.isEmpty() || tensv.isEmpty() || gtsv.isEmpty() || nssv.isEmpty())
                {
                    Toast.makeText(ThemSinhVienActivity.this,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();

                }
                else {
                    themSinhVien(urlinsert);
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
    private  void themSinhVien(String url)
    {

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals("thanh cong"))
                        {
                            Toast.makeText(ThemSinhVienActivity.this, "Thêm Thành Công",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ThemSinhVienActivity.this,MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(ThemSinhVienActivity.this,"Lỗi Thêm",Toast.LENGTH_LONG).show();
                        }

                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThemSinhVienActivity.this,"Lỗi Hệ Thống" + error.toString(),Toast.LENGTH_LONG);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maSV",edtmasv.getText().toString().trim());
                params.put("tenSV",edttensv.getText().toString().trim());
                params.put("gioitinhSV",edtgioitinhsv.getText().toString().trim());
                params.put("namsinhSV",edtnamsinhsv.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void anhXa() {
        edtmasv = (EditText)findViewById(R.id.editTextMasv);
        edttensv = (EditText)findViewById(R.id.editTextTensv);
        edtgioitinhsv = (EditText)findViewById(R.id.editTextGioitinhsv);
        edtnamsinhsv = (EditText)findViewById(R.id.editTextNamSinhsv);

        btnThem = (Button) findViewById(R.id.buttonThemsv);
        btnHuy = (Button) findViewById(R.id.buttonHuy);


    }
}
