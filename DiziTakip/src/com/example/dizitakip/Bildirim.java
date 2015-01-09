package com.example.dizitakip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Bildirim extends Activity{
	TextView txtMesaj;
	String strValue;
	Button btnTamam;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bildirim_layout);

		txtMesaj = (TextView) findViewById(R.id.textBildirim);

		Intent intent = getIntent();
		strValue = intent.getStringExtra("content");
		txtMesaj.setText(strValue);
		
		btnTamam=(Button)findViewById(R.id.buttonTamam);
		btnTamam.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				finish();				
			}
		});

	}
}
