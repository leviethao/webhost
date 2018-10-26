package com.example.dmx.usingwebservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.RequestQueue;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String ip = "192.168.2.105:81";
    ListView lvSinhVien ;
    SinhVienAdapter adapter;
    String datavt;
    ArrayList<SinhVien> sinhVienArrayList;
    String urldeletesv = "http://"+ip+"/webserviceandroid/deletesinhvien.php";
     String urlgetdata = "http://"+ip+"/webserviceandroid/getdata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        getData(urlgetdata);
        suKien();
    }

    private void suKien() {

        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,UpdateSinhVienActivity.class);
                intent.putExtra("masv",sinhVienArrayList.get(position).getMasv());
                intent.putExtra("tensv",sinhVienArrayList.get(position).getTensv());
                intent.putExtra("gioitinhsv",sinhVienArrayList.get(position).getGioitinh());
                intent.putExtra("namsinhsv",sinhVienArrayList.get(position).getNamsinh());
                startActivity(intent);
                }
        });
        lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                datavt = sinhVienArrayList.get(position).getMasv().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xóa sinh viên!!!");
                builder.setMessage("Bạn có chắc chắn xóa Sinh Viên " + datavt);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaSinhVien(urldeletesv);

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                builder.show();
                return true;
            }
        });

    }

    private void anhXa() {

        lvSinhVien = (ListView)findViewById(R.id.listviewSinhVien);
        sinhVienArrayList = new ArrayList<>();
    }

    private void getData(String url)
    {
        final com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                            sinhVienArrayList.clear();
                            for (int i =0;i<response.length();i++)
                            {
                                try {
                                    JSONObject object = response.getJSONObject(i);
                                        sinhVienArrayList.add(new SinhVien(
                                                object.getString("masv"),
                                                object.getString("tensv"),
                                                object.getString("gioitinh"),
                                                object.getInt("namsinh"),
                                                R.drawable.avatar
                                        ));




                                }

                                catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            sinhVienArrayList.remove(sinhVienArrayList.size()-1);
                            adapter = new SinhVienAdapter(MainActivity.this, R.layout.dong_sinh_vien,sinhVienArrayList);
                            lvSinhVien.setAdapter(adapter);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "loi", Toast.LENGTH_LONG).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);


    }

    private  void XoaSinhVien(String url)
    {

        com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.trim().equals("thanh cong"))
                        {
                            Toast.makeText(MainActivity.this, "Xóa Thành Công",Toast.LENGTH_LONG).show();
                            getData(urlgetdata);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Lỗi Xoa",Toast.LENGTH_LONG).show();
                        }

                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Lỗi Hệ Thống" + error.toString(),Toast.LENGTH_LONG);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("masv",String.valueOf(datavt));
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

         getMenuInflater().inflate(R.menu.menu_sinhvien,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case  R.id.menuAddSV: {
                startActivity(new Intent(MainActivity.this, ThemSinhVienActivity.class));
            }
                break;
            case  R.id.menuDeleteSV: {

            }
            break;

        }

        return super.onOptionsItemSelected(item);
    }
}
