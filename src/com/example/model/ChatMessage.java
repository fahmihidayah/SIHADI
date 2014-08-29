package com.example.model;

import java.io.Serializable;
import java.util.Date;

/*
 * {
 "status": "200",
 "message": "success",
 "data": {
 "message": "dsd",	
 "user": {
 "id": "1321",
 "nama": "fahmi",
 "gcm_id": null
 }
 }
 }
 */
public class ChatMessage implements Serializable{
	private String message;
	private Date time;
	private User user;
	private boolean read = false;
		
	public ChatMessage(String message, Date time, User user) {
		super();
		this.message = message;
		this.time = time;
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
	
	

}
