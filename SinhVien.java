package com.example.dmx.usingwebservice;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private  String masv;
    private  String tensv;
    private  String gioitinh;
    private int namsinh;
    private int Hinh;

    public SinhVien(String masv, String tensv, String gioitinh, int namsinh, int Hinh) {
        this.masv = masv;
        this.tensv = tensv;
        this.gioitinh = gioitinh;
        this.namsinh = namsinh;
        this.Hinh = Hinh;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }
}
