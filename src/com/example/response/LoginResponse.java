package com.example.response;

import com.example.model.User;

/*
 * {
 "status": "200",
 "message": "success",
 "data": {
 "id": "1321",
 "nama": "fahmi",
 "gcm_id": "0"
 }
 }
 */
public class LoginResponse extends BaseResponse {
	private User data;

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}

}
