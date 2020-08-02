package com.example.ktc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ktc.Model.PCB;
import com.example.ktc.Model.TTChamBai;
import com.example.ktc.R;

import java.util.ArrayList;

public class CustomAdapterThongTinChamBai1 extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<TTChamBai> data_TTChamBai;
    public CustomAdapterThongTinChamBai1(@NonNull Context context, int resource, @NonNull ArrayList<TTChamBai> data_TTChamBai) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data_TTChamBai = data_TTChamBai;
    }

    @Override
    public int getCount() {
        return data_TTChamBai.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        TextView tvSoPhieu = view.findViewById(R.id.tvsophieu);
        TextView tvSoBai = view.findViewById(R.id.tvsobai);
        TextView tvMaMonHoc = view.findViewById(R.id.tvmamonhoc);
        TTChamBai ttChamBai = data_TTChamBai.get(position);
        tvSoPhieu.setText("Số phiếu: " + ttChamBai.getSoPhieu());
        tvSoBai.setText("Số bài: " + ttChamBai.getSoBai());
        tvMaMonHoc.setText("Mã môn học: " + ttChamBai.getMaMonHoc());
        return view;
    }
}
