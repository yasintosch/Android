package com.example.databaseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OgrenciDuzenle extends Activity{

	Button guncelle;
	Button sil;
	EditText et_OkulNo ;
	EditText et_Adi;
	EditText et_Bolum;
	EditText et_Sinif;
	Database db;
	int id;
	String ad;
	String okulno;
	String bolum;
	String sinif;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ogrenciduzenle);
		
		db=new Database(getApplicationContext());
		
		Bundle paketim  = new  Bundle();
        paketim = getIntent().getExtras();
       
        id=paketim.getInt("id");
        ad = paketim.getString("ad");
        okulno= paketim.getString("okulno");
        bolum = paketim.getString("bolum");
        sinif = paketim.getString("sinif");
        
		
		guncelle=(Button)findViewById(R.id.btnGuncelle);
		sil=(Button)findViewById(R.id.btnSil);
		et_OkulNo =(EditText)findViewById(R.id.et_duzenle_OkulNo);
		et_Adi =(EditText)findViewById(R.id.et_duzenle_Adi);
		et_Bolum =(EditText)findViewById(R.id.et_duzenle_Bolum);
		et_Sinif =(EditText)findViewById(R.id.et_duzenle_Sinif);
		
		
		et_OkulNo.setText(okulno);
		et_Adi.setText(ad);
		et_Bolum.setText(bolum);
		et_Sinif.setText(sinif);
		
		guncelle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			
				
				
			 String okulno=et_OkulNo.getText().toString();
			 String ad=et_Adi.getText().toString();
			 String bolum=et_Bolum.getText().toString();
			 String sinif=et_Sinif.getText().toString();
			 

						Ogrenci ogrenci =new Ogrenci(id,okulno,ad,bolum,sinif);
				 
						db.OgrenciDuzenle(ogrenci);
					
						Toast.makeText(getApplicationContext(), ad+" adlý ogrenci "+"güncellendi", Toast.LENGTH_SHORT).show();												
						Intent i = new Intent(getApplicationContext(), MainActivity.class);
				        startActivity(i);
						
					 }
					

			
		});
		
		sil.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			 String okulno=et_OkulNo.getText().toString();
			 String ad=et_Adi.getText().toString();
			 String bolum=et_Bolum.getText().toString();
			 String sinif=et_Sinif.getText().toString();
			 

						Ogrenci ogrenci =new Ogrenci(id,okulno,ad,bolum,sinif);
				 
						db.OgrenciSil(ogrenci);
					
						Toast.makeText(getApplicationContext(), ad+" adlý ogrenci "+"silindi", Toast.LENGTH_SHORT).show();												
						Intent i = new Intent(getApplicationContext(), MainActivity.class);
				        startActivity(i);
						
					 }
					

			
		});
		

	}
	public void onItemClick(int mPosition) {
		
		//Ogrenci tempValues = ( Ogrenci ) CustomListViewValuesArr.get(mPosition);
	}
}
