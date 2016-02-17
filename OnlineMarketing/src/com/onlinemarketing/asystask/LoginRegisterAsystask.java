package com.onlinemarketing.asystask;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonAccount;
import com.onlinemarketing.object.LoginRegister;
import com.onlinemarketing.util.Message;

import android.os.AsyncTask;

public class LoginRegisterAsystask extends AsyncTask<Integer, String, LoginRegister> {

	JsonAccount json;
	LoginRegister account;

	public LoginRegisterAsystask(String email, String pass, String device_id, String user_id, String session_id) {
		super();
		SystemConfig.Email = email;
		SystemConfig.Pass = pass;
		SystemConfig.device_id = device_id;
		SystemConfig.user_id = user_id;
		SystemConfig.session_id = session_id;
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
			account = json.paserRegister(SystemConfig.Email, SystemConfig.Pass, SystemConfig.device_id, SystemConfig.user_id, SystemConfig.session_id ,SystemConfig.statusLogin);
			break;
		case SystemConfig.statusRegister:
			account = json.paserRegister(SystemConfig.Email, SystemConfig.Pass, SystemConfig.device_id , SystemConfig.user_id, SystemConfig.session_id, SystemConfig.statusRegister);
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
