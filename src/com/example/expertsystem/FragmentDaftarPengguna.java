package com.example.expertsystem;

import java.util.ArrayList;

import com.example.beans.DaftarPenggunaBeans;
import com.example.model.User;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.CommonUtilities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentDaftarPengguna extends Fragment{
	
	public View rootView;
	public ListView listViewPengguna;
	public CustomAdapter<User> customAdapter;
	private DaftarPenggunaBeans daftarPenggunaBeans;
	private void initialComponent(){
		daftarPenggunaBeans = new DaftarPenggunaBeans(this);
		listViewPengguna = (ListView) rootView.findViewById(R.id.listViewPengguna);
		customAdapter = new CustomAdapter<User>(getActivity(), R.layout.layout_item_pengguna, daftarPenggunaBeans.getDaftarPengguna()) {
			
			@Override
			public void setViewItems(View view, int position) {
				User user = listData.get(position);
				CommonUtilities.setTextToView(view, R.id.textViewNamaPengguna, "Nama : " +  user.getNama());
			}
		};
		listViewPengguna.setAdapter(customAdapter);
		daftarPenggunaBeans.requestDaftarPengguna();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_daftar_pengguna, null);
		this.initialComponent();
		return rootView;
	}
}
