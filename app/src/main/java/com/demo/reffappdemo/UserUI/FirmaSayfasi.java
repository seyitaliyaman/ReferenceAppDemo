package com.demo.reffappdemo.UserUI;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.reffappdemo.KampanyaPaylas;
import com.demo.reffappdemo.R;
import com.squareup.picasso.Picasso;

public class FirmaSayfasi extends AppCompatActivity {

    private ViewPager viewPager;
    LinearLayout sliderDots;
    private int dotsno;
    private ImageView[] dots = new ImageView[2];

    private ImageView kampanyaView;

    private TextView kampanyaAd,kampanyaInfo,kampanyaSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma_sayfasi);
        String isim = getIntent().getStringExtra("isim");
        Log.v("ads",isim);

        kampanyaView = findViewById(R.id.kampanyaView);

        kampanyaAd = findViewById(R.id.kampanyaAd);
        kampanyaInfo = findViewById(R.id.kampanyaInfo);
        kampanyaSure = findViewById(R.id.kampanyaSüre);


        Bundle bundle = this.getIntent().getExtras();

        HomeListItem listItem = (HomeListItem) bundle.get("key");

        kampanyaAd.setText(listItem.getIsim());
        kampanyaInfo.setText(listItem.getKampanya());
        kampanyaSure.setText(listItem.getKampanyaTime());


        Picasso.get().load(listItem.getView()).into(kampanyaView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(isim);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Paylaşma Seçenekleri Yazılacak
                Intent i = new Intent(getBaseContext(), KampanyaPaylas.class);
                startActivity(i);
            }
        });

        /*viewPager = findViewById(R.id.imagepager);
        sliderDots = findViewById(R.id.sliderdots);

        ImagePageAdapter ipa = new ImagePageAdapter(this.getSupportFragmentManager(),2);

        viewPager.setAdapter(ipa);

        dotsno = ipa.getCount();

        for (int i=0;i<dotsno;i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            sliderDots.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int j=0;j<dotsno;j++){
                    dots[j].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.inactive_dot));
                }

                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
