package com.yay.kayip_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profil_Ozellestir extends Activity {
	
	EditText edt_eskisifre;
	EditText edt_yenisifre;
	EditText edt_yenis_tekrar;
	Button btn_kaydet;
	Button btn_iptal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profil_ozellestir);
		
		btn_iptal=(Button)findViewById(R.id.button_iptal);
		
		btn_iptal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.yay.kayip_v2.TABLAR"));
			}
		});
	}

}
