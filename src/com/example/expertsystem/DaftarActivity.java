package com.example.expertsystem;

import com.example.beans.DaftarBeans;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DaftarActivity extends Activity {
	
	public EditText editTextNama;
	public EditText editTextAlamat;
	public EditText editTextUserName;
	public EditText editTextPassword;
	public EditText editTextKelompokTani;
	private DaftarBeans daftarBeans;
	
	private void initialComponent() {
		daftarBeans = new DaftarBeans(this);
		editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
		editTextNama = (EditText)findViewById(R.id.editTextName);
		editTextUserName =(EditText) findViewById(R.id.editTextUserName);
		editTextPassword =(EditText)findViewById(R.id.editTextPassword);
		editTextKelompokTani = (EditText)findViewById(R.id.editTextKelompokTani);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_daftar);
		initialComponent();

	}
	
	public void onClickDaftar(View view){
		daftarBeans.setAlamat(editTextAlamat.getText().toString());
		daftarBeans.setKelompokTani(editTextKelompokTani.getText().toString());
		daftarBeans.setNama(editTextNama.getText().toString());
		daftarBeans.setPassword(editTextPassword.getText().toString());
		daftarBeans.setUserName(editTextUserName.getText().toString());
		daftarBeans.daftar();
	}
	

}
