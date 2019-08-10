package com.demo.reffappdemo.UserUI;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class KampanyaListItem {

    private Drawable img;
    private String kampanyaAd;
    private String firma;
    private String adres;
    private String tarih;
    private String imgUrl;

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public KampanyaListItem(Drawable img, String kampanyaAd, String firma, String adres, String tarih) {
        this.img = img;
        this.kampanyaAd = kampanyaAd;
        this.firma = firma;
        this.adres = adres;
        this.tarih = tarih;
    }

    public KampanyaListItem(String imgUrl, String kampanyaAd, String firma, String adres, String tarih) {
        this.imgUrl = imgUrl;
        this.kampanyaAd = kampanyaAd;
        this.firma = firma;
        this.adres = adres;
        this.tarih = tarih;
    }


    public String getKampanyaAd() {
        return kampanyaAd;
    }

    public void setKampanyaAd(String kampanyaAd) {
        this.kampanyaAd = kampanyaAd;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
