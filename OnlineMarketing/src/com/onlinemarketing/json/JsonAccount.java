package com.onlinemarketing.json;

import java.net.URLEncoder;

import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.LoginRegister;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonAccount {

	JSONObject jsonObject;
	StringBuilder request;

	public LoginRegister paserRegister(String email, String pass, String device_id,String user_id, String session_id, int indext) {
		LoginRegister obj = new LoginRegister();
		// check email password
		if(AndroidUtils.isNotNull(email) && AndroidUtils.isNotNull(pass)){
			if(AndroidUtils.validate(email)){
				try {
					if (indext == Constan.getIntProperty("statusRegister")) {
						request = new StringBuilder(SystemConfig.API + SystemConfig.Register);
					} else if (indext == Constan.getIntProperty("statusLogin")) {
						request = new StringBuilder(SystemConfig.API + SystemConfig.Login);
					}
					request.append("email=").append(email);
					request.append("&password=").append(URLEncoder.encode(pass, "UTF-8"));
					request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
					
					request.append("&user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
					request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
					Debug.e("Link: " + request.toString());
					String str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
					Debug.e("Str: " + str);
					jsonObject = new JSONObject(str);
					obj.setCode(Integer.parseInt(jsonObject.getString("code")));
					obj.setMessage(jsonObject.getString("message"));
					if (obj.getCode() == Constan.getIntProperty("success")) {
						obj.setUser_Id(Integer.parseInt(jsonObject.getString("user_id")));
						obj.setSession_id(jsonObject.getString("session_id"));
					}
					
				} catch (Exception e) {
					Debug.e(e.toString());
				}
			}else {
				obj.setCode(Constan.getIntProperty("loginerror"));
				obj.setMessage(Constan.getProperty("Error02"));
			}
		}else {
			obj.setCode(Constan.getIntProperty("loginerror"));
			obj.setMessage(Constan.getProperty("Error01"));
		}
		
		return obj;

	}
}
