package com.demo.reffappdemo.UserUI;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.reffappdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CampListAdapter extends ArrayAdapter<KampanyaListItem> {



    private List<KampanyaListItem> cardList = new ArrayList<>();

    static class ViewHolder {
        ImageView img;
        TextView camp;
        TextView firm;
        TextView adress;
        TextView date;
    }

    public CampListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(KampanyaListItem object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public KampanyaListItem getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CampListAdapter.ViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.kampanya_list_item, parent, false);
            viewHolder = new CampListAdapter.ViewHolder();
            viewHolder.img = row.findViewById(R.id.campimg);
            viewHolder.camp = row.findViewById(R.id.campname);
            viewHolder.firm = row.findViewById(R.id.firm);
            viewHolder.adress = row.findViewById(R.id.loca);
            viewHolder.date = row.findViewById(R.id.campdate);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CampListAdapter.ViewHolder)row.getTag();
        }
        KampanyaListItem card = getItem(position);

        Log.e("kampanyaar url",""+card.getImgUrl());
        Picasso.get().load(card.getImgUrl()).resize(150,120).into(viewHolder.img);
        viewHolder.img.setImageDrawable(card.getImg());
        viewHolder.camp.setText(card.getKampanyaAd());
        viewHolder.firm.setText(card.getFirma());
        viewHolder.adress.setText(card.getAdres());
        viewHolder.date.setText(card.getTarih());
        return row;
    }
}
