package com.example.expertsystem;

import java.util.Observable;
import java.util.Observer;

import com.example.beans.DataSingleton;
import com.example.beans.GroupBeans;
import com.example.model.ChatMessage;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.CommonUtilities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentGroup extends Fragment implements Observer{
	
	public View rootView;
	public CustomAdapter<ChatMessage> customAdapter;
	public ListView listViewChatMessage;
	public Button buttonSend;
	public EditText editTextMessage;
	private GroupBeans groupBeans;
	private void initialComponent(){
		DataSingleton.getInstance().addObserver(this);
		groupBeans = new GroupBeans(this);
		listViewChatMessage = (ListView) rootView.findViewById(R.id.listViewChatMessage);
		customAdapter = new CustomAdapter<ChatMessage>(getActivity(), R.layout.layout_item_message, DataSingleton.getInstance().getListChatMessage()) {
			
			@Override
			public void setViewItems(View view, int position) {
				ChatMessage data = listData.get(position);
				int grafity = Gravity.LEFT;
				if(data.getUser().getId().equalsIgnoreCase(DataSingleton.getInstance().getCurrentUser().getId())){
					grafity = Gravity.RIGHT;
				}
				CommonUtilities.setTextToView(view, R.id.textViewContactName, data.getUser().getNama()).setGravity(grafity);
				CommonUtilities.setTextToView(view, R.id.textViewMessage, data.getMessage()).setGravity(grafity);
				String time = CommonUtilities.getStringDate(data.getTime(), "h:mm a");
				CommonUtilities.setTextToView(view, R.id.textViewTime, time);
			}
		};
		listViewChatMessage.setAdapter(customAdapter);
		buttonSend = (Button) rootView.findViewById(R.id.buttonSend);
		buttonSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!editTextMessage.getText().toString().equalsIgnoreCase("")){
					groupBeans.setMessage(editTextMessage.getText().toString());
					groupBeans.sendMessage();
					editTextMessage.setText("");
				}
			}
		});
		editTextMessage = (EditText) rootView.findViewById(R.id.editTextMessage);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.layout_group, null);
		initialComponent();
		return rootView;
	}
	
	@Override
	public void onDestroyView() {
		DataSingleton.getInstance().deleteObserver(this);
		super.onDestroyView();
	}

	@Override
	public void update(Observable observable, Object data) {
		customAdapter.notifyDataSetChanged();
		// list view to last
		listViewChatMessage.setSelection(customAdapter.getCount() - 1);
	}
}
