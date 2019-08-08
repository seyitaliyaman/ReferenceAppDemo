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
    private String viewUri;
    private String firmaUri;
    private String kampanyaTime;

    private String adres;
    private String sektör;
    private String telefon;


    public HomeListItem(String adres, String sektör, String telefon) {
        this.adres = adres;
        this.sektör = sektör;
        this.telefon = telefon;
    }

    public HomeListItem(Drawable img, String isim, String ilce, String kampanya) {
        this.img = img;
        this.isim = isim;
        this.ilce = ilce;
        this.kampanya = kampanya;
    }

    public HomeListItem(String viewUri, String firmaUri, String isim, String ilce, String kampanya,String kampanyaTime){
        this.viewUri = viewUri;
        this.firmaUri = firmaUri;
        this.isim = isim;
        this.ilce = ilce;
        this.kampanya = kampanya;
        this.kampanyaTime = kampanyaTime;
    }

    public String getFirmaUri() {
        return firmaUri;
    }

    public void setFirmaUri(String firmaUri) {
        this.firmaUri = firmaUri;
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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getSektör() {
        return sektör;
    }

    public void setSektör(String sektör) {
        this.sektör = sektör;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}