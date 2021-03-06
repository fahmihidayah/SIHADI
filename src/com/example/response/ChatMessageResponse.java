package com.example.response;

import java.util.Date;

import com.example.model.ChatMessage;
import com.example.model.User;

/*
 * {
 "status": "200",
 "message": "success",
 "data": {
 "message_data": "hello",
 "id": "1321",
 "nama": "fahmi",
 "gcm_id": "0"
 }
 }
 */
public class ChatMessageResponse extends BaseResponse {
	private String message_data;
	private String id;
	private String nama;
	private String alamat;
	private String kelompok_tani;	
	private String gcm_id;
	private String grup;

	public String getMessage_data() {
		return message_data;
	}

	public void setMessage_data(String message_data) {
		this.message_data = message_data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getGcm_id() {
		return gcm_id;
	}

	public void setGcm_id(String gcm_id) {
		this.gcm_id = gcm_id;
	}
	
	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKelompok_tani() {
		return kelompok_tani;
	}

	public void setKelompok_tani(String kelompok_tani) {
		this.kelompok_tani = kelompok_tani;
	}

	public String getGrup() {
		return grup;
	}

	public void setGrup(String grup) {
		this.grup = grup;
	}

	public ChatMessage getChatMessage(){
		ChatMessage chatMessage = new ChatMessage(message_data, new Date(), new User(id, nama, alamat, kelompok_tani, gcm_id));
		return chatMessage;
	}

	
}
