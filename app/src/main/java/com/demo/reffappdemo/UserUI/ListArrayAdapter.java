package com.demo.reffappdemo.UserUI;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.reffappdemo.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListArrayAdapter  extends ArrayAdapter<HomeListItem>  {

    private List<HomeListItem> cardList = new ArrayList<HomeListItem>();

    static class ViewHolder {
        ImageView img;
        ImageView firmaimg;
        TextView text1;
        TextView text2;
        TextView text3;
        TextView likeCount;
    }

    public ListArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(HomeListItem object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public HomeListItem getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.firmaimg = row.findViewById(R.id.imageView6);
            viewHolder.img = row.findViewById(R.id.imageView);
            viewHolder.text1 = row.findViewById(R.id.textView);
            viewHolder.text2 = row.findViewById(R.id.textView2);
            viewHolder.text3 = row.findViewById(R.id.textView3);
            viewHolder.likeCount = row.findViewById(R.id.likeNum);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)row.getTag();
        }
        HomeListItem card = getItem(position);
        //Log.e("deneme4 ",""+Uri.parse(card.getView()));
        Picasso.get().load(Uri.parse(card.getFirmaUri())).resize(300,250).into(viewHolder.firmaimg);
        Picasso.get().load(Uri.parse(card.getView())).resize(600,500).into(viewHolder.img);
        //viewHolder.img.setImageURI(Uri.parse(card.getView()));
        //viewHolder.img.setImageDrawable(card.getImg());
        viewHolder.text1.setText(card.getIsim());
        viewHolder.text2.setText(card.getIlce());
        viewHolder.text3.setText(card.getKampanya());
        viewHolder.likeCount.setText(card.getLikeCount());

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
