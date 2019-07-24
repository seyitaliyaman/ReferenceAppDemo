package com.demo.reffappdemo.UserUI;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ImagePageAdapter extends FragmentStatePagerAdapter {

    private int numOfPages;

    public ImagePageAdapter(FragmentManager fm, int numOfPages) {
        super(fm);
        this.numOfPages = numOfPages;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new KullanilanFragment();

            case 1:
                return new AktifFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfPages;
    }
}
