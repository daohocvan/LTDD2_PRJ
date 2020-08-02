package com.example.ktc.Model;

public class PCB {
    String sophieu;

    public String getSophieu() {
        return sophieu;
    }

    public void setSophieu(String sophieu) {
        this.sophieu = sophieu;
    }

    public String getNgaygiaobai() {
        return ngaygiaobai;
    }

    public void setNgaygiaobai(String ngaygiaobai) {
        this.ngaygiaobai = ngaygiaobai;
    }

    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    String ngaygiaobai;
    String magv;
    @Override
    public String toString() {
        return "PCB{" +sophieu + '\'' +
                ", ngaygiaobai=" + ngaygiaobai +
                ", magv='" + magv + '\'' +
                '}';
    }
}
