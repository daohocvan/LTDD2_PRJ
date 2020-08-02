package com.example.ktc.Model;

import androidx.annotation.NonNull;

public class TTChamBai {
    String soPhieu, soBai;
    String maMonHoc;

    public String getSoPhieu() {
        return soPhieu;
    }

    public String getSoBai() {
        return soBai;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public void setSoBai(String soBai) {
        this.soBai = soBai;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    @NonNull
    @Override
    public String toString() {
        return "TTChamBai{" + soPhieu + '\'' +
                ", soBai=" + soBai +
                ", maMonHoc='" + maMonHoc + '\'' +
                '}';
    }
}
