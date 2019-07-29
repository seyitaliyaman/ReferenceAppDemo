package com.demo.reffappdemo.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.reffappdemo.R;

public class AktifFragment extends Fragment {

    private ListView listView;
    private CampListAdapter adapter;

    public AktifFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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




            }
        });

        adapter = new CampListAdapter(getContext(),R.layout.list_item);

        for (int i = 0; i < 10; i++) {
            KampanyaListItem card = new KampanyaListItem(getContext().getDrawable(R.drawable.images),"DenemeRestorant","Zeytinburnu","Sağ kola alana sol kola bedava!","Şimdi");
            adapter.add(card);
            //list.add(card);
        }
        listView.setAdapter(adapter);

        return view;
    }


}
