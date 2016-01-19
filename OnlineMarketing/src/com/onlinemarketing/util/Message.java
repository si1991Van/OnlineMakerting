package com.onlinemarketing.util;

import android.content.Context;
import android.widget.Toast;

public class Message {
	Context context;

	public Message(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public void showMessage(String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
	
	public static void showMessage(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
}
