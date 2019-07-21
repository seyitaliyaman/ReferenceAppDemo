package com.demo.reffappdemo;

import android.graphics.drawable.Drawable;

public class HomeListItem {

    private Drawable img;
    private String isim;
    private String ilce;
    private String kampanya;

    public HomeListItem(Drawable img, String isim, String ilce, String kampanya) {
        this.img = img;
        this.isim = isim;
        this.ilce = ilce;
        this.kampanya = kampanya;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getKampanya() {
        return kampanya;
    }

    public void setKampanya(String kampanya) {
        this.kampanya = kampanya;
    }
}
