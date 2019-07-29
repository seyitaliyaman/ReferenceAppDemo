package com.demo.reffappdemo.Model;

public class Kampanya {

    private String fotoURL;
    private String kampanyaAd;
    private String kampanyaId;
    private String kampanyaInfo;
    private String kampanyaSüre;

    public Kampanya(String fotoURL, String kampanyaAd, String kampanyaId, String kampanyaInfo, String kampanyaSüre) {
        this.fotoURL = fotoURL;
        this.kampanyaAd = kampanyaAd;
        this.kampanyaId = kampanyaId;
        this.kampanyaInfo = kampanyaInfo;
        this.kampanyaSüre = kampanyaSüre;
    }

    public Kampanya(){}

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    public String getKampanyaAd() {
        return kampanyaAd;
    }

    public void setKampanyaAd(String kampanyaAd) {
        this.kampanyaAd = kampanyaAd;
    }

    public String getKampanyaId() {
        return kampanyaId;
    }

    public void setKampanyaId(String kampanyaId) {
        this.kampanyaId = kampanyaId;
    }

    public String getKampanyaInfo() {
        return kampanyaInfo;
    }

    public void setKampanyaInfo(String kampanyaInfo) {
        this.kampanyaInfo = kampanyaInfo;
    }

    public String getKampanyaSüre() {
        return kampanyaSüre;
    }

    public void setKampanyaSüre(String kampanyaSüre) {
        this.kampanyaSüre = kampanyaSüre;
    }
}
