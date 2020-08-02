package com.example.ktc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ktc.Model.SpMaMonHoc;
import com.example.ktc.R;

import java.util.ArrayList;

public class CustomSpMaMonHoc extends BaseAdapter {
    Context context;
    int resource;
    ArrayList<SpMaMonHoc> data;

    public CustomSpMaMonHoc(Context context, int resource, ArrayList<SpMaMonHoc> data) {
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resource, null);
        TextView tvMaMonHoc = convertView.findViewById(R.id.tvMaMonHoc);
        ImageView imgHinh = convertView.findViewById(R.id.imgSach);
        tvMaMonHoc.setText(data.get(position).maMonhoc);
        imgHinh.setImageResource(data.get(position).hinh);
        return convertView;
    }
}
