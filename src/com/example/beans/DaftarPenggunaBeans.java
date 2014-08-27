package com.example.beans;

import java.util.ArrayList;

import org.json.JSONObject;

import android.widget.Toast;

import com.example.expertsystem.FragmentDaftarPengguna;
import com.example.model.User;
import com.example.response.ListUserResponse;
import com.framework.rest_clients.WebRestClient;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class DaftarPenggunaBeans {
	private FragmentDaftarPengguna fragmentDaftarPengguna;
	private ArrayList<User> daftarPengguna;
	
	
	public DaftarPenggunaBeans(FragmentDaftarPengguna fragmentDaftarPengguna) {
		super();
		this.fragmentDaftarPengguna = fragmentDaftarPengguna;
		daftarPengguna = DataSingleton.getInstance().getListPengguna();
	}
	
	public void requestDaftarPengguna(){
		RequestParams params = new RequestParams();
		
		WebRestClient.post("list_user.php", params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				ListUserResponse listUserResponse = new Gson().fromJson(response.toString(), ListUserResponse.class);
				DataSingleton.getInstance().getListPengguna().clear();
				DataSingleton.getInstance().getListPengguna().addAll(listUserResponse.getData());
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
	
	 
}
