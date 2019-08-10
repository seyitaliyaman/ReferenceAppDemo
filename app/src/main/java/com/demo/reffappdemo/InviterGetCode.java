package com.demo.reffappdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class InviterGetCode extends AppCompatActivity {

    private TextView aktifKampİnfo,aktifKod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviter_get_code);


        aktifKampİnfo = findViewById(R.id.aktifKampİnfo);
        aktifKod = findViewById(R.id.aktifKod);



        aktifKampİnfo.setText("\""+this.getIntent().getStringExtra("kampanyaAd")+"\" isimli kampanyayı kullanmak üzeresiniz.Kampanyayı onaylatmak için aşağıdaki kodu işletmede onaylatınız.");

        aktifKod.setText(getRandomNumberString());


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        if (getWindowManager().getDefaultDisplay().getWidth()<getWindowManager().getDefaultDisplay().getHeight()){
            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int) (width*.85),(int) (height*.5));
            getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corner_button));
        }else{
            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int) (width*.5),(int) (height*.85));
            getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corner_button));
        }
    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
