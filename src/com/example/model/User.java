package com.example.model;

import java.io.Serializable;

/*
{
  "status": "200",
  "message": "success",
  "data": {
    "id": "1321",
    "nama": "fahmi",
    "alamat": "",
    "kelompok_tani": "",
    "gcm_id": "0"
  }
}
 */
public class User implements Serializable{
	private String id;
	private String nama;
	private String alamat;
	private String kelompok_tani;
	private String gcm_id;

	

	public User(String id, String nama, String alamat, String kelompok_tani,
			String gcm_id) {
		super();
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.kelompok_tani = kelompok_tani;
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
	
	

}
