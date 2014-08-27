package com.example.beans;

import java.util.Date;

import org.json.JSONObject;

import com.example.expertsystem.FragmentGroup;
import com.example.model.ChatMessage;
import com.example.model.User;
import com.example.response.ChatMessageResponse;
import com.framework.rest_clients.WebRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GroupBeans {
	private FragmentGroup fragmentGroup;
	private String message;
	private User currentUser;

	public GroupBeans(FragmentGroup fragmentGroup) {
		super();
		this.fragmentGroup = fragmentGroup;
		currentUser = DataSingleton.getInstance().getCurrentUser();
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public void sendMessage(){
		RequestParams params = new RequestParams();
		params.put("id_sender", currentUser.getId());
		params.put("message", message);
		WebRestClient.post("send_message.php", params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ChatMessage chatMessage = new ChatMessage(message, new Date(), currentUser);
				DataSingleton.getInstance().getListChatMessage().add(chatMessage);
				DataSingleton.getInstance().saveToFile(fragmentGroup.getActivity());
				DataSingleton.getInstance().notifyObserverDataChange();
			}
		});
	}
}
