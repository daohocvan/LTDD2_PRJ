package com.example.ktc.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ktc.Model.PCB;

import java.util.ArrayList;

public class DBPCB {
    DBHelper dbHelper;

    public DBPCB(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void Them(PCB PCB)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SoPhieu",PCB.getSophieu());
        values.put("NgayGiaoBai",PCB.getNgaygiaobai());
        values.put("MaGV",PCB.getMagv());
        db.insert("PhieuChamBai",null,values);
    }

    public  void Sua(PCB PCB)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SoPhieu",PCB.getSophieu());
        values.put("NgayGiaoBai",PCB.getNgaygiaobai());
        values.put("MaGV",PCB.getMagv());
        db.update("PhieuChamBai",values,"SoPhieu ='"+PCB.getSophieu() +"'",null);
    }


    public  void Xoa(PCB PCB)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from PhieuChamBai where SoPhieu= '"+PCB.getSophieu()+"'";
        db.execSQL(sql);

    }

    public ArrayList<PCB> LayDL()
    {
        ArrayList<PCB> data = new ArrayList<>();
        String sql="select * from PhieuChamBai";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            PCB PCB = new PCB();
            PCB.setSophieu(cursor.getString(0));
            PCB.setNgaygiaobai(cursor.getString(1));
            PCB.setMagv(cursor.getString(2));
            data.add(PCB);
        }
        while (cursor.moveToNext());

        return  data;
    }

    public ArrayList<PCB> LayDL(String ma)
    {
        ArrayList<PCB> data = new ArrayList<>();
        String sql="select * from PhieuChamBai where SoPhieu = '"+ma+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            PCB PCB = new PCB();
            PCB.setSophieu(cursor.getString(0));
            PCB.setNgaygiaobai(cursor.getString(1));
            PCB.setMagv(cursor.getString(2));
            data.add(PCB);
        }
        while (cursor.moveToNext());

        return  data;
    }
}
