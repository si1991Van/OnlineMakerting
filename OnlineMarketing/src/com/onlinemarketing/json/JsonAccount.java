package com.onlinemarketing.json;

import com.lib.Debug;
import com.onlinemarketing.config.SystemConfig;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonAccount {

	public void Login(String user, String pass) {
		try {
			String json = AndroidUtils.getjSonUrl(SystemConfig.apiLogin
												+ "User = "+user + "&"
												+ "Pass = "+pass);
		} catch (Exception e) {
			Debug.e(e.toString());
		}
	}
}
