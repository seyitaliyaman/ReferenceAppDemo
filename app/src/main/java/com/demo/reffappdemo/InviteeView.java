package com.demo.reffappdemo;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.reffappdemo.Model.Firma;
import com.demo.reffappdemo.Model.Kampanya;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class InviteeView extends AppCompatActivity {

    private ImageView kampanyaView;
    private TextView kampanyaAd,kampanyaInfo,kampanyaSure;
    private TextView firmaAdres, firmaTelefon, firmaSektör;
    private FloatingActionButton fab;



    private DatabaseReference mRef,mFirma,mKampanya;

    private Kampanya kampanya;
    private Firma firma;

    private ConstraintLayout secondLay;

    private String getKampanyaId;
    private String kampCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitee_view);


        secondLay = findViewById(R.id.secondLay);
        fab = findViewById(R.id.invFab);

        secondLay.setVisibility(View.VISIBLE);
        fab.hide();



        new CountDownTimer(4000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                secondLay.setVisibility(View.INVISIBLE);
                fab.show();
            }
        }.start();

        kampanyaView = findViewById(R.id.invKampanyaView);
        kampanyaAd = findViewById(R.id.invKampanyaAd);
        kampanyaInfo = findViewById(R.id.invKampanyaInfo);
        kampanyaSure = findViewById(R.id.invKampanyaSüre);

        firmaAdres = findViewById(R.id.invFirmaAdres);
        firmaTelefon = findViewById(R.id.invFirmaTelefon);
        firmaSektör = findViewById(R.id.invFirmaSektör);





        Uri uri = this.getIntent().getData();
        try {
            URL url = new URL(uri.getScheme(),uri.getHost(),uri.getPath());
            String [] urlsplit = url.toString().split("/");
            kampCode = urlsplit[3];
            Log.e("gelen link",""+urlsplit[3]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mRef= FirebaseDatabase.getInstance().getReference("Invitee");
        mFirma = FirebaseDatabase.getInstance().getReference("Firma");
        mKampanya = FirebaseDatabase.getInstance().getReference("Kampanya");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getKampanyaId = (String) dataSnapshot.child(kampCode).getValue();
                Log.e("gelen değer",""+getKampanyaId);
                mKampanya.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        kampanya = dataSnapshot.child(getKampanyaId).getValue(Kampanya.class);
                        Log.e("gelen kampanya",""+kampanya.getFirmaID());

                        Picasso.get().load(kampanya.getFotoURL()).into(kampanyaView);

                        kampanyaSure.setText(kampanya.getKampanyaSüre());
                        kampanyaInfo.setText(kampanya.getKampanyaInfo());
                        kampanyaAd.setText(kampanya.getKampanyaAd());
                        mFirma.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                firma = dataSnapshot.child(kampanya.getFirmaID()).getValue(Firma.class);
                                Log.e("gelen kampanya",""+firma.getSektor());
                                firmaSektör.setText(firma.getSektor());
                                firmaTelefon.setText(firma.getTelefon());
                                firmaAdres.setText(firma.getAdres());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),InviteeGetCode.class);
                i.putExtra("kampanyaAd",kampanyaAd.getText().toString());
                startActivity(i);
            }
        });

    }
}