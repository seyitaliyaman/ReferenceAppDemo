package com.demo.reffappdemo.UserUI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.demo.reffappdemo.R;

public class HomeFrag extends Fragment {

    private Button search;
    private Spinner sektor,ilce;
    private ListView listView;
    private String[] sektors = new String[9];
    private String[] ilceler = new String[40];
    private ListArrayAdapter adapter;


    public HomeFrag() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //List<HomeListItem> list = new ArrayList<>();

        listView.addHeaderView(new View(getContext()));
        listView.addFooterView(new View(getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String isim = adapter.getItem(i).getIsim();
                Intent intent = new Intent(getContext(),FirmaSayfasi.class);
                intent.putExtra("isim",isim);
                startActivity(intent);


            }
        });

        adapter = new ListArrayAdapter(getContext(),R.layout.list_item);

        for (int i = 0; i < 10; i++) {
            HomeListItem card = new HomeListItem(getContext().getDrawable(R.drawable.images),"DenemeRestorant","Zeytinburnu","Sağ kola alana sol kola bedava!");
            adapter.add(card);
            //list.add(card);
        }
        listView.setAdapter(adapter);



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
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
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