package com.yay.kayip_v2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Giris extends Activity{

	TextView tv_kaydol;
	EditText et_email_giris;
	EditText et_sifre_giris;
	Button btn_giris;
	
	DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_giris_layout);
		db = new DatabaseHelper(getApplicationContext());
		
		
		tv_kaydol=(TextView)findViewById(R.id.tv_Kaydol);
		btn_giris=(Button) findViewById(R.id.btn_giris);
		et_email_giris=(EditText)findViewById(R.id.et_email_giris);
		et_sifre_giris=(EditText)findViewById(R.id.et_sifre_giris);
		
		tv_kaydol.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent("com.yay.kayip_v2.ACTIVITYKAYIT"));
			}
		});
		
		
		btn_giris.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String email=et_email_giris.getText().toString();
				String sifre=et_sifre_giris.getText().toString();
				boolean checker=false;
				
				
				Cursor c= db.kayitGetir();
				
				if(c!=null){
					if(c.moveToFirst()){
						do{
							String e= c.getString(c.getColumnIndex("email"));
							if(e.equals(email)){
								checker=true;
								String s= c.getString(c.getColumnIndex("sifre"));
								if(s.equals(sifre))
								{									
									db.baglantiyiKapa();
									startActivity(new Intent("com.yay.kayip_v2.TABLAR"));
								}
								else
								{
									Dialog dialog = new Dialog(Activity_Giris.this);
									dialog.setTitle("Giriþ Ýþlemi");
									TextView tvsonuc= new TextView(Activity_Giris.this);
									tvsonuc.setText("Email ve sifrenizi tekrar kontrol edin...");
									dialog.setContentView(tvsonuc);
									dialog.show();
								}
							}
						}while(c.moveToNext());	
					}
					if(!checker){
						Dialog dialog = new Dialog(Activity_Giris.this);
						dialog.setTitle("Giriþ Ýþlemi");
						TextView tvsonuc= new TextView(Activity_Giris.this);
						tvsonuc.setText("Email adresi veritabanýna kayýtlý deðil.");
						dialog.setContentView(tvsonuc);
						dialog.show();
					}
				}	
			}
		});
	
	}

	
}
