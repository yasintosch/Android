package com.yay.kayip_v2;

public class Kullanicilar {
    int kul_id;
    String email;
    String sifre;
 
    // constructors
    public Kullanicilar() {
    }
 
    public Kullanicilar(String email, String sifre) {
        this.email = email;
        this.sifre = sifre;
    }
 
    public Kullanicilar(int kul_id, String email, String sifre) {
        this.kul_id = kul_id;
        this.email = email;
        this.sifre = sifre;
    }

 
    // setters
    public void setKul_Id(int kul_id) {
        this.kul_id = kul_id;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    // getters
    public int getKul_Id() {
        return this.kul_id;
    }
 
    public String getEmail() {
        return this.email;
    }
 
    public String getSifre() {
        return this.sifre;
    }
}
