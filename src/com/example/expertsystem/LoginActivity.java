package com.example.expertsystem;

import com.example.beans.DataSingleton;
import com.example.beans.LoginBeans;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends FragmentActivity{
	
	private LoginBeans loginBeans;
	public EditText editTextUserName;
	public EditText editTextPassword;
	
	private void initialComponent() {
		loginBeans = new LoginBeans(this);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextUserName = (EditText) findViewById(R.id.editTextUserName);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		initialComponent();

	}
	
	public void onClickLogin(View view){
//		Intent intent = new Intent(this, MainActivity.class);
//		startActivity(intent);
		
		
		loginBeans.setPassword(editTextPassword.getText().toString());
		loginBeans.setUserName(editTextUserName.getText().toString());
		loginBeans.login();
//		startActivity(new Intent(this, MainActivity.class));

	}
	
	public void onClickDaftar(View view){
		Intent intent = new Intent(this, DaftarActivity.class);
		startActivity(intent); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_login, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_setting_server:
	    	EditServerDialog editServerDialog = new EditServerDialog();
	    	editServerDialog.show(getSupportFragmentManager(), "edit_dialog");
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@SuppressLint({ "ValidFragment", "NewApi" })
	public class EditServerDialog extends DialogFragment {
		
		private View rootViewDialog;
		private EditText editTextServerAddress;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Update Server Address");
			LayoutInflater layoutInflater = getActivity().getLayoutInflater();
			
			rootViewDialog = layoutInflater.inflate(R.layout.layot_setting_server_dialog, null);
			builder.setView(rootViewDialog);
			editTextServerAddress = (EditText) rootViewDialog.findViewById(R.id.editTextServerAddress);
			editTextServerAddress.setText(DataSingleton.getInstance().getServerAddress());
			builder.setPositiveButton("SIMPAN",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							loginBeans.updateServerAddress(editTextServerAddress.getText().toString());
						}
					});
			return builder.create();
		}
	}

}
