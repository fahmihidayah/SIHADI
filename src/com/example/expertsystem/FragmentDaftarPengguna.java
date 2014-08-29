package com.example.expertsystem;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.example.beans.DaftarPenggunaBeans;
import com.example.beans.DataSingleton;
import com.example.model.User;
import com.example.model.UserChat;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.CommonUtilities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class FragmentDaftarPengguna extends Fragment implements Observer{
	
	public View rootView;
	public ListView listViewPengguna;
	public CustomAdapter<UserChat> customAdapter;
	private DaftarPenggunaBeans daftarPenggunaBeans;
	private void initialComponent(){
		daftarPenggunaBeans = new DaftarPenggunaBeans(this);
		listViewPengguna = (ListView) rootView.findViewById(R.id.listViewPengguna);
		customAdapter = new CustomAdapter<UserChat>(getActivity(), R.layout.layout_item_pengguna, daftarPenggunaBeans.getListUserChat()) {
			
			@Override
			public void setViewItems(View view, int position) {
				UserChat user = listData.get(position);
				CommonUtilities.setTextToView(view, R.id.textViewNamaPengguna, "Id : " + user.getUser().getId() + "\nNama : " +  user.getUser().getNama() + "\nUnread Message : " + user.getUnreadMessage());
			}
		};
		listViewPengguna.setAdapter(customAdapter);
		daftarPenggunaBeans.requestDaftarPengguna();
		listViewPengguna.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				daftarPenggunaBeans.selectUserToChat(arg2);
			}
		});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_daftar_pengguna, null);
		this.initialComponent();
		return rootView;
	}

	@Override
	public void onResume() {
		customAdapter.notifyDataSetChanged();
		DataSingleton.getInstance().addObserver(this);
		super.onResume();
	}
	
	@Override
	public void onPause() {
		DataSingleton.getInstance().deleteObserver(this);
		super.onPause();
	}
	@Override
	public void update(Observable observable, Object data) {
		customAdapter.notifyDataSetChanged();
	}
}
