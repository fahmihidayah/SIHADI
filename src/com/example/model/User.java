package com.example.model;

import java.io.Serializable;

/*
 * {
 "data": {
 "id": "1321",
 "nama": "fahmi",
 "gcm_id": "0"
 }
 }
 */
public class User implements Serializable{
	private String id;
	private String nama;
	private String gcm_id;

	public User(String id, String nama, String gcm_id) {
		super();
		this.id = id;
		this.nama = nama;
		this.gcm_id = gcm_id;
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

}
