package com.example.model;

public class Penyakit extends Conclution {
	private String saran;
	private int gambar;
	
	public Penyakit(String nameStr) {
		super(nameStr);
	}

	public String getSaran() {
		return saran;
	}

	public void setSaran(String saran) {
		this.saran = saran;
	}

	public int getGambar() {
		return gambar;
	}

	public void setGambar(int gambar) {
		this.gambar = gambar;
	}
	
	

}
