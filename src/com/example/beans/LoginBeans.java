package com.example.beans;

import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;

import com.example.expertsystem.LoginActivity;
import com.example.expertsystem.MainActivity;
import com.example.model.Constants;
import com.example.model.User;
import com.example.response.LoginResponse;
import com.framework.gcm.GcmDataHandler;
import com.framework.gcm.OnFinishListenner;
import com.framework.rest_clients.WebRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LoginBeans implements OnFinishListenner, Constants{
	private LoginActivity loginActivity;
	private String userName;
	private String password;
	private GcmDataHandler gcmDataHandler;
	private User currentUser;

	public LoginBeans(LoginActivity loginActivity) {
		super();
		this.loginActivity = loginActivity;
		gcmDataHandler = new GcmDataHandler(loginActivity);
		gcmDataHandler.setOnFinishListenner(this);
		gcmDataHandler.setSenderId(SENDER_ID);
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	};
	
	public void login(){
		RequestParams params = new RequestParams();
		params.put("user_name", userName);
		params.put("password", password);
		WebRestClient.post("login.php", params, new JsonHttpResponseHandler(){
			
			@Override
			public void onSuccess(JSONObject response) {
				LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);
				if(loginResponse.getStatus().equalsIgnoreCase("200")){
					currentUser = loginResponse.getData();
					if(currentUser.getGcm_id().equalsIgnoreCase("0")){
						gcmDataHandler.registerDevice();
					}
					else {
						Toast.makeText(loginActivity, "User in used", Toast.LENGTH_LONG).show();
					}
				}
				else {
					Toast.makeText(loginActivity, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
				}
				
			}
		});
	}
	
	
	@Override
	public void finishRegister(String registerID) {
		currentUser.setGcm_id(registerID);
		RequestParams params = new RequestParams();
		params.put("id", currentUser.getId());
		params.put("gcm_id", currentUser.getGcm_id());
		WebRestClient.post("update_user.php", params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Intent intent = new Intent(loginActivity, MainActivity.class);
				loginActivity.startActivity(intent);
				DataSingleton.getInstance().setCurrentActivity(MAIN_ACTIVITY);
				DataSingleton.getInstance().setCurrentUser(currentUser);
				DataSingleton.getInstance().saveToFile(loginActivity);
				loginActivity.finish();
			}
		});
	}
	public void updateServerAddress(String serverAddress){
		DataSingleton.getInstance().setServerAddress(serverAddress);
		DataSingleton.getInstance().saveToFile(loginActivity);
		Toast.makeText(loginActivity, "Server Updated", Toast.LENGTH_LONG).show();
	}
}
