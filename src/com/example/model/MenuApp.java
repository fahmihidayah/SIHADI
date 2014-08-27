package com.example.model;

public class MenuApp {
	private int icon;
	private String name;

	public MenuApp(int icon, String name) {
		super();
		this.icon = icon;
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
