package com.example.ktc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ktc.GiaoDien.ChiTietActivity;
import com.example.ktc.Model.MonHoc;
import com.example.ktc.R;

import java.util.ArrayList;
import java.util.Locale;


public class CustomApdapterMonHoc extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<MonHoc> data;
    ArrayList<MonHoc> data_DS;

    public CustomApdapterMonHoc(Context context, int resource, ArrayList<MonHoc> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<MonHoc>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvTenMonHoc;
        TextView tvChiPhi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgHinh = view.findViewById(R.id.imgHinh);
            holder.imgDetail = view.findViewById(R.id.imgDetail);
            holder.tvTenMonHoc = view.findViewById(R.id.tvTenMonHoc);
            holder.tvChiPhi = view.findViewById(R.id.tvChiPhi);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final MonHoc monHoc = data.get(position);
        if(monHoc.getMaMonHoc().equals("AR1")){
            holder.imgHinh.setImageResource(R.drawable.android2);
        }
        if(monHoc.getMaMonHoc().equals("AR2")){
            holder.imgHinh.setImageResource(R.drawable.android);
        }
        if(monHoc.getMaMonHoc().equals("AR3")){
            holder.imgHinh.setImageResource(R.drawable.sach);
        }
        holder.tvTenMonHoc.setText("Tên môn học: " + monHoc.getTenMonHoc());
//        int chiPhi = Integer.parseInt(monHoc.getChiPhi());
//        DecimalFormat dcfPercent = new DecimalFormat("###,###,###");
//        String strPercent = dcfPercent.format(chiPhi);
        holder.tvChiPhi.setText("Chi phí: " + monHoc.getChiPhi() + " VNĐ");
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, ChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", monHoc.getMaMonHoc());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });


        return view;
    }

    //filter
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data_DS);
        } else {
            for (MonHoc model : data_DS) {
                if (model.getTenMonHoc().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getChiPhi().toLowerCase(Locale.getDefault())
                        .contains(charText) ){
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
