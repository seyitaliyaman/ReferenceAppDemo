package com.demo.reffappdemo.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.reffappdemo.InviterGetCode;
import com.demo.reffappdemo.Model.Firma;
import com.demo.reffappdemo.Model.Kampanya;
import com.demo.reffappdemo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AktifFragment extends Fragment {

    private ListView listView;
    private CampListAdapter adapter;

    private DatabaseReference mKampanya ;
    private DatabaseReference mFirma ;

    private Kampanya kampanya;
    private Firma firma;

    private List<Kampanya> kampanyaList = new ArrayList<>();
    private List<Firma> firmaList = new ArrayList<>();

    public AktifFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mKampanya = FirebaseDatabase.getInstance().getReference("Kampanya");
        mFirma = FirebaseDatabase.getInstance().getReference("Firma");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aktif, container, false);

        listView = view.findViewById(R.id.listViewAktif);

        listView.addHeaderView(new View(getContext()));
        listView.addFooterView(new View(getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), InviterGetCode.class);
                intent.putExtra("kampanyaAd",kampanyaList.get(i-1).getKampanyaAd());
                startActivity(intent);
            }
        });

        adapter = new CampListAdapter(getContext(),R.layout.list_item);

        mKampanya.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    kampanya = data.getValue(Kampanya.class);
                    kampanyaList.add(kampanya);
                }
                Log.e("gelen kampanya ",""+kampanyaList.get(1).getKampanyaAd());
                mFirma.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot data : dataSnapshot.getChildren()){
                            firma = data.getValue(Firma.class);
                            firmaList.add(firma);
                        }
                        Log.e("gelen firma ",""+firmaList.get(2).getFirma());

                        for (int i = 0; i < 3; i++) {
                            KampanyaListItem card = new KampanyaListItem(kampanyaList.get(i).getFotoURL(),kampanyaList.get(i).getKampanyaAd(),firmaList.get(i).getFirma(),firmaList.get(i).getAdres(),kampanyaList.get(i).getKampanyaSüre());
                            adapter.add(card);
                            //list.add(card);
                        }
                        listView.setAdapter(adapter);
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




        return view;
    }


}
