package com.example.dizitakip;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class DiziEkle extends Activity {
	
private DataBase d;
TextView txtSaat,txtDk,txtGun,txtAy,txtYil;
EditText edtIsim;
int hour,minute,year,month,day;
Button btnEkle,btnTarih,btnSaat;
Switch swBildirim;
int bildirimControl=0;

int i=0;
Intent intentsOpen;
static PendingIntent pendingIntent;
static AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab3);

		 d = new DataBase(this);

		 edtIsim=(EditText)findViewById(R.id.editIsim);
		
		 txtSaat = (TextView) findViewById(R.id.textSaat);
		 txtDk = (TextView) findViewById(R.id.textDk);
		 txtGun = (TextView) findViewById(R.id.textGun);
		 txtAy = (TextView) findViewById(R.id.textAy);
		 txtYil = (TextView) findViewById(R.id.textYil);
		
		 swBildirim = (Switch)findViewById(R.id.switchBildirim);		 
		 swBildirim.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View arg0) {
					if(swBildirim.isChecked())
					{
						bildirimControl=1;
					}
					else
					{
						bildirimControl=0;
					}
				}
			});
			
		 final Calendar c = Calendar.getInstance();
		 hour = c.get(Calendar.HOUR_OF_DAY);
		 minute = c.get(Calendar.MINUTE);
		 year = c.get(Calendar.YEAR);
		 month = c.get(Calendar.MONTH);
		 day = c.get(Calendar.DAY_OF_MONTH);
		
		btnEkle=(Button)findViewById(R.id.buttonEkle);
		btnTarih=(Button)findViewById(R.id.buttonTarih);
		btnSaat=(Button)findViewById(R.id.buttonSaat);
		
		intentsOpen = new Intent(this, AlarmReceiver.class);		
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		 
    }    
    
public void onClickControl(View v) {
		
		if(v==btnEkle){
			
			String isimDB = edtIsim.getText().toString();
			String tarihDB = txtGun.getText().toString()+"."+txtAy.getText().toString()+"."+txtYil.getText().toString();
			String saatDB = txtSaat.getText().toString()+":"+txtDk.getText().toString();
			String bildirimDB="";
			if(bildirimControl==1)
			{
				bildirimDB="Bildirim Açýk";
			}
			else
			{
				bildirimDB="Bildirim Kapalý";
			}
			
			try{
				 kayitEkle(isimDB,
						tarihDB,
						saatDB,
						bildirimDB);
				 
				   if (bildirimControl==1){
					 
					 int hour=Integer.parseInt(txtSaat.getText().toString());
					 int min=Integer.parseInt(txtDk.getText().toString());
					 int year=Integer.parseInt(txtYil.getText().toString());
					 int month=(Integer.parseInt(txtAy.getText().toString()))-1;
					 int day=Integer.parseInt(txtGun.getText().toString());
					 i++;
					 setAlarm(hour,min,year,month,day);
				 }					 
				
			}
			finally
			{ 
				 d.close();
			}				

		}
		
		if(v==btnTarih){
			
			showDialog(500);

		}
		if(v==btnSaat){
			
			showDialog(1000);

		}
	}	
    
	@Override
	protected Dialog onCreateDialog(int id) {
	switch (id) {
	case 1000:
		return new TimePickerDialog(this, timePickerListener, hour, minute,true);

	case 500:
		return new DatePickerDialog(this, datePickerListener, year, month,day);

	}
	return null;
	}
    
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int yearSelected,int monthOfYear, int dayOfMonth) {
                   year = yearSelected;
                   month = monthOfYear;
                   day = dayOfMonth;
                   
                   if (day>=10)
                	   txtGun.setText(String.valueOf(day));
                   else
                	   txtGun.setText("0"+String.valueOf(day));
                   
                   if (month>=9)                   
                	   txtAy.setText(String.valueOf(month+1));
                   else
                       txtAy.setText("0"+String.valueOf(month+1));

                   txtYil.setText(String.valueOf(year));                   
                }
            };	
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {			
				public void onTimeSet(TimePicker view, int selectedHour,int selectedMinute) {			
					hour = selectedHour;
					minute = selectedMinute;

					if (hour>=10)
						txtSaat.setText(String.valueOf(hour));
					else
						txtSaat.setText("0"+String.valueOf(hour));
					
					if (minute>=10)
						txtDk.setText(String.valueOf(minute));
					else
						txtDk.setText("0"+String.valueOf(minute));
		}
	};
	
	public void setAlarm(int h, int m, int y , int mo, int d) {

		pendingIntent = PendingIntent.getBroadcast(this,i, intentsOpen, 0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
		calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, mo);
		calendar.set(Calendar.DAY_OF_MONTH, d);

		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_DAY * 7, pendingIntent);		
		
	}
	
	private void kayitEkle(String isim, String tarih, String saat, String bildirim)
	{	
		d = new DataBase(this);
		d.open();
		d.insertRow(isim,tarih,saat,bildirim);
		Toast.makeText(getApplicationContext(), "Kaydedildi", Toast.LENGTH_SHORT).show();
	}
	
}