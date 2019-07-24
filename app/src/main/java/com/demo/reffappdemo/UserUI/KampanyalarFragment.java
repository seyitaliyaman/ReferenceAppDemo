package com.demo.reffappdemo.UserUI;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.reffappdemo.R;


public class KampanyalarFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kampanyalar, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabbed);
        tabLayout.addTab(tabLayout.newTab().setText("Aktif"));
        tabLayout.addTab(tabLayout.newTab().setText("Kullanılan"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager pager = view.findViewById(R.id.viewpager);
        final PageAdapter adapter = new PageAdapter(getActivity().getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public void onFragmentInteraction(Uri uri){

    }


}
