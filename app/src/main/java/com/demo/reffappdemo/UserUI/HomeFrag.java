package com.demo.reffappdemo.UserUI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.demo.reffappdemo.Model.Firma;
import com.demo.reffappdemo.Model.Kampanya;
import com.demo.reffappdemo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFrag extends Fragment {

    private Button search;
    private Spinner sektor,ilce;
    private ListView listView;
    private String[] sektors = new String[9];
    private String[] ilceler = new String[40];
    private ListArrayAdapter adapter;

    private DatabaseReference mDatabase;
    private DatabaseReference mKampanyaRef;
    private DatabaseReference mFirmaInfRef;
    private ChildEventListener mKampanyaListener;

    private FirebaseUser user;

    private List<Kampanya> rows = new ArrayList<>();
    private List<Firma> firmaList = new ArrayList<>();
    private List<HomeListItem> tmp = new ArrayList<>();

    //List<String> foto = new ArrayList<>();
    //List<String> firmilce = new ArrayList<>();

    String foto = "";
    String firmilce = "";


    public HomeFrag() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mKampanyaRef = FirebaseDatabase.getInstance().getReference("Kampanya");
        mFirmaInfRef = FirebaseDatabase.getInstance().getReference("Firma");
        user = FirebaseAuth.getInstance().getCurrentUser();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home_page, container, false);
        // Inflate the layout for this fragment
        search = view.findViewById(R.id.button);
        sektor = view.findViewById(R.id.spinner);
        ilce = view.findViewById(R.id.spinner2);
        listView = view.findViewById(R.id.listView);

        //String uid = getArguments().getString("edt");

        sektors[0] = "Sektör Seçiniz";
        sektors[1] = "Hepsi";
        sektors[2] = "Gıda/Yeme-İçme";
        sektors[3] = "Sağlık ve Güzellik";
        sektors[4] = "Spor";
        sektors[5] = "Kuru Temizleme";
        sektors[6] = "Çiçek";
        sektors[7] = "Oto Bakım";
        sektors[8] = "Diğer";

        ilceler[0] = "İlçe Seçiniz";
        ilceler[1] = "Adalar";
        ilceler[2] = "Arnavutköy";
        ilceler[3] = "Ataşehir";
        ilceler[4] = "Avcılar";
        ilceler[5] = "Bağcılar";
        ilceler[6] = "Bahçelievler";
        ilceler[7] = "Bakırköy";
        ilceler[8] = "Başakşehir";
        ilceler[9] = "Bayrampaşa";
        ilceler[10] = "Beşiktaş";
        ilceler[11] = "Beykoz";
        ilceler[12] = "Beylikdüzü";
        ilceler[13] = "Beyoğlu";
        ilceler[14] = "Büyükçekmece";
        ilceler[15] = "Çatalca";
        ilceler[16] = "Çekmeköy";
        ilceler[17] = "Esenler";
        ilceler[18] = "Esenyurt";
        ilceler[19] = "Eyüpsultan";
        ilceler[20] = "Fatih";
        ilceler[21] = "Gaziosmanpaşa";
        ilceler[22] = "Güngören";
        ilceler[23] = "Kadıköy";
        ilceler[24] = "Kağıthane";
        ilceler[25] = "Kartal";
        ilceler[26] = "Küçükçekmece";
        ilceler[27] = "Maltepe";
        ilceler[28] = "Pendik";
        ilceler[29] = "Sancaktepe";
        ilceler[30] = "Sarıyer";
        ilceler[31] = "Silivri";
        ilceler[32] = "Sultanbeyli";
        ilceler[33] = "Sultangazi";
        ilceler[34] = "Şile";
        ilceler[35] = "Şişli";
        ilceler[36] = "Tuzla";
        ilceler[37] = "Ümraniye";
        ilceler[38] = "Üsküdar";
        ilceler[39] = "Zeytinburnu";

        ArrayAdapter<String> sektadp = new SpinnerAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,sektors);
        sektor.setAdapter(sektadp);

        ArrayAdapter<String> ilceadp = new SpinnerAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,ilceler);
        ilce.setAdapter(ilceadp);



        //rows = mDatabase.child("Kampanya").

        //List<HomeListItem> list = new ArrayList<>();



        mKampanyaRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.e("tag","taki");
                for (DataSnapshot kamp:dataSnapshot.getChildren()){
                    Kampanya kampanya = kamp.getValue(Kampanya.class);
                    rows.add(kampanya);

                    Log.e("tag","taki");
                }

                adapter = new ListArrayAdapter(getContext(),R.layout.list_item);

                for (int i = 0; i < rows.size(); i++) {

                    Log.e("gelen url : ",""+rows.get(i).getFotoURL());
                    Kampanya camp = rows.get(i);
                    final String firma = camp.getFirmaID();
                    Log.e("firmaID",firma);
                    DatabaseReference dr = mDatabase.child("Firma").child(firma);

                    final int j = i;

                    dr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Firma firm = dataSnapshot.getValue(Firma.class);

                            foto = dataSnapshot.child("firmaFoto").getValue().toString();
                            firmilce = dataSnapshot.child("ilce").getValue().toString();
                            //firma = dataSnapshot.child("firma")

                            HomeListItem card = new HomeListItem(rows.get(j).getFotoURL(),foto,rows.get(j).getKampanyaAd(),
                                    firmilce,rows.get(j).getKampanyaInfo(),rows.get(j).getKampanyaSüre());
                            adapter.add(card);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mFirmaInfRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                   for(DataSnapshot firmmaInf : dataSnapshot.getChildren()){
                    Firma firma = firmmaInf.getValue(Firma.class);
                    Log.e("firma id",""+dataSnapshot);
                    firmaList.add(firma);
                }
                for (int i=0; i<firmaList.size(); i++){
                    HomeListItem inf = new HomeListItem(firmaList.get(i).getAdres(),firmaList.get(i).getSektor(),firmaList.get(i).getTelefon());
                    tmp.add(inf);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.e("df",""+rows.size());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String isim = adapter.getItem(i).getIsim();
                HomeListItem item = adapter.getItem(i);
                String uri = item.getFirmaUri();


                Log.e("tıklanan firma info",""+tmp.get(i).getSektör()+" index "+i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key",adapter.getItem(i));
                bundle.putSerializable("firmainf",tmp.get(i));
                Intent intent = new Intent(getContext(),FirmaSayfasi.class);
                intent.putExtra("isim",isim);
                intent.putExtras(bundle);

                startActivity(intent);


            }
        });


        return view;


    }



    class SpinnerAdapter extends ArrayAdapter<String>{

        public SpinnerAdapter(Context context, int resource, String[] arr) {
            super(context, resource,arr);
        }
        @Override
        public boolean isEnabled(int position) {
            if (position==0)
                return false;
            else
                return true;
        }


        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            if(position == 0){
                tv.setTextColor(Color.GRAY);
            }
            else {
                tv.setTextColor(Color.BLACK);
            }
            return view;
        }
    }

}