package com.example.expertsystem;

import com.example.beans.DataSingleton;
import com.example.model.Penyakit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentSaran extends Fragment {

	public View rootView;
	public TextView textViewsaran;
	public Button buttonNext;

	private void initialComponent() {
		buttonNext = (Button) rootView.findViewById(R.id.buttonFinish);
		textViewsaran=(TextView) rootView.findViewById(R.id.textView1);
		buttonNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MainActivity mainActivity = (MainActivity) getActivity();
				mainActivity.setFragment(new FragmentDiagnosa());
			}
		});
		
		Penyakit penyakit = (Penyakit) DataSingleton.getInstance().getInferentor().getSortedResult().get(0);
		textViewsaran.setText(penyakit.getSaran());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_saran, null);
		initialComponent();
		return rootView;
	}
}
