package com.example.ktc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ktc.Model.GiaoVien;
import com.example.ktc.Model.TTChamBai;
import com.example.ktc.R;

import java.util.ArrayList;

public class CustomAdapterGiaoVienDangCham extends ArrayAdapter
{
    Context context;
    int resource;
    ArrayList<GiaoVien> data_GiaoVien;
    public CustomAdapterGiaoVienDangCham(@NonNull Context context, int resource, ArrayList<GiaoVien> data_GiaoVien) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data_GiaoVien = data_GiaoVien;
    }

    @Override
    public int getCount() {
        return data_GiaoVien.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        TextView tvMaGV = view.findViewById(R.id.tvmagv);
        TextView tvTenGV = view.findViewById(R.id.tvtengv);
        TextView tvSDT = view.findViewById(R.id.tvSDT);
        GiaoVien giaoVien = data_GiaoVien.get(position);
        tvMaGV.setText("Mã: " + giaoVien.getMagv());
        tvTenGV.setText("Tên: " + giaoVien.getTengv());
        tvSDT.setText("Số điện thoại: " + giaoVien.getSdt());
        return view;
    }
}
