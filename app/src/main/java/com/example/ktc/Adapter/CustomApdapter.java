package com.example.ktc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.ktc.GiaoDien.ChiTietGVActivity;
import com.example.ktc.Model.GiaoVien;
import com.example.ktc.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomApdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<GiaoVien> data;
    ArrayList<GiaoVien> data_ds;

    public CustomApdapter(Context context, int resource, ArrayList<GiaoVien> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_ds = new ArrayList<GiaoVien>();
        this.data_ds.addAll(data);

    }


    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgGT;
        ImageView imgDelete;
        TextView tvma;
        TextView tvten;
        TextView tvsdt;
    }
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length()==0){
            data.addAll(data_ds);
        }
        else {
            for (GiaoVien model : data_ds){
                if (model.getMagv().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getTengv().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getSdt().toLowerCase(Locale.getDefault())
                        .contains(charText) ){
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =convertView;
        Holder holder = null;
        if(view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
           /* holder.imgGT = view.findViewById(R.id.imgGT);*/
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            holder.tvma = view.findViewById(R.id.tvma);
            holder.tvten = view.findViewById(R.id.tvten);
            holder.tvsdt = view.findViewById(R.id.tvsdt);
            view.setTag(holder);
        }
        else
            holder=(Holder)view.getTag();

        final GiaoVien GiaoVien = data.get(position);
        holder.tvma.setText(GiaoVien.getMagv());
        holder.tvten.setText(GiaoVien.getTengv());
        holder.tvsdt.setText(GiaoVien.getSdt());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent((Activity) context, ChiTietGVActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", GiaoVien.getMagv());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);


            }
        });


        return view;
    }
}
