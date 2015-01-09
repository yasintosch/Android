package com.example.dizitakip;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		generateNotification(context,"Diziniz Baþlamak Üzere !");
		
	}
	
	@SuppressWarnings("deprecation")
	private void generateNotification(Context context, String message) {

		  int icon = R.drawable.ic_launcher;
		  long when = System.currentTimeMillis();
		  
		  NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		  Notification notification = new Notification(icon, message, when);
		  
		  String title = context.getString(R.string.app_name);
		  String subTitle = ("Dizinin baþlamasýna çok az kaldý !");
		  Intent notificationIntent = new Intent(context, Bildirim.class);
		  notificationIntent.putExtra("content", message);
		  PendingIntent intent = PendingIntent.getActivity(context, 0,notificationIntent, 0);
		  
		  notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		 
		  notification.setLatestEventInfo(context, title, subTitle, intent);		  

		  notification.defaults |= Notification.DEFAULT_SOUND;
		  notification.flags |= Notification.FLAG_AUTO_CANCEL;
		  notification.defaults |= Notification.DEFAULT_VIBRATE;
		  notificationManager.notify(0, notification);
		  
    }
}