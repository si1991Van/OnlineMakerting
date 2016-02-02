package com.onlinemarketing.json;

import java.net.URLEncoder;

import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.LoginRegister;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonAccount {

	JSONObject jsonObject;
	StringBuilder request;

	public LoginRegister paserRegister(String email, String pass, String device_id, int indext) {
		LoginRegister obj = new LoginRegister();
		try {
			if (indext == SystemConfig.statusRegister) {
				request = new StringBuilder(SystemConfig.API + SystemConfig.Register);
			} else if (indext == SystemConfig.statusLogin) {
				request = new StringBuilder(SystemConfig.API + SystemConfig.Login);
			}
			request.append("email=").append(email);
			request.append("&password=").append(URLEncoder.encode(pass, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));

			Debug.e("Link: " + request.toString());
			String str = AndroidUtils.getjSonUrl(request.toString(), 2);
			Debug.e("Str: " + str);
			jsonObject = new JSONObject(str);
			obj.setCode(Integer.parseInt(jsonObject.getString("code")));
			obj.setMessage(jsonObject.getString("message"));
			if (obj.getCode() == 200) {
				obj.setUser_Id(Integer.parseInt(jsonObject.getString("user_id")));
				obj.setSession_id(jsonObject.getString("session_id"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;

	}
}
