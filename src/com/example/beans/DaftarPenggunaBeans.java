package com.example.beans;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Intent;
import android.widget.Toast;

import com.example.expertsystem.ChatUserActivity;
import com.example.expertsystem.FragmentDaftarPengguna;
import com.example.model.User;
import com.example.model.UserChat;
import com.example.response.ListUserResponse;
import com.framework.rest_clients.WebRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class DaftarPenggunaBeans {
	private FragmentDaftarPengguna fragmentDaftarPengguna;
	private ArrayList<User> daftarPengguna;
	private ArrayList<UserChat> listUserChat = new ArrayList<UserChat>();
	
	public DaftarPenggunaBeans(FragmentDaftarPengguna fragmentDaftarPengguna) {
		super();
		this.fragmentDaftarPengguna = fragmentDaftarPengguna;
		daftarPengguna = DataSingleton.getInstance().getListPengguna();
		listUserChat = DataSingleton.getInstance().getListChatUser();
	}
	
	public void requestDaftarPengguna(){
		RequestParams params = new RequestParams();
		params.put("user_id", DataSingleton.getInstance().getCurrentUser().getId());
		WebRestClient.post("list_user.php", params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ListUserResponse listUserResponse = new Gson().fromJson(response.toString(), ListUserResponse.class);
				DataSingleton.getInstance().getListPengguna().clear();
				DataSingleton.getInstance().getListPengguna().addAll(listUserResponse.getData());
				addUserChat();
				DataSingleton.getInstance().saveToFile(fragmentDaftarPengguna.getActivity());
				Toast.makeText(fragmentDaftarPengguna.getActivity(), "Sukses request data", Toast.LENGTH_LONG).show();
				fragmentDaftarPengguna.customAdapter.notifyDataSetChanged();
			}
		});
	}
	public ArrayList<User> getDaftarPengguna() {
		return daftarPengguna;
	}
	public void setDaftarPengguna(ArrayList<User> daftarPengguna) {
		this.daftarPengguna = daftarPengguna;
	}
	
	
	public ArrayList<UserChat> getListUserChat() {
		return listUserChat;
	}

	public void setListUserChat(ArrayList<UserChat> listUserChat) {
		this.listUserChat = listUserChat;
	}

	public void selectUserToChat(int index){
		ArrayList<UserChat> listUserChat = DataSingleton.getInstance().getListChatUser();
		UserChat userChatSelected = new UserChat();
		userChatSelected.setUser(daftarPengguna.get(index));
		int indexUserChat = listUserChat.indexOf(userChatSelected);
		if(indexUserChat == -1){
			DataSingleton.getInstance().getListChatUser().add(userChatSelected);
			DataSingleton.getInstance().saveToFile(fragmentDaftarPengguna.getActivity());
			DataSingleton.getInstance().saveToFile(fragmentDaftarPengguna.getActivity());
			indexUserChat = DataSingleton.getInstance().getListChatUser().indexOf(userChatSelected);
		}
		Intent intent = new Intent(fragmentDaftarPengguna.getActivity(), ChatUserActivity.class);
		intent.putExtra("index", indexUserChat);
		fragmentDaftarPengguna.getActivity().startActivity(intent);
		
	}
	
	private void addUserChat(){
		for (User user : daftarPengguna) {
			UserChat userChat = new UserChat();
			userChat.setUser(user);
			if(!listUserChat.contains(userChat)){
				listUserChat.add(userChat);
			}
		}
	}
	 
}
