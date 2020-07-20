package com.example.ktc.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ktc.Model.TTChamBai;

import java.util.ArrayList;



public class DBTTChamBai {
    DBHelper dbHelper;

    public DBTTChamBai(Context context) {
       dbHelper = new DBHelper(context);
    }

    public void them(TTChamBai ttChamBai)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sophieu", ttChamBai.getSoPhieu());
        values.put("mamonhoc", ttChamBai.getMaMonHoc());
        values.put("sobai", ttChamBai.getSoBai());
        db.insert("TTChamBai", null, values);
    }

    public  void sua(TTChamBai ttChamBai)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sobai", ttChamBai.getSoBai());
        db.update("TTChamBai",values,"sophieu ='" + ttChamBai.getSoPhieu()+ "' and mamonhoc = '" + ttChamBai.getMaMonHoc() + "'",null);
    }


    public  void xoa(String soPhieu, String maMonHoc)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("TTChamBai", "sophieu ='"+ soPhieu +"' and mamonhoc = '" + maMonHoc + "'", null);
    }

    public ArrayList<TTChamBai> layDuLieu()
    {
        ArrayList<TTChamBai> data = new ArrayList<>();
        String sql="select * from TTChamBai";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            TTChamBai ttChamBai = new TTChamBai();
            ttChamBai.setSoPhieu(cursor.getString(0));
            ttChamBai.setMaMonHoc(cursor.getString(1));
            ttChamBai.setSoBai(cursor.getString(2));
            data.add(ttChamBai);
        }
        while (cursor.moveToNext());

        return  data;
    }


    public ArrayList<TTChamBai> layDuLieuBangMaMonHoc(String soPhieu, String ma)
    {
        ArrayList<TTChamBai> data = new ArrayList<>();
        String sql="select * from TTChamBai where mamonhoc = '"+ ma + "' and sophieu = '" + soPhieu + "'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            TTChamBai ttChamBai = new TTChamBai();
            ttChamBai.setSoPhieu(cursor.getString(0));
            ttChamBai.setMaMonHoc(cursor.getString(1));
            ttChamBai.setSoBai(cursor.getString(2));
            data.add(ttChamBai);
        }
        while (cursor.moveToNext());

        return  data;
    }
}
