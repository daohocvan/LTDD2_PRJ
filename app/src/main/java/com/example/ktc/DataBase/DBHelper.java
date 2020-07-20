package com.example.ktc.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "QLChamBai", null, 1);
    }

    private static final String GiaoVien = "create table GiaoVien(MaGV text, TenGV Text , SDT text )";
    private static final String PhieuChamBai = "create table PhieuChamBai(SoPhieu text, NgayGiaoBai Text , MaGV text )";
    private static final String MonHoc = "create table MonHoc(mamonhoc text, tenmonhoc text, chiphi text)";
    private static final String ThongTinChamBai = "create table TTChamBai(sophieu text, mamonhoc text, sobai text)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GiaoVien);
        db.execSQL(PhieuChamBai);
        db.execSQL(MonHoc);
        db.execSQL(ThongTinChamBai);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
