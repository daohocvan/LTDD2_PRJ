package com.example.ktc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ktc.Model.PCB;
import com.example.ktc.R;

import java.util.ArrayList;

public class CustomAdapterPhieuTheoDoi extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PCB> data_PCB;
    public CustomAdapterPhieuTheoDoi(@NonNull Context context, int resource, ArrayList<PCB> data_PCB) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data_PCB = data_PCB;
    }

    @Override
    public int getCount() {
        return data_PCB.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        TextView tvSoPhieu = view.findViewById(R.id.tvSoPhieu);
        TextView tvNgayGiaoBai = view.findViewById(R.id.tvNgayGiaoBai);
        PCB pcb = data_PCB.get(position);
        tvSoPhieu.setText("Số phiếu: " + pcb.getSophieu());
        tvNgayGiaoBai.setText("Ngày giao bài: " + pcb.getNgaygiaobai());
        return view;
    }
}
