package com.framework.navigation_drawer_v1;

public class MenuDrawer {
	private int resourceImage;
	private String titleMenu;

	public MenuDrawer(int resourceImage, String titleMenu) {
		super();
		this.resourceImage = resourceImage;
		this.titleMenu = titleMenu;
	}

	public int getResourceImage() {
		return resourceImage;
	}

	public void setResourceImage(int resourceImage) {
		this.resourceImage = resourceImage;
	}

	public String getTitleMenu() {
		return titleMenu;
	}

	public void setTitleMenu(String titleMenu) {
		this.titleMenu = titleMenu;
	}

}
