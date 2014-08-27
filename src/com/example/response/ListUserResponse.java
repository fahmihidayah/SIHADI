package com.example.response;

import java.util.ArrayList;

import com.example.model.User;

/**
 * 
 * @author fahmi
{
  status: "200",
  message: "success",
  data: [
    {
      id: "323",
      nama: "doni",
      alamat: "",
      kelompok_tani: "",
      gcm_id: "111"
    }
  ]
}
 */
public class ListUserResponse extends BaseResponse{
	private ArrayList<User> data;

	public ArrayList<User> getData() {
		return data;
	}

	public void setData(ArrayList<User> data) {
		this.data = data;
	}
	
	
}
