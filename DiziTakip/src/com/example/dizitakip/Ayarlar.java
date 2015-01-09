package com.example.dizitakip;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Ayarlar extends Activity {
    SeekBar skArkaplan;
    TextView txtArkaplanDeger;
    Spinner spSil;
    private DataBase d;
    Button btnUygula,btnSil;
    String s="";
    Cursor cursor; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);
        
        d=new DataBase(this);
        d.open();
        getFromDB();
        
  	  	skArkaplan = (SeekBar)findViewById(R.id.seekBar1);

        final TextView txtArkaplanDeger = (TextView)findViewById(R.id.textArkaplanDeger);
         
        skArkaplan.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {		
  		@Override
  		public void onStopTrackingTouch(SeekBar arg0) {
  			// TODO Auto-generated method stub
  		}
  		
  		@Override
  		public void onStartTrackingTouch(SeekBar arg0) {
  			// TODO Auto-generated method stub			
  		}
  		
  		@Override
  		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
  			// TODO Auto-generated method stub
  			txtArkaplanDeger.setText(String.valueOf(arg1));  			
  		}
  	});
          
        btnSil=(Button)findViewById(R.id.buttonSil);
        btnSil.setOnClickListener(new View.OnClickListener() {
	    	 @Override
	    	 public void onClick(View v) {
	    		 s=cursor.getString(1);
	    		 Toast .makeText(getApplicationContext(), s+" silinecek !", Toast.LENGTH_SHORT).show();
	 		}});	 	     
		
          spSil.setOnItemSelectedListener(new OnItemSelectedListener() {    		
  	        @Override
  	        public void onItemSelected(AdapterView<?> arg0, View arg1, final int pos, long arg3) {  	        	

  	              	if (pos == 0) {
  	              		
  	              // Toast .makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
  	              		
  	                } else if (pos == 1) {
  	                
  	              // Toast .makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
  	                } else if (pos == 2) {
  	                	
  	              // Toast .makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
  	                } 
  	        }       
  	        @Override
  	        public void onNothingSelected(AdapterView<?> arg0) {

  	        }
  	    });
                  
    }
        
        public void getFromDB() {
			d.open();
			cursor = d.getAllRows();
  			String[] fromFieldNames = new String[] {DataBase.KEY_ISIM}; 		 	
  			int[] toViewIDs = new int[]{R.id.item_isim};

  			 SimpleCursorAdapter myCursorAdapter = 
  					new SimpleCursorAdapter(
  							getApplicationContext(),
  							R.layout.item_layout_sil,
  							cursor,
  							fromFieldNames,
  							toViewIDs,
  							0
  							);

       			spSil = (Spinner)findViewById(R.id.spinnerSil);
       			spSil.setAdapter(myCursorAdapter);   		
  		}
        
}