package com.demo.reffappdemo.Model;

import com.google.firebase.database.FirebaseDatabase;

public class Firma {

    private String adres;
    private String firma;
    private String ilce;
    private String sektor;
    private String telefon;

    public Firma(String adres, String firma, String ilce, String sektor , String telefon) {
        this.adres = adres;
        this.firma = firma;
        this.ilce = ilce;
        this.sektor = sektor;
        this.telefon = telefon;
    }

    public Firma(){

    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}


