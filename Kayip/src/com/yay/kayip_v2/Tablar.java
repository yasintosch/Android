package com.yay.kayip_v2;



import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Tablar extends TabActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablar);
		
		TabHost sekmeAlani= getTabHost();
		TabHost.TabSpec sekme;
		
		Intent i;
		
		i= new Intent(this, Activity_Tab_Anasayfa.class);
		sekme=sekmeAlani.newTabSpec("Anasayfa_ad").setIndicator("Anasayfa").setContent(i);
		sekmeAlani.addTab(sekme);
		
		i= new Intent(this, Activity_Tab_Profil.class);
		sekme=sekmeAlani.newTabSpec("Profil_ad").setIndicator("Profil").setContent(i);
		sekmeAlani.addTab(sekme);
		
		i= new Intent(this, Activity_Tab_IlanVer.class);
		sekme=sekmeAlani.newTabSpec("Ilanver_ad").setIndicator("Ýlan Ver").setContent(i);
		sekmeAlani.addTab(sekme);
		
		sekmeAlani.setCurrentTab(0);
		
		
	}
	
	

}
