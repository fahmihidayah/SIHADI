package com.framework.navigation_drawer_v1;

import java.util.ArrayList;

import com.example.expertsystem.R;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.CommonUtilities;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@SuppressLint("NewApi")
public abstract class NavigationDrawerActivity extends Activity {
	protected ArrayList<MenuDrawer> listMenuDrawers;
	protected CustomAdapter<MenuDrawer> adapterMenuDrawers;
	protected DrawerLayout mDrawerLayout;
	protected ListView mDrawerList;
	protected ActionBarDrawerToggle mDrawerToggle;
	protected String title = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getNavigationDrawerLayout());
		// enabling action bar app icon and behaving it as toggle button

		mDrawerList = (ListView) findViewById(getListViewResource());
		mDrawerLayout = (DrawerLayout) findViewById(getDrawerLayout());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				 
				getActionBar().setTitle(title);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle("Ini Contoh Close");
				getActionBar().setTitle(R.string.app_name);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				displayView(arg2);
			}
		});
		listMenuDrawers = new ArrayList<MenuDrawer>();
		setListDrawerMenu(listMenuDrawers);
		initialCustomAdapter();
		mDrawerList.setAdapter(adapterMenuDrawers);
		afterInitializeConfiguration(savedInstanceState);
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = getFragmentFromIndex(position);
		if (fragment != null) {
			setFragment(fragment);
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			title = listMenuDrawers.get(position).getTitleMenu();
			mDrawerLayout.closeDrawer(mDrawerList);

		} else {
			Log.e("MainActivity", "Error in creating fragment");
		}

	}

	public void setFragment(Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
		fragmentManager.beginTransaction()
				.replace(getFrameContainerLayout(), fragment).commit();

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * overide this method to perform when list item selected
	 * 
	 * @param i
	 * @return
	 */
	public abstract Fragment getFragmentFromIndex(int i);

	/**
	 * overide this method
	 */
	public abstract void setListDrawerMenu(ArrayList<MenuDrawer> listMenuDrawers);

	/**
	 * overide this if you want to custom the listview item view
	 */
	public void afterInitializeConfiguration(Bundle saveInstanceState) {

	}

	public void initialCustomAdapter() {
		adapterMenuDrawers = new CustomAdapter<MenuDrawer>(this,
				R.layout.drawer_list_item, listMenuDrawers) {

			@Override
			public void setViewItems(View view, int position) {
				MenuDrawer menuDrawer = listData.get(position);
				CommonUtilities.setTextToView(view, R.id.title,
						menuDrawer.getTitleMenu());
				CommonUtilities.setResToImageView(view, R.id.icon, menuDrawer.getResourceImage());
			}
		};
	}

	public int getListViewResource() {
		return R.id.list_slidermenu;
	}

	public int getFrameContainerLayout() {
		return R.id.frame_container;
	}

	public int getDrawerLayout() {
		return R.id.drawer_layout;
	}

	public int getNavigationDrawerLayout() {
		return R.layout.navigation_drawer_activity;
	}

}
