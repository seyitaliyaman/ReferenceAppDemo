package com.demo.reffappdemo.Model;

import java.util.HashMap;
import java.util.List;

public class Firma {

    private String adres;
    private String firma;
    private String firmaFoto;
    private String ilce;
    private HashMap<String,String> kampanyalar;
    private String sektor;
    private String telefon;

    public Firma(String adres, String firma, String firmaFoto, String ilce, HashMap<String,String> kampanyalar,
                 String sektor, String telefon) {
        this.adres = adres;
        this.firma = firma;
        this.firmaFoto = firmaFoto;
        this.ilce = ilce;
        this.kampanyalar = kampanyalar;
        this.sektor = sektor;
        this.telefon = telefon;
    }

    public Firma(){}

    public String getFirmaFoto() {
        return firmaFoto;
    }

    public void setFirmaFoto(String firmaFoto) {
        this.firmaFoto = firmaFoto;
    }

    public HashMap<String,String> getKampanyalar() {
        return kampanyalar;
    }

    public void setKampanyalar(HashMap<String,String> kampanyalar) {
        this.kampanyalar = kampanyalar;
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


