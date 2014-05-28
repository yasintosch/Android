package com.yay.kayip_v2;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Activity_Tab_Profil extends Activity{

	Button btn_ozellestir;
	TextView txt_email;
	ListView list;
    CustomAdapter adapter;
    public  Activity_Tab_Profil activity_tab_profil = null;
    public  ArrayList<Ilanlar> CustomListViewValuesArr = new ArrayList<Ilanlar>();
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_profil_layout);
		
		txt_email=(TextView)findViewById(R.id.textEmail);
		txt_email.setText("ayln@gmail.com");
		
		activity_tab_profil=this;
		setListData();
		
        Resources res =getResources();
        list = ( ListView )findViewById( R.id.list_ilanlar );
        
        adapter=new CustomAdapter(activity_tab_profil, CustomListViewValuesArr, res);
        list.setAdapter( adapter );
		
		
		btn_ozellestir= (Button)findViewById(R.id.button_sifredegistir);
		
		btn_ozellestir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.yay.kayip_v2.PROFILOZELLESTIR"));
				
			}
		});
		
	}
	
	public void setListData()
    {
         
        for (int i = 0; i < 11; i++) {
             
            final Ilanlar ilan = new Ilanlar();
                 
              /******* Firstly take data in model object ******/
               ilan.setIlan_turu("ilan_turu"+i);
               ilan.setKayip_turu("kayip_turu"+i);
               ilan.setOzellikler("ozellikler"+i);
               ilan.setTarih("tarih"+i);
               ilan.setYer("yer"+i);
               ilan.setIletisim("iletisim"+i);
                
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( ilan );
        }
       
        /*****************  This function used by adapter ****************/
    
  
        
    }

	public void onItemClick(int mPosition) {
		
		Ilanlar tempValues = ( Ilanlar ) CustomListViewValuesArr.get(mPosition);
	}
	
	

}