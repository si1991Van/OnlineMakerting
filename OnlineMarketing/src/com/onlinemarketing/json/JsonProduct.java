package com.onlinemarketing.json;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.LoginRegister;
import com.onlinemarketing.object.OutputProduct;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonProduct {
	JSONObject jsonObject;
	StringBuilder request;

	public OutputProduct paserProduct(String user_id, String session_id, String device_id, int indext) {
		OutputProduct obj = new OutputProduct();
		// check email password
		
				try {
					
					request.append("user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
					request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
					request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));

					Debug.e("Link Home: " + request.toString());
					String str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
					Debug.e("Str: " + str);
					jsonObject = new JSONObject(str);
					obj.setCode(Integer.parseInt(jsonObject.getString("code")));					
					obj.setMessage(jsonObject.getString("message"));
					obj.setSession_id(jsonObject.getString("session_id"));
					if (obj.getCode() == Constan.getIntProperty("success")) {
						JSONArray data = jsonObject.getJSONArray("data");
						//JSONArray product = data.getJSONArray("ss");
						
					}
					
				} catch (Exception e) {
					Debug.e(e.toString());
				}
			
		
		return obj;

	}
}
