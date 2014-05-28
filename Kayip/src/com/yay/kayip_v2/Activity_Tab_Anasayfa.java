package com.yay.kayip_v2;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.res.Resources;

public class Activity_Tab_Anasayfa extends Activity{

	
    ListView list;
    CustomAdapter adapter;
    public  Activity_Tab_Anasayfa activity_tab_anasayfa = null;
    public  ArrayList<Ilanlar> CustomListViewValuesArr = new ArrayList<Ilanlar>();
   
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_anasayfa_layout);
		
		activity_tab_anasayfa=this;
		setListData();
		
        Resources res =getResources();
        list = ( ListView )findViewById( R.id.list_ilanlar );
        
        adapter=new CustomAdapter(activity_tab_anasayfa, CustomListViewValuesArr, res);
        list.setAdapter( adapter );

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
