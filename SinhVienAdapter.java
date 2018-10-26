package com.example.dmx.usingwebservice;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<SinhVien> sinhVienList;

    public SinhVienAdapter(Context context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
 private  class   viewHolder
 {
     TextView txtTen, txtGioiTinh,txtNamSinh;
     ImageView imgHinhAvatar;

 }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        viewHolder viewHolder;

        if (convertView == null)
        {
                viewHolder = new viewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.txtTen = (TextView)convertView.findViewById(R.id.textViewTenSinhVien);
            viewHolder.txtGioiTinh = (TextView)convertView.findViewById(R.id.textViewGioiTinh);
            viewHolder.txtNamSinh = (TextView)convertView.findViewById(R.id.textViewNamSinh);
            viewHolder.imgHinhAvatar = (ImageView) convertView.findViewById(R.id.imageViewavatar);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (SinhVienAdapter.viewHolder)convertView.getTag();
        }

        SinhVien sinhVien  = sinhVienList.get(position);
        viewHolder.txtTen.setText(sinhVien.getTensv());
        viewHolder.txtGioiTinh.setText("Gioi Tinh: " + sinhVien.getGioitinh());
        viewHolder.txtNamSinh.setText("Nam Sinh: " + sinhVien.getNamsinh()+ "");
        viewHolder.imgHinhAvatar.setImageResource(sinhVien.getHinh());

        return convertView;
    }
}
