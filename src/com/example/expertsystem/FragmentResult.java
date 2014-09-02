package com.example.expertsystem;

import java.util.ArrayList;


import com.example.beans.DataSingleton;
import com.example.beans.Inferentor;
import com.example.model.Conclution;
import com.example.model.Penyakit;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.CommonUtilities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentResult extends Fragment{
	
	public View rootView;
	public Button buttonNext;
	public ListView listViewPenyakit;
	public CustomAdapter<Conclution> customAdapter;
	public Inferentor inferentor;
	public TextView textViewKesimpulanKata;
	private ArrayList<Conclution> listResult;
	
	
	private void initialComponent(){
		inferentor = DataSingleton.getInstance().getInferentor();
		buttonNext = (Button) rootView.findViewById(R.id.buttonNext);
		textViewKesimpulanKata = (TextView) rootView.findViewById(R.id.textViewKesimpulanKata);
		buttonNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity mainActivity = (MainActivity) getActivity();
				mainActivity.setFragment(new FragmentSaran());
			}
		});
		listViewPenyakit = (ListView) rootView.findViewById(R.id.listViewPenyakit);
		inferentor.countResult();
		listResult = inferentor.getSortedResult();
		customAdapter = new CustomAdapter<Conclution>(getActivity(), R.layout.layout_item_penyakit, listResult) {
			
			@Override
			public void setViewItems(View view, final int position) {
				Conclution conclution = listData.get(position);
				CommonUtilities.setTextToView(view, R.id.textViewResultPenyakit, conclution.getNameStr());
				CommonUtilities.setResToImageView(view, R.id.imageView1, ((Penyakit) conclution).getGambar());			}
		};
		listViewPenyakit.setAdapter(customAdapter);
		
		String kesimpulanKata = "Hasil diagnosa : Tanaman Padi Anda terserang ";
		int i = 0;
		for (Conclution conclution : listResult) {
			if(i == 0){
				kesimpulanKata += conclution.getNameStr() + ",";	
			}else {
				kesimpulanKata += "kemungkinan yang lain terkena :" + conclution.getNameStr() + ",";
			}
			i++;
		}
		textViewKesimpulanKata.setText(kesimpulanKata);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_result, null);
		initialComponent();
		return rootView;
	}
}
