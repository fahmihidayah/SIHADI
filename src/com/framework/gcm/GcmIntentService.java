package com.framework.gcm;

import com.example.expertsystem.ChatGCMBroadcast;
import com.example.model.Constants;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GcmIntentService extends Service implements Constants{

	// need initializations
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// ini seharusnya dinamis
//		this.gcmIntentServiceBeans = new GcmIntentServiceBeans(this);
//		
//		String messsage = intent.getStringExtra("message");
//		String nama = intent.getStringExtra("NAMA");
//		
//		gcmIntentServiceBeans.setMessageResponse(messsage);
//		gcmIntentServiceBeans.getMessageToServer();
//		intent.setAction(BROADCAST_CHAT);
		Intent newIntent = new Intent(BROADCAST_CHAT);
		String message = intent.getStringExtra("message");
		newIntent.putExtra("message", message);
		sendBroadcast(newIntent);
//		Log.d("data", intent.getStringExtra("message"));
		
		return START_NOT_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
