package com.example.expertsystem;

import java.util.ArrayList;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ChatGCMBroadcast extends BroadcastReceiver {
private boolean makeNotif = true;
	
	public boolean isMakeNotif() {
		return makeNotif;
	}
	public void setMakeNotif(boolean makeNotif) {
		this.makeNotif = makeNotif;
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		String message = intent.getStringExtra("message");
		DataSingleton.getInstance().loadActiveState(context);
		makeNotif = DataSingleton.getInstance().getActive();
//		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		if(message != null){
			ReceiveMessageResponse messageResponse = new Gson().fromJson(message, ReceiveMessageResponse.class);
			if(messageResponse.getMessage_data() != null){
				ChatMessage chatMessage = messageResponse.toChatMessage();
				insertData(chatMessage, context);
				createNotif(chatMessage, context);
			}
		}
					
	}
	
	private void insertData(ChatMessage chatMessage, Context context){
		if(!makeNotif){
			DataSingleton.getInstance().loadFromFile(context);
		}
		ArrayList<ChatingRoom> listChatingRoom = DataSingleton.getInstance().getListChatingRoom();
		if(chatMessage.getTingkat().equalsIgnoreCase("0")){
			for (ChatingRoom chatingRoom : listChatingRoom) {
				chatingRoom.getListChatMessage().add(chatMessage);
			}
			DataSingleton.getInstance().notifyObserverDataChange();
			DataSingleton.getInstance().saveToFile(context);
		}
		else {
			for (ChatingRoom chatingRoom : listChatingRoom) {
				if(chatingRoom.getTingkat().equalsIgnoreCase(chatMessage.getTingkat())){
					chatingRoom.getListChatMessage().add(chatMessage);
					DataSingleton.getInstance().notifyObserverDataChange();
					DataSingleton.getInstance().saveToFile(context);
					break;
				}
			}
		}
		
		
	}
	
	private void createNotif(ChatMessage chatMessage, Context context){
		if(!makeNotif){
			Intent intent = new Intent(context, MainClientActivity.class);
		    PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		    
			NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification.Builder builder = new Notification.Builder(context)
			.setContentIntent(pIntent);
			builder.setContentTitle("Pesan ditingkat : " + chatMessage.getTingkat());
			builder.setContentText(chatMessage.getUserName() + " : " +chatMessage.getMessage());
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setSound( Uri.parse("android.resource://"
		            + context.getPackageName() + "/" + R.raw.dive_alarm));
			Notification notification = builder.build();
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notificationManager.notify(0, notification);
		}
		
	}
}
