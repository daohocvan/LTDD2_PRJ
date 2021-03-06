package com.example.ktc.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ktc.Model.GiaoVien;
import com.example.ktc.Model.MonHoc;

import java.util.ArrayList;

public class DBGiaoVien {
    DBHelper dbHelper;

    public DBGiaoVien(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void Them(GiaoVien GiaoVien)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaGV",GiaoVien.getMagv());
        values.put("TenGV",GiaoVien.getTengv());
        values.put("SDT",GiaoVien.getSdt());
        db.insert("GiaoVien",null,values);
    }

    public  void Sua(GiaoVien GiaoVien)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaGV",GiaoVien.getMagv());
        values.put("TenGV",GiaoVien.getTengv());
        values.put("SDT",GiaoVien.getSdt());
        db.update("GiaoVien",values,"MaGV ='"+ GiaoVien.getMagv() +"'",null);
    }


    public  void Xoa(GiaoVien GiaoVien)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from GiaoVien where magv= '"+GiaoVien.getMagv()+"'";
        db.execSQL(sql);

    }

    public ArrayList<GiaoVien> LayDL()
    {
        ArrayList<GiaoVien> data = new ArrayList<>();
        String sql="select * from GiaoVien";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            GiaoVien GiaoVien = new GiaoVien();
            GiaoVien.setMagv(cursor.getString(0));
            GiaoVien.setTengv(cursor.getString(1));
            GiaoVien.setSdt(cursor.getString(2));
            data.add(GiaoVien);
        }
        while (cursor.moveToNext());

        return  data;
    }

    public ArrayList<GiaoVien> LayDL(String ma)
    {
        ArrayList<GiaoVien> data = new ArrayList<>();
        String sql="select * from GiaoVien where MaGV = '" + ma +"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            GiaoVien GiaoVien = new GiaoVien();
            GiaoVien.setMagv(cursor.getString(0));
            GiaoVien.setTengv(cursor.getString(1));
            GiaoVien.setSdt(cursor.getString(2));
            data.add(GiaoVien);
        }
        while (cursor.moveToNext());

        return  data;
    }
    public ArrayList<String> LayDLMGV()
    {
        ArrayList<String> data = new ArrayList<>();
        String sql="select * from GiaoVien";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            data.add(cursor.getString(0));
        }
        while (cursor.moveToNext());

        return  data;
    }
    public ArrayList<GiaoVien> layTenGiaoVien(String ma) {
        ArrayList<GiaoVien> data = new ArrayList<>();
        String sql = "select DISTINCT GiaoVien.MaGV, GiaoVien.TenGV, GiaoVien.SDT from MonHoc INNER JOIN TTChamBai ON MonHoc.mamonhoc = TTChamBai.mamonhoc  INNER JOIN PhieuChamBai\n" +
                "    ON TTChamBai.sophieu = PhieuChamBai.SoPhieu INNER JOIN GiaoVien ON PhieuChamBai.MaGV = GiaoVien.MaGV WHERE MonHoc.mamonhoc = '" + ma + "' ORDER BY MonHoc.tenmonhoc ASC";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            GiaoVien giaoVien = new GiaoVien();
            giaoVien.setMagv(cursor.getString(0));
            giaoVien.setTengv(cursor.getString(1));
            giaoVien.setSdt(cursor.getString(2));
            data.add(giaoVien);
        }
        while (cursor.moveToNext());
        return data;
    }
}
