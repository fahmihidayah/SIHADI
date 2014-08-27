package com.example.beans;

import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;

import com.example.expertsystem.LoginActivity;
import com.example.expertsystem.MainActivity;
import com.example.model.User;
import com.framework.rest_clients.WebRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainBeans {
	private MainActivity mainActivity;
	private User currentUser;

	public MainBeans(MainActivity mainActivity) {
		super();
		this.mainActivity = mainActivity;
		currentUser = DataSingleton.getInstance().getCurrentUser();
	}
	
	public void logout(){
		RequestParams params = new RequestParams();
		params.put("id", currentUser.getId());
		WebRestClient.post("logout.php", params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				DataSingleton.getInstance().setCurrentActivity("");
				DataSingleton.getInstance().saveToFile(mainActivity);
				DataSingleton.getInstance().getListChatMessage().clear();
				mainActivity.startActivity(new Intent(mainActivity, LoginActivity.class));
				mainActivity.finish();
			}
		});
	}
	public void updateServerAddress(String serverAddress){
		DataSingleton.getInstance().setServerAddress(serverAddress);
		DataSingleton.getInstance().saveToFile(mainActivity);
		Toast.makeText(mainActivity, "Server Updated", Toast.LENGTH_LONG).show();
	}

}
