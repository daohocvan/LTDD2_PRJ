package com.example.ktc.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ktc.Model.MonHoc;

import java.util.ArrayList;


public class DBMonHoc {
    DBHelper dbHelper;

    public DBMonHoc(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void them(MonHoc monHoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mamonhoc", monHoc.getMaMonHoc());
        values.put("tenmonhoc", monHoc.getTenMonHoc());
        values.put("chiphi", monHoc.getChiPhi());
        db.insert("MonHoc", null, values);
    }

    public void sua(MonHoc monHoc) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (!monHoc.getTenMonHoc().equals(""))
            values.put("tenmonhoc", monHoc.getTenMonHoc());
        if (!monHoc.getChiPhi().equals(""))
            values.put("chiphi", monHoc.getChiPhi());
        db.update("MonHoc", values, "mamonhoc ='" + monHoc.getMaMonHoc() + "'", null);
    }


    public void xoa(String maMonHoc) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("MonHoc", "mamonhoc ='" + maMonHoc + "'", null);
    }

    public ArrayList<MonHoc> layDuLieu() {
        ArrayList<MonHoc> data = new ArrayList<>();
        String sql = "select * from MonHoc";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(cursor.getString(0));
            monHoc.setTenMonHoc(cursor.getString(1));
            monHoc.setChiPhi(cursor.getString(2));
            data.add(monHoc);
        }
        while (cursor.moveToNext());

        return data;
    }


    public ArrayList<MonHoc> layDuLieuBangMaMonHoc(String ma) {
        ArrayList<MonHoc> data = new ArrayList<>();
        String sql = "select tenmonhoc, chiphi from MonHoc where mamonhoc = '" + ma + "'";
        ;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            MonHoc monHoc = new MonHoc();
            monHoc.setTenMonHoc(cursor.getString(0));
            monHoc.setChiPhi(cursor.getString(1));
            data.add(monHoc);
        }
        while (cursor.moveToNext());

        return data;
    }

    public ArrayList<String> layMaMonHoc() {
        {
            ArrayList<String> data = new ArrayList<>();
            String sql = "select mamonhoc from MonHoc";
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToFirst();
            do {
                data.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
            return data;
        }
    }

    public ArrayList<MonHoc> lay(String ma) {
        ArrayList<MonHoc> data = new ArrayList<>();
        String sql = "SELECT MonHoc.mamonhoc, MonHoc.tenmonhoc, MonHoc.chiphi from TTChamBai INNER JOIN PhieuChamBai ON TTChamBai.sophieu = PhieuChamBai.SoPhieu " +
                "INNER JOIN MonHoc ON TTChamBai.mamonhoc = MonHoc.mamonhoc  where PhieuChamBai.MaGV = '" + ma + "' ORDER BY MonHoc.tenmonhoc ASC";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            MonHoc monHoc = new MonHoc();
            monHoc.setMaMonHoc(cursor.getString(0));
            monHoc.setTenMonHoc(cursor.getString(1));
            monHoc.setChiPhi(cursor.getString(2));
            data.add(monHoc);
        }
        while (cursor.moveToNext());

        return data;
    }
}