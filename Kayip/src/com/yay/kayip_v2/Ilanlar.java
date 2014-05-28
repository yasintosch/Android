package com.yay.kayip_v2;
import android.provider.MediaStore.Images;

public class Ilanlar {

int ilan_id;
String ilan_turu;
String kayip_turu;
String ozellikler;
String tarih;
String yer;
String iletisim;
String image="";

public Ilanlar() {
}

public Ilanlar(String ilan_turu, String kayip_turu,String ozellikler,String tarih,String yer,String iletisim) {
    this.ilan_turu = ilan_turu;
    this.kayip_turu=kayip_turu;
    this.ozellikler=ozellikler;
    this.tarih=tarih;
    this.yer=yer;
    this.iletisim=iletisim;
}

public Ilanlar(int ilan_id,String ilan_turu, String kayip_turu,String ozellikler,String tarih,String yer,String iletisim) {
    this.ilan_id = ilan_id;
    this.ilan_turu = ilan_turu;
    this.kayip_turu=kayip_turu;
    this.ozellikler=ozellikler;
    this.tarih=tarih;
    this.yer=yer;
    this.iletisim=iletisim;
}

public void setImage(String image)
{
	this.image=image;

}

public String getImage(){return this.image;}

// setters
public void setKayip_turu(String kayip_turu){
	this.kayip_turu= kayip_turu;
}

public void setIlan_Id(int ilan_id) {
    this.ilan_id = ilan_id;
}

public void setIlan_turu(String ilan_turu) {
    this.ilan_turu=ilan_turu;    
}

public void setOzellikler(String ozellikler) {
    this.ozellikler = ozellikler;
}

public void setTarih(String tarih) {
    this.tarih = tarih;
}

public void setYer(String yer) {
    this.yer = yer;
}

public void setIletisim(String iletisim) {
    this.iletisim = iletisim;
}




// getters
public int getIlan_Id() {
    return this.ilan_id;
}

public String getKayip_turu() {
    return this.kayip_turu;
}

public String getIlan_turu() {
    return this.ilan_turu;
}

public String getOzellikler() {
    return this.ozellikler;
}

public String getTarih() {
    return this.tarih;
}
public String getYer() {
    return this.yer;
}
public String getIletisim(){
    return this.iletisim;
}


	
	
}
