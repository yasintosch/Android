package com.example.databaseapp;




import java.util.ArrayList;
import java.util.List;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	Button ogrencieklesayfasi;
    ListView list;
    CustomAdapter adapter;
    public  MainActivity mainactivity = null;
    public  ArrayList<Ogrenci> CustomListViewValuesArr = new ArrayList<Ogrenci>();
    Database db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db=new Database(getApplicationContext());
        
        CustomListViewValuesArr=db.TumOgrencileriGetir();
        
        
        ogrencieklesayfasi=(Button)findViewById(R.id.btnOgrenciEkle);
        mainactivity=this;
        Resources res =getResources();
        //setListData();
        list = ( ListView )findViewById( R.id.list_Ogrenciler );
       adapter=new CustomAdapter(mainactivity, CustomListViewValuesArr, res);

        list.setAdapter( adapter );
        
   
        ogrencieklesayfasi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(getApplicationContext(), OgrenciEkle.class);
		        startActivity(i);
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	public void onItemClick(int mPosition) {
		
		Ogrenci tempValues = CustomListViewValuesArr.get(mPosition);
		
		
        Intent intentim = new Intent(MainActivity.this,OgrenciDuzenle.class);
        intentim.putExtra("id", tempValues.getId());
        intentim.putExtra("ad", tempValues.getAd());
        intentim.putExtra("okulno", tempValues.getOkul_no());
        intentim.putExtra("bolum", tempValues.getBolum());
        intentim.putExtra("sinif", tempValues.getSinif());
        
        
		//Intent i = new Intent(getApplicationContext(), OgrenciDuzenle.class);
        //startActivity(i);
        startActivity(intentim);
	}
    
}
