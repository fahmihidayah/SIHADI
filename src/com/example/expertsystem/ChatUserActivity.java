package com.example.expertsystem;

import java.util.Observable;
import java.util.Observer;

import com.example.beans.ChatUserBeans;
import com.example.beans.DataSingleton;
import com.example.model.ChatMessage;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.CommonUtilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ChatUserActivity extends Activity implements Observer {
	
	public ListView listViewChatMessage;
	public CustomAdapter<ChatMessage> customAdapter;
	public EditText editTextMessage;
	public Button buttonSend;
	private ChatUserBeans chatUserBeans;
	
	private void initialComponent(){
		chatUserBeans = new ChatUserBeans(this);
		listViewChatMessage = (ListView) findViewById(R.id.listViewChatMessage);
		buttonSend = (Button) findViewById(R.id.buttonSend);
		editTextMessage = (EditText) findViewById(R.id.editTextMessage);
		DataSingleton.getInstance().addObserver(this);
		customAdapter = new CustomAdapter<ChatMessage>(this, R.layout.layout_item_message, chatUserBeans.getUserChat().getListChatMessage()) {
			
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
		
		buttonSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chatUserBeans.sendMessage(editTextMessage.getText().toString());
				editTextMessage.setText("");
				
			}
		});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_group);
		initialComponent();
		
	}

	@Override
	public void update(Observable observable, Object data) {
		chatUserBeans.getUserChat().setAllRead();
		Toast.makeText(this, "got new messaage", Toast.LENGTH_LONG).show();
		customAdapter.notifyDataSetChanged();
	}
	
	
}
