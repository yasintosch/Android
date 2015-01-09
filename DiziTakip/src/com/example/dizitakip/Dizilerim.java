package com.example.dizitakip;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Dizilerim extends Activity implements SensorEventListener {
private DataBase d;
Button btnGuncel;
ImageView img;
int j=0;
SensorManager sensorManager;
long lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        
        d=new DataBase(this);
        d.open();
        img=(ImageView)findViewById(R.id.imageView1);
        img.setImageResource(R.drawable.dizilerbg);
        img.setAlpha(100);
        
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
               
        btnGuncel=(Button)findViewById(R.id.button_goster);	
        btnGuncel.setOnClickListener(new View.OnClickListener() {
	    	 @Override
	    	 public void onClick(View v) {
	 			yenile();
	 		}	 	     
		
		private void yenile() {
			d.open();
			Cursor cursor = d.getAllRows();
		 
   			String[] fromFieldNames = new String[] 
   					{DataBase.KEY_ISIM, DataBase.KEY_TARIH, DataBase.KEY_SAAT, DataBase.KEY_BILDIRIM};
   			
   			int[] toViewIDs = new int[]
   					{R.id.item_isim, R.id.item_tarih, R.id.item_saat, R.id.item_bildirim};

   			SimpleCursorAdapter myCursorAdapter = 
   					new SimpleCursorAdapter(
   							getApplicationContext(),
   							R.layout.item_layout,
   							cursor,
   							fromFieldNames,
   							toViewIDs,
   							0
   							);

   			ListView myList = (ListView)findViewById(R.id.listView1);
   			myList.setAdapter(myCursorAdapter);
   		}
		});
        
    }
   
    @Override
    public void onSensorChanged(SensorEvent event) {
      if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        getAccelerometer(event);
      }

    }

    @SuppressWarnings("deprecation")
	private void getAccelerometer(SensorEvent event) {
    	
      float[] values = event.values;      
      float x = values[0];
      float y = values[1];
      float z = values[2];
      
      float accelationSquareRoot = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

      long actualTime = System.currentTimeMillis();
      
      if (accelationSquareRoot >= 5)
      {
        if (actualTime - lastUpdate < 1000) { return;  }
        
        lastUpdate = actualTime;
        
        if (j==0) {
            img.setImageResource(R.drawable.walking);
            img.setAlpha(100);
            j++;
        }
        else if (j==1) {
            img.setImageResource(R.drawable.supernatural);
            img.setAlpha(100);
            j++;
        }
        else if (j==2) {
            img.setImageResource(R.drawable.arrow);
            img.setAlpha(100);
            j++;
        }
        else if (j==3) {
            img.setImageResource(R.drawable.doctorwho);
            img.setAlpha(100);
            j++;
        }
        else if (j==4) {
            img.setImageResource(R.drawable.the100);
            img.setAlpha(100);
            j++;
        }
        else if (j==5) {
            img.setImageResource(R.drawable.grimm);
            img.setAlpha(100);
            j++;
        }
        else if (j==6) {
            img.setImageResource(R.drawable.person);	
            img.setAlpha(100);
            j++;
        }
        else if (j==7) {
            img.setImageResource(R.drawable.cons);	
            img.setAlpha(100);
            j=0;
        }
      }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onResume() {
      super.onResume();
      sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
      super.onPause();
      sensorManager.unregisterListener(this);
    } 

}