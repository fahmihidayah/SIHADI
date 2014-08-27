package com.example.expertsystem;

import com.example.beans.DataSingleton;
import com.example.beans.Inferentor;
import com.example.model.Premis;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.CommonUtilities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

public class FragmentDiagnosa extends Fragment{
	
	public View rootView;
	public Button buttonNext;
	public ListView listViewGejala;
	public CustomAdapter<Premis> customAdapter;
	public Inferentor inferentor;
	
	private void initialComponent(){
		inferentor = DataSingleton.getInstance().getInferentor();
		buttonNext = (Button) rootView.findViewById(R.id.buttonNext);
		buttonNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity mainActivity = (MainActivity) getActivity();
				mainActivity.setFragment(new FragmentResult());
			}
		});
		listViewGejala = (ListView) rootView.findViewById(R.id.listViewGejala);
		customAdapter = new CustomAdapter<Premis>(getActivity(), R.layout.layout_item_gejala, inferentor.getListPremis()) {
			
			@Override
			public void setViewItems(View view, final int position) {
				Premis premis = listData.get(position);
				CommonUtilities.setTextToView(view, R.id.textViewGejala, premis.getNameStr());
				CheckBox checkBoxGejala = (CheckBox) view.findViewById(R.id.checkBoxGejala);
				checkBoxGejala.setChecked(premis.isValue());
				checkBoxGejala.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						listData.get(position).setValue(isChecked);
					}
				});
			}
		};
		listViewGejala.setAdapter(customAdapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_diagnosa, null);
		initialComponent();
		return rootView;
	}
}
