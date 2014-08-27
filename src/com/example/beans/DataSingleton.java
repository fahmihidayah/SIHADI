package com.example.beans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;
import android.widget.Toast;

import com.example.model.ChatMessage;
import com.example.model.Conclution;
import com.example.model.Constants;
import com.example.model.Premis;
import com.example.model.User;
import com.framework.file_handler.FileHandler;
/**
 * 
 * @author fahmi
 * kurang menyimpan daftar pengguna
 */
public class DataSingleton extends Observable implements Constants {
	private static DataSingleton instance;
	private Inferentor inferentor = new Inferentor();
	private User currentUser ;
	private String currentActivity = "";
	private ArrayList<ChatMessage> listChatMessage;
	private ArrayList<User> listPengguna = new ArrayList<User>();
	private String serverAddress = "192.168.1.1";

	protected DataSingleton() {
		listChatMessage = new ArrayList<ChatMessage>();
	}

	public static DataSingleton getInstance() {
		if (instance == null) {
			instance = new DataSingleton();
		}
		return instance;
	}

	public Inferentor getInferentor() {
		return inferentor;
	}

	public void setInferentor(Inferentor inferentor) {
		this.inferentor = inferentor;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(String currentActivity) {
		this.currentActivity = currentActivity;
	}

	public ArrayList<ChatMessage> getListChatMessage() {
		return listChatMessage;
	}

	public void setListChatMessage(ArrayList<ChatMessage> listChatMessage) {
		this.listChatMessage = listChatMessage;
	}
	
	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public void notifyObserverDataChange(){
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<User> getListPengguna() {
		return listPengguna;
	}

	public void setListPengguna(ArrayList<User> listPengguna) {
		this.listPengguna = listPengguna;
	}

	public void saveToFile(Context context){
		try {
			FileHandler.saveDataToFile(context, CURRENT_ACTIVITY_FILE, Context.MODE_PRIVATE, currentActivity);
			FileHandler.saveDataToFile(context, CURRENT_USER_DATA, Context.MODE_PRIVATE, currentUser);
			FileHandler.saveDataToFile(context, CHAT_MESSAGE_DATA, Context.MODE_PRIVATE, listChatMessage);
			FileHandler.saveDataToFile(context, SERVER_ADDRESS, Context.MODE_PRIVATE, serverAddress);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFromFile(Context context){
		try {
			currentActivity = (String) FileHandler.loadDataFromFile(context,CURRENT_ACTIVITY_FILE);
			currentUser = (User) FileHandler.loadDataFromFile(context, CURRENT_USER_DATA);
			listChatMessage = (ArrayList<ChatMessage>) FileHandler.loadDataFromFile(context, CHAT_MESSAGE_DATA);
			serverAddress = (String) FileHandler.loadDataFromFile(context, SERVER_ADDRESS);
		} catch (StreamCorruptedException e) {
			Toast.makeText(context, "StreamCorruptedException", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			Toast.makeText(context, "FileNotFoundException", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Toast.makeText(context, "IOException", Toast.LENGTH_LONG).show();
		} catch (ClassNotFoundException e) {
			Toast.makeText(context, "ClassNotFoundException", Toast.LENGTH_LONG).show();
		}
		
	}

}
