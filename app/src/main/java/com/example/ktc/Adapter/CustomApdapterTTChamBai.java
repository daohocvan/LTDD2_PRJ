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

import com.example.ktc.GiaoDien.ChiTietThongTinChamBai;
import com.example.ktc.Model.TTChamBai;
import com.example.ktc.R;

import java.util.ArrayList;
import java.util.Locale;


public class CustomApdapterTTChamBai extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<TTChamBai> data;
    ArrayList<TTChamBai> data_DS;

    public CustomApdapterTTChamBai(Context context, int resource, ArrayList<TTChamBai> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<TTChamBai>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvSoPhieu;
        TextView tvSoBai;
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
            holder.tvSoPhieu = view.findViewById(R.id.tvSoPhieu);
            holder.tvSoBai = view.findViewById(R.id.tvSoBai);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final TTChamBai ttChamBai = data.get(position);
        if(ttChamBai.getMaMonHoc().equals("AR1")){
            holder.imgHinh.setImageResource(R.drawable.android2);
        }
        if(ttChamBai.getMaMonHoc().equals("AR2")){
            holder.imgHinh.setImageResource(R.drawable.android);
        }
        if(ttChamBai.getMaMonHoc().equals("AR3")){
            holder.imgHinh.setImageResource(R.drawable.sach);
        }
        holder.tvSoPhieu.setText("Số phiếu: " + ttChamBai.getSoPhieu());
//        int chiPhi = Integer.parseInt(monHoc.getChiPhi());
//        DecimalFormat dcfPercent = new DecimalFormat("###,###,###");
//        String strPercent = dcfPercent.format(chiPhi);
        holder.tvSoBai.setText("Số bài: " + ttChamBai.getSoBai());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((Activity) context, ChiTietThongTinChamBai.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", ttChamBai.getMaMonHoc());
                intent.putExtras(bundle);
                bundle.putString("sophieu", ttChamBai.getSoPhieu());
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
            for (TTChamBai model : data_DS) {
                if (model.getMaMonHoc().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getSoPhieu().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getSoBai().toLowerCase(Locale.getDefault())
                        .contains(charText) ){
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
