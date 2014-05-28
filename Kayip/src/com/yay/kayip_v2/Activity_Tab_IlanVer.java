package com.yay.kayip_v2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
/******************************************/

public class Activity_Tab_IlanVer extends Activity  {
	
	String sd_ilan;
	String sd_kayip;
	Spinner spnr_ilanturu;
	TextView txt_ilanturu;
	Spinner spnr_kayipturu;
	TextView txt_kayipturu;
	EditText etTarih;
	EditText etYer;
	EditText etOzellikler;
	EditText etIletisim;
	Button btn_iptal;
	
	
	private static final int IMAGE_PICK=1;
	private static final int IMAGE_CAPTURE=2;
	
	private ImageView image;
	private Button btn_galeri;
	
	Button btn_kaydet;
	DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_ilanver_layout);

		db = new DatabaseHelper(getApplicationContext());
		
		txt_kayipturu=(TextView)findViewById(R.id.txtkayipturu);
		txt_ilanturu=(TextView)findViewById(R.id.txt_ilanturu);
		spnr_kayipturu= (Spinner)findViewById(R.id.spnr_kayipturu);
		spnr_ilanturu= (Spinner)findViewById(R.id.spnr_ilanturu);
		etTarih=(EditText) findViewById(R.id.etTarih);
		etYer=(EditText) findViewById(R.id.etYer);
		etOzellikler=(EditText) findViewById(R.id.etOzellikler);
		etIletisim=(EditText) findViewById(R.id.etIletisim);
		btn_galeri=(Button)findViewById(R.id.button_image);
		btn_kaydet=(Button) findViewById(R.id.btn_kaydet);
		btn_iptal=(Button) findViewById(R.id.btn_iptal);
		
		
		/***************** Spinnerlar ************************/
		ArrayAdapter adapter_ilan= ArrayAdapter.createFromResource(this, R.array.ilan_turu, android.R.layout.simple_spinner_item);
		adapter_ilan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnr_ilanturu.setAdapter(adapter_ilan);
		
		spnr_ilanturu.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				sd_ilan = spnr_ilanturu.getItemAtPosition(position).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		ArrayAdapter adapter_kayip= ArrayAdapter.createFromResource(this, R.array.kayip_turu, android.R.layout.simple_spinner_item);
		adapter_kayip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnr_kayipturu.setAdapter(adapter_kayip);
		
		
		/************************ Kayýp Türü Spinnerý *****************************/
		spnr_kayipturu.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,	int position, long arg3) {
				// TODO Auto-generated method stub
				sd_kayip = spnr_kayipturu.getItemAtPosition(position).toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/***************** Kaydet butonu ******************************/		
		
		btn_kaydet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String ilan_turu=sd_ilan;
				String kayip_turu= sd_kayip;
				
				String tarih=etTarih.getText().toString();
				String yer=etYer.getText().toString();
				String ozellikler=etOzellikler.getText().toString();
                String iletisim=etIletisim.getText().toString();
				
                try{
                	db.ilanKaydet(ilan_turu, kayip_turu, ozellikler, tarih, yer, iletisim);
                	
                	Dialog dialog = new Dialog(Activity_Tab_IlanVer.this);
                	dialog.setTitle("Ekleme Ýþlemi");
                	TextView tvsonuc= new TextView(Activity_Tab_IlanVer.this);
                	tvsonuc.setText("Baþarýlý.");
                	dialog.setContentView(tvsonuc);
                	dialog.show();
                	db.baglantiyiKapa();
                	startActivity(new Intent("com.yay.kayip_v2.TABLAR"));
                }catch(SQLiteException se){
                	Log.e(getClass().getSimpleName(), "Kayýt Baþarýsýz!");
                	db.baglantiyiKapa();
                }
				
			}
		});
		
		btn_iptal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.yay.kayip_v2.TABLAR"));
				
			}
		});
		
		
/***************** image yükleme butonu ******************************/		
		
		btn_galeri.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				startActivityForResult(Intent.createChooser(intent, "Bir fotoðraf seçin"), IMAGE_PICK);
				
			}
		});
		
		/*btn_cek.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent camintent= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(camintent, IMAGE_CAPTURE);
				
			}
		});  */
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if (resultCode==Activity.RESULT_OK){
			switch(requestCode){
			case IMAGE_PICK:
				this.imageFromGallery(resultCode, data);
				break;
			//case IMAGE_CAPTURE:
				//this.imageFromCamera(resultCode, data);
				//break;
			default:
				break;
			}
		}
	}
	

	
	
	
	private void imageFromGallery(int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Uri selectedImage= data.getData();
		String[] pathColumn= {MediaStore.Images.Media.DATA};
		
		Cursor cursor= getContentResolver().query(selectedImage, pathColumn, null, null, null);
		cursor.moveToFirst();
		
		int columnIndex= cursor.getColumnIndex(pathColumn[0]);
		String filePath= cursor.getString(columnIndex);
		this.image.setImageBitmap(BitmapFactory.decodeFile(filePath));
		cursor.close();
	}
	
	
	

}
