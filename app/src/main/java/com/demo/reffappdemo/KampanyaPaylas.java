package com.demo.reffappdemo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.reffappdemo.Model.Kampanya;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class KampanyaPaylas extends AppCompatActivity {

    private ImageView facebookButton,whatsappButton,mailButton,smsButton,copyButton;

    private TextView kampanyaLink;

    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

    private String kampId;
    private String kampNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kampanya_paylas);

        facebookButton = findViewById(R.id.facebookButton);
        whatsappButton = findViewById(R.id.whatsappButton);
        mailButton = findViewById(R.id.mailButton);
        smsButton = findViewById(R.id.smsButton);
        copyButton = findViewById(R.id.copyButton);

        kampanyaLink = findViewById(R.id.kampanyaLink);
        kampNo = getRandomNumberString();

        kampanyaLink.setText("www.akillidavet.com/"+kampNo);



        kampId = getIntent().getStringExtra("kampKey");

        Log.e("kampanya id paylaş",""+kampId);

        mRef.child("Invitee").child(kampNo).setValue(kampId).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.e("Kayıt işlemi ","başarılı");
                }else{
                    Log.e("Kayıt işlemi ","başarısız");
                }
            }
        });


        /*smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", "12125551212");
                smsIntent.putExtra("sms_body","Body of Message");
                startActivity(smsIntent);
            }
        });*/

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                if(!kampanyaLink.getText().toString().equals("")){
                    clipboardManager.setText(kampanyaLink.getText().toString());
                    Toast.makeText(getBaseContext(),"Link panoya kopyalandı",Toast.LENGTH_LONG).show();
                }

            }
        });


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


