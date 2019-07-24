package com.demo.reffappdemo.UserUI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new AktifFragment();
            case 1:
                return new KullanilanFragment();
            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
