package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserChat implements Serializable {
	private User user;
	private ArrayList<ChatMessage> listChatMessage = new ArrayList<ChatMessage>();
	private int unreadMessage;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<ChatMessage> getListChatMessage() {
		return listChatMessage;
	}
	public void setListChatMessage(ArrayList<ChatMessage> listChatMessage) {
		this.listChatMessage = listChatMessage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserChat other = (UserChat) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	public int getUnreadMessage() {
		return unreadMessage;
	}
	public void setUnreadMessage(int unreadMessage) {
		this.unreadMessage = unreadMessage;
	}
	public void countUnreadMessage(){
		unreadMessage = 0;
		for (ChatMessage e : listChatMessage) {
			unreadMessage = unreadMessage + (e.isRead()?0:1);
		}
	}
	public void setAllRead() {
		for (ChatMessage e : listChatMessage) {
			e.setRead(true);
		}
		
		unreadMessage = 0;
	}
	public void addUnreadMessage() {
		unreadMessage++;
	}
	

}
