package com.yay.kayip_v2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Activity_Kayit extends Activity {
	
	Button btnKayit;
	EditText et_email_kayit;
	EditText et_sifre_kayit;
	EditText et_sifretekrar_kayit;
	TextView goster;
	
	DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kayit_layout);
		db = new DatabaseHelper(getApplicationContext());
		
		btnKayit=(Button)findViewById(R.id.btn_kayit);
		et_email_kayit=(EditText)findViewById(R.id.et_email_kayit);
		et_sifre_kayit=(EditText)findViewById(R.id.et_sifre_kayit);
		et_sifretekrar_kayit=(EditText)findViewById(R.id.et_sifretekrar_kayit);
		
     btnKayit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String email=et_email_kayit.getText().toString();
				String sifre=et_sifre_kayit.getText().toString();
				String sifretekrar=et_sifretekrar_kayit.getText().toString();
				
				if(sifre.equals(sifretekrar))
				{
					

					
					db.kullaniciKaydet(email,sifre);
					
					Dialog dialog = new Dialog(Activity_Kayit.this);
					dialog.setTitle("Ekleme Ýþlemi");
					TextView tvsonuc= new TextView(Activity_Kayit.this);
					tvsonuc.setText("Baþarýlý.");
					dialog.setContentView(tvsonuc);
					dialog.show();
					
					
					
					//StringBuilder gelenveri=db.kayitYazdýr();
					//goster.setText(gelenveri);
					
					db.baglantiyiKapa();
					
					startActivity(new Intent("com.yay.kayip_v2.TABLAR"));
					}
				
			else
				{
					Dialog dialog = new Dialog(Activity_Kayit.this);
					dialog.setTitle("Ekleme Ýþlemi");
					TextView tvsonuc= new TextView(Activity_Kayit.this);
					tvsonuc.setText("Email ve sifrenizi tekrar kontrol edin...");
					dialog.setContentView(tvsonuc);
					dialog.show();

				}
				
				
			}
		});
		
	}


}
