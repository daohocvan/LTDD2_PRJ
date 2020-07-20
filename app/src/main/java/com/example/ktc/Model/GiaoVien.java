package com.example.ktc.Model;

public class GiaoVien {
    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    public String getTengv() {
        return tengv;
    }

    public void setTengv(String tengv) {
        this.tengv = tengv;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    String magv,tengv,sdt;
    @Override
    public String toString() {
        return "GiaoVien{" +
                "magv='" + magv + '\'' +
                ", tengv=" + tengv +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
