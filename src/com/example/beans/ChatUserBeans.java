package com.example.beans;

import org.json.JSONObject;

import android.widget.Toast;

import com.example.expertsystem.ChatUserActivity;
import com.example.model.User;
import com.example.model.UserChat;
import com.framework.rest_clients.WebRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ChatUserBeans {
	
	private ChatUserActivity chatUserActivity;
	private UserChat userChat = new UserChat();
	private User currentUser;
	
	public ChatUserBeans(ChatUserActivity chatUserActivity) {
		super();
		this.chatUserActivity = chatUserActivity;
		int index = chatUserActivity.getIntent().getIntExtra("index", -1);
		if(index == -1){
			chatUserActivity.onBackPressed();
		}
		else {
			userChat = DataSingleton.getInstance().getListChatUser().get(index);
			currentUser = DataSingleton.getInstance().getCurrentUser();
		}
	}
	
	public void sendMessage(String message){
		RequestParams params = new RequestParams();
		params.put("id_sender", currentUser.getId());
		params.put("message", message);
		params.put("id_receiver", userChat.getUser().getId());
		WebRestClient.post("send_message.php", params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(chatUserActivity, "message sent", Toast.LENGTH_LONG).show();
			}
		});
	}

	public UserChat getUserChat() {
		return userChat;
	}

	public void setUserChat(UserChat userChat) {
		this.userChat = userChat;
	}
	
	

}
