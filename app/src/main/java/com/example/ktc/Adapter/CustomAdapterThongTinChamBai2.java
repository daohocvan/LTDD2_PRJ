package com.example.ktc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ktc.Model.MonHoc;
import com.example.ktc.Model.TTChamBai;
import com.example.ktc.R;


import java.util.ArrayList;

public class CustomAdapterThongTinChamBai2 extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<MonHoc> data_MonHoc;
    public CustomAdapterThongTinChamBai2(@NonNull Context context, int resource,    ArrayList<MonHoc> data_MonHoc) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data_MonHoc = data_MonHoc;
    }

    @Override
    public int getCount() {
        return data_MonHoc.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        TextView tvMaMonHoc = view.findViewById(R.id.tvmamon);
        TextView tvTenMonHoc = view.findViewById(R.id.tvtenmonhoc);
        TextView tvChiPhi = view.findViewById(R.id.tvchiphi);
        MonHoc monHoc = data_MonHoc.get(position);
        tvMaMonHoc.setText("Mã môn học: " + monHoc.getMaMonHoc());
        tvTenMonHoc.setText("Tên môn học: " + monHoc.getTenMonHoc());
        tvChiPhi.setText("Chi phí: " + monHoc.getChiPhi() + " VNĐ");
        return view;
    }
}
