package com.example.dizitakip;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TabHost tabs = getTabHost();
        TabHost.TabSpec tab;
        Intent i;
        
        i=new Intent(this,Dizilerim.class);
        tab = tabs.newTabSpec("tab1").setIndicator("DÝZÝLERÝM",getResources().getDrawable(R.drawable.ic_launcher)).setContent(i);
        tabs.addTab(tab);
        
        i=new Intent(this,DiziEkle.class);
        tab = tabs.newTabSpec("tab2").setIndicator("DÝZÝ EKLE",getResources().getDrawable(R.drawable.ic_launcher)).setContent(i);
        tabs.addTab(tab);
        
        i=new Intent(this,Ayarlar.class);
        tab = tabs.newTabSpec("tab3").setIndicator("AYARLAR",getResources().getDrawable(R.drawable.ic_launcher)).setContent(i);
        tabs.addTab(tab);
      
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
