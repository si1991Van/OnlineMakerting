package com.onlinemarketing.asystask;

import com.example.onlinemarketing.HomePageActivity;
import com.lib.Debug;
import com.lib.SharedPreferencesUtils;
import com.onlinemarketing.activity.LoginActivity;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonAccount;
import com.onlinemarketing.object.LoginRegister;
import com.onlinemarketing.util.Message;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.AsyncTask;
import android.sax.StartElementListener;

public class LoginRegisterAsystask extends
		AsyncTask<Integer, String, LoginRegister> {

	Context context;
	JsonAccount json;
	LoginRegister account;
	boolean chkRemember;

	public LoginRegisterAsystask(String email, String pass, String device_id,
			String user_id, String session_id, boolean chkremember, Context context) {
		super();
		SystemConfig.Email = email;
		SystemConfig.Pass = pass;
		SystemConfig.device_id = device_id;
		SystemConfig.user_id = user_id;
		SystemConfig.session_id = session_id;
		this.chkRemember = chkremember;
		this.context =  (Activity) context;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		json = new JsonAccount();
		account = new LoginRegister();
		super.onPreExecute();
	}

	@Override
	protected LoginRegister doInBackground(Integer... params) {
		switch (params[0]) {
		case SystemConfig.statusLogin:
			account = json.paserRegister(SystemConfig.Email, SystemConfig.Pass,
					SystemConfig.device_id, SystemConfig.user_id,
					SystemConfig.session_id, SystemConfig.statusLogin);
			break;
		case SystemConfig.statusRegister:
			account = json.paserRegister(SystemConfig.Email, SystemConfig.Pass,
					SystemConfig.device_id, SystemConfig.user_id,
					SystemConfig.session_id, SystemConfig.statusRegister);
			break;
		}
		return account;

	}

	@Override
	protected void onPostExecute(LoginRegister result) {
		try{
		SharedPreferencesUtils.putString(context, "device_id", SystemConfig.device_id);
		if (result.getCode() == Constan.getIntProperty("success")) {
			Message.showMessage(result.getMessage());
			Intent intent = new Intent(context.getApplicationContext(), HomePageActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        context.startActivity(intent);
	        ((Activity)context).finish();
	        SharedPreferencesUtils.putString(context, SystemConfig.USER_ID, String.valueOf(result.getUser_Id()));
	        SharedPreferencesUtils.putString(context, SystemConfig.SESSION_ID, String.valueOf(result.getSession_id()));
	        SystemConfig.user_id = String.valueOf(result.getUser_Id());
	        SystemConfig.session_id = result.getSession_id();
	        SharedPreferencesUtils.putBoolean(context, SystemConfig.CHECKLOGIN, chkRemember);
	        Debug.e("Check login :  " + chkRemember);
	        
		} else {
			Message.showMessage(result.getMessage());
		}
		super.onPostExecute(result);
		}catch (Exception e) {
			// TODO: handle exception
			Debug.e("Lôi cmnr: " + e.toString());
		}
	}

}
