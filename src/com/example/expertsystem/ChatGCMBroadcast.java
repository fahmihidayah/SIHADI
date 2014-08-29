package com.example.expertsystem;

import java.util.ArrayList;

import com.example.beans.DataSingleton;
import com.example.model.ChatMessage;
import com.example.model.User;
import com.example.model.UserChat;
import com.example.response.ChatMessageResponse;
import com.google.gson.Gson;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

@SuppressLint("NewApi")
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
		DataSingleton.getInstance().loadStatusActive(context);
		makeNotif = DataSingleton.getInstance().getActive();
		if(!makeNotif){
			DataSingleton.getInstance().loadFromFile(context);
		}
		Toast.makeText(context, "status " + makeNotif, Toast.LENGTH_LONG).show();
		if(message != null){
			ChatMessageResponse messageResponse = new Gson().fromJson(message, ChatMessageResponse.class);
			if(messageResponse.getMessage_data() != null){
				ChatMessage chatMessage = messageResponse.getChatMessage();
				boolean isGrup = (messageResponse.getGrup().equals("1") ? true : false);
				insertData(chatMessage, context, isGrup);
				createNotif(chatMessage, context);
			}
		}
					
	}
	
	private void insertData(ChatMessage chatMessage, Context context, boolean isGrup){
		if(isGrup){
			DataSingleton.getInstance().getListChatMessage().add(chatMessage);
		}
		else {
			UserChat userChat = new UserChat();
			userChat.setUser(chatMessage.getUser());
			int index = DataSingleton.getInstance().getListChatUser().indexOf(userChat);
			if(index != -1){
				DataSingleton.getInstance().getListChatUser().get(index).getListChatMessage().add(chatMessage);
				DataSingleton.getInstance().getListChatUser().get(index).addUnreadMessage();
			}
			else {
				userChat.addUnreadMessage();
				DataSingleton.getInstance().getListChatUser().add(userChat);
			}
		}
		DataSingleton.getInstance().saveToFile(context);
		DataSingleton.getInstance().notifyObserverDataChange(null);
	}
	
	private void createNotif(ChatMessage chatMessage, Context context){
		if(!DataSingleton.getInstance().getActive()){
			Intent intent = new Intent(context, MainActivity.class);
		    PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		    
			NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification.Builder builder = new Notification.Builder(context)
			.setContentIntent(pIntent);
			builder.setContentTitle("Pesan dari : " + chatMessage.getUser().getNama());
			builder.setContentText(chatMessage.getUser().getNama() + " : " + chatMessage.getMessage());
			builder.setSmallIcon(R.drawable.ic_launcher);
			builder.setSound( Uri.parse("android.resource://"
		            + context.getPackageName() + "/" + R.raw.dive_alarm));
			Notification notification = builder.build();
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notificationManager.notify(0, notification);
		}
		
	}
}
