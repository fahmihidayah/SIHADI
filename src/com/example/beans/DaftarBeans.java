package com.example.beans;

import org.json.JSONObject;

import android.widget.Toast;

import com.example.expertsystem.DaftarActivity;
import com.framework.rest_clients.WebRestClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class DaftarBeans {
	private DaftarActivity daftarActivity;
	private String nama;
	private String alamat;
	private String userName;
	private String password;
	private String kelompokTani;
	public DaftarBeans(DaftarActivity daftarActivity) {
		super();
		this.daftarActivity = daftarActivity;
	}
	public void setDaftarActivity(DaftarActivity daftarActivity) {
		this.daftarActivity = daftarActivity;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setKelompokTani(String kelompokTani) {
		this.kelompokTani = kelompokTani;
	}
	public void daftar(){
		RequestParams params = new RequestParams();
		params.put("nama", nama);
		params.put("user_name", userName);
		params.put("password", password);
		params.put("alamat", alamat);
		params.put("kelompok_tani", kelompokTani);
		WebRestClient.post("daftar.php", params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject response) {
				Toast.makeText(daftarActivity, "User baru dibuat ",Toast.LENGTH_LONG).show();
			}
		});
	}

}
