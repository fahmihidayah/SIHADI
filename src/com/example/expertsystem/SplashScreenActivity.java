package com.example.expertsystem;

import com.example.beans.DataSingleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreenActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_splash_screen);
		
		DataSingleton.getInstance().loadFromFile(this);
		DataSingleton.getInstance().setActive(true);
		DataSingleton.getInstance().saveToFile(this);
		Toast.makeText(this, "status " + DataSingleton.getInstance().getActive(), Toast.LENGTH_LONG).show();
		if(DataSingleton.getInstance().getCurrentActivity().isEmpty()){
			startActivity(new Intent(this, LoginActivity.class));
		}
		else {
			startActivity(new Intent(DataSingleton.getInstance().getCurrentActivity()));
		}
		finish();
		
	}
}
