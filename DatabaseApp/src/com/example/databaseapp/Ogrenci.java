package com.example.databaseapp;

public class Ogrenci {

	private int id;
	private String okul_no;
	private String ad;
	private String bolum;
	private String sinif;
	private String image="";
	
	
	
	
	
	public Ogrenci() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ogrenci(String okul_no, String ad, String bolum, String sinif) {
		super();
		this.okul_no = okul_no;
		this.ad = ad;
		this.bolum = bolum;
		this.sinif = sinif;
	}
	public Ogrenci(int id,String okul_no, String ad, String bolum, String sinif) {
		super();
		this.id=id;
		this.okul_no = okul_no;
		this.ad = ad;
		this.bolum = bolum;
		this.sinif = sinif;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOkul_no() {
		return okul_no;
	}
	public void setOkul_no(String okul_no) {
		this.okul_no = okul_no;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getBolum() {
		return bolum;
	}
	public void setBolum(String bolum) {
		this.bolum = bolum;
	}
	public String getSinif() {
		return sinif;
	}
	public void setSinif(String sinif) {
		this.sinif = sinif;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Ogrenci [id=" + id + ", okul_no=" + okul_no + ", ad=" + ad
				+ ", bolum=" + bolum + ", sinif=" + sinif + "]";
	}
	
	
	
}
