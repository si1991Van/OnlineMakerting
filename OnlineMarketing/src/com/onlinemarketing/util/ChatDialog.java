package com.onlinemarketing.util;

import java.util.ArrayList;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.adapter.ListMessageAdapter;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonMessage;
import com.onlinemarketing.object.MessageVO;
import com.onlinemarketing.object.OutputMessage;
import com.onlinemarketing.object.ProfileVO;
import com.smile.android.gsm.utils.AndroidUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ChatDialog {

	Context context;
	static Dialog dialogListMsg;
	ListView  listviewChat;
	ArrayList<MessageVO> listMessage = new ArrayList<MessageVO>();
	public static OutputMessage oOputMsg;
	ListMessageAdapter adapterListMessage;
	static Dialog dialog;
	Button btn_SMS, btnListChat, btnSend;
	EditText editMessage, editSendMessage;
	TextView txtShowMessageChat;
	TableLayout tab;
	static String messageMsg;
	int iduser;
	int status_callWS = 0;
	public ChatDialog(Context context) {
		super();
		this.context = context;
	}
	public ChatDialog(Context context, int id){
		this.iduser = id;
	}
	public void run(int status){
		if(status == SystemConfig.statusListMessage)
		{
			status_callWS = SystemConfig.statusListMessage;
			new MessageAsystask().execute(status);
		}
		else if (status == SystemConfig.statusSendMessage) 
		{
			status_callWS = SystemConfig.statusSendMessage;
			new MessageAsystask().execute(status);
		}
	}
	public void dialogListMessage() {
		
		dialogListMsg = new Dialog(context);
		dialogListMsg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialogListMsg.setContentView(R.layout.dialog_list_message);
		listviewChat = (ListView) dialogListMsg.findViewById(R.id.listChat);
		listviewChat.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				iduser = listMessage.get(position).getReceiver_id();
				
				dialogChat(iduser);
				dialogListMsg.dismiss();
			}
		});
		dialogListMsg.show();
	}
	
	public Dialog dialogChat(int iduser) {
		
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_chat);
		btn_SMS = (Button) dialog.findViewById(R.id.btnMessageChat);
		btnListChat = (Button) dialog.findViewById(R.id.btnListMessagesChat);
		btnSend = (Button) dialog.findViewById(R.id.btnSendChat);
		editSendMessage = (EditText) dialog.findViewById(R.id.txtMessageChat);
		tab = (TableLayout) dialog.findViewById(R.id.tab);
		dialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				com.lib.Debug.showAlert(context, "Chet chu con");
			}
		});
		btnListChat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (AndroidUtils.isConnectedToInternet(context)) {
					ChatDialog chat = new ChatDialog(context);
					chat.run(SystemConfig.statusListMessage);
					dialog.dismiss();
				}
			}
		});
		btnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setStyleSendMessage();
				run(SystemConfig.statusSendMessage);
			}
		});
		dialog.show();
		return dialog;
	}
	public  void setStyleSendMessage(){
		
		TableRow tr2 = new TableRow(context.getApplicationContext());
		tr2.setLayoutParams(new TableRow.LayoutParams(
		TableRow.LayoutParams.MATCH_PARENT,
		TableRow.LayoutParams.WRAP_CONTENT, Gravity.LEFT));
		
		TextView textview = new TextView(context
				.getApplicationContext());
		textview.setGravity(Gravity.LEFT);
		textview.setTextSize(20);
		textview.setTextColor(Color.parseColor("#A901DB"));
		ProfileVO objprofile = SystemConfig.oOputproduct.getProfileVO()
				.get(0);
		textview.setText(Html.fromHtml("<b>" + objprofile.getEmail()
				+ " : </b>" + editSendMessage.getText().toString()));
		tr2.addView(textview);
		tab.addView(tr2);
		messageMsg = editSendMessage.getText().toString();
		editSendMessage.setText(null);
	}
	public  void setStylerecivedMessage(){
			
			TableRow tr2 = new TableRow(context.getApplicationContext());
			tr2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT, Gravity.RIGHT));
			TextView textview = new TextView(context.getApplicationContext());
			textview.setTextSize(20);
			textview.setTextColor(Color.parseColor("#0101DF"));
			ProfileVO objprofile = SystemConfig.oOputproduct.getProfileVO()
					.get(0);
			textview.setText(Html.fromHtml("<b>" + objprofile.getEmail()
					+ " : </b>" + editSendMessage.getText().toString()));
			tr2.addView(textview);
			tab.addView(tr2);
			messageMsg = editSendMessage.getText().toString();
			editSendMessage.setText(null);
		}
	public class MessageAsystask extends
			AsyncTask<Integer, Integer, OutputMessage> {
		JsonMessage message;
//		int status_callWS=0;

		@Override
		protected void onPreExecute() {
			message = new JsonMessage();
			Debug.e("sssssssssssssssssssssssssssssssssssss: "+status_callWS);
			if (SystemConfig.statusListMessage == status_callWS) {
				dialogListMessage();
			}
			super.onPreExecute();
		}

		@Override
		protected OutputMessage doInBackground(Integer... params) {
//			status_callWS = params[0];
			switch (params[0]) {
			case SystemConfig.statusListMessage:
				oOputMsg = message.paseListHistoryMessage(SystemConfig.user_id,
						SystemConfig.session_id, SystemConfig.device_id);
				listMessage = oOputMsg.getArrMessage();
				break;
			case SystemConfig.statusSendMessage:
				oOputMsg = message.SendMessage(SystemConfig.user_id,
						SystemConfig.session_id, SystemConfig.device_id, iduser,messageMsg);
				break;
			}
			return oOputMsg;
		}

		@Override
		protected void onPostExecute(OutputMessage result) {
			if (result.getCode() == Constan.getIntProperty("success") && status_callWS == SystemConfig.statusListMessage) {
				adapterListMessage = new ListMessageAdapter(context,
						R.layout.item_list_message, listMessage);
				com.lib.Debug.e(listMessage.size() + "");
				listviewChat.setAdapter(adapterListMessage);
			}
		}
	}

}
