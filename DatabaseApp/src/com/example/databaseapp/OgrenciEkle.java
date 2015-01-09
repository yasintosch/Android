package com.example.databaseapp;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OgrenciEkle extends Activity {

	Button kaydet;
	EditText et_OkulNo ;
	EditText et_Adi;
	EditText et_Bolum;
	EditText et_Sinif;
	Database db;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ogrenciekle);
		
		db=new Database(getApplicationContext());
		
		kaydet=(Button)findViewById(R.id.btnKaydet);
		et_OkulNo =(EditText)findViewById(R.id.et_OkulNo);
		et_Adi =(EditText)findViewById(R.id.et_Adi);
		et_Bolum =(EditText)findViewById(R.id.et_Bolum);
		et_Sinif =(EditText)findViewById(R.id.et_Sinif);
		
		 
		 kaydet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			 String okulno=et_OkulNo.getText().toString();
			 String ad=et_Adi.getText().toString();
			 String bolum=et_Bolum.getText().toString();
			 String sinif=et_Sinif.getText().toString();
			 

						Ogrenci ogrenci =new Ogrenci(okulno,ad,bolum,sinif);
				 
						db.OgrenciEkle(ogrenci);
					
						Toast.makeText(getApplicationContext(), ad+" adlý ogrenci "+"eklendi", Toast.LENGTH_SHORT).show();												
						Intent i = new Intent(getApplicationContext(), MainActivity.class);
				        startActivity(i);
						
					 }
					

			
		});
		
		 
	}
	
}
