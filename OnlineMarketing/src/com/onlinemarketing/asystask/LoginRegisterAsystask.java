package com.onlinemarketing.asystask;

import android.os.AsyncTask;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonAccount;
import com.onlinemarketing.object.LoginRegister;
import com.onlinemarketing.util.Message;
import com.lib.Debug;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonAccount;
import com.onlinemarketing.object.LoginRegister;

import android.os.AsyncTask;

public class LoginRegisterAsystask extends AsyncTask<Integer, String, LoginRegister> {

	JsonAccount json;
	LoginRegister account;
	String Email, Pass, Device_id;

	public LoginRegisterAsystask(String email, String pass, String device_id) {
		super();
		Email = email;
		Pass = pass;
		Device_id = device_id;
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
			account = json.paserRegister(Email, Pass, Device_id, SystemConfig.statusLogin);
			Debug.e("Email: " + Email + "\nPass: " + Pass + "\nDevice_id: " + Device_id);
			break;
		case SystemConfig.statusRegister:
			account = json.paserRegister(Email, Pass, Device_id, SystemConfig.statusRegister);
			break;
		}
		return account;

	}

	@Override
	protected void onPostExecute(LoginRegister result) {
		Debug.e("message: " + result.getMessage());
		if(result.getCode() == Constan.getIntProperty("success")){
			Message.showMessage(result.getMessage());
		}else {
			Message.showMessage(result.getMessage());
		}
		super.onPostExecute(result);
	}

}
