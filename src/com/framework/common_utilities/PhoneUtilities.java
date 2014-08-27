package com.framework.common_utilities;

import android.app.PendingIntent;
import android.telephony.SmsManager;

public class PhoneUtilities {

	public static void simpleSendMessage(String message, String number){
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(number, null, message, null, null);
	}
	
	public static void sendMessage(String message, String number){
		
	}
	
	public static void call(String number){
		
	}

}
