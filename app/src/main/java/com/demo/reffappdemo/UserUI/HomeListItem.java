package com.demo.reffappdemo.UserUI;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

import java.io.Serializable;

public class HomeListItem implements Serializable {

    private Drawable img;
    private String isim;
    private String ilce;
    private String kampanya;
    private String  viewUri;
    private String kampanyaTime;



    public HomeListItem(Drawable img, String isim, String ilce, String kampanya) {
        this.img = img;
        this.isim = isim;
        this.ilce = ilce;
        this.kampanya = kampanya;
    }

    public HomeListItem(String viewUri,String isim, String ilce, String kampanya,String kampanyaTime){
        this.viewUri = viewUri;
        this.isim = isim;
        this.ilce = ilce;
        this.kampanya = kampanya;
        this.kampanyaTime = kampanyaTime;
    }

    public String getView() {
        return viewUri;
    }

    public void setView(String view) {
        this.viewUri = viewUri;
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

    public String getViewUri() {
        return viewUri;
    }

    public void setViewUri(String viewUri) {
        this.viewUri = viewUri;
    }

    public String getKampanyaTime() {
        return kampanyaTime;
    }

    public void setKampanyaTime(String kampanyaSüre) {
        this.kampanyaTime = kampanyaSüre;
    }
}
