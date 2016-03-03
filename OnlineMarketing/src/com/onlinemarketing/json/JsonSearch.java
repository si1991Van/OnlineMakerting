package com.onlinemarketing.json;

import java.net.URLEncoder;

import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.Output;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonSearch {

	JSONObject jsonObject;
	StringBuilder request;

	public Output paserSaveSearch(String user_id, String session_id, String device_id, String name, String lat,
			String log, String price_id, String category_id, String type_id, String time_id) {
		Output obj = new Output();
		String str = null;
		try {
			request = new StringBuilder(SystemConfig.API);
			request.append(SystemConfig.Produc_Save + "/1");
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
			request.append("&name=").append(URLEncoder.encode(name, "UTF-8"));
			
			request.append("&lat=").append(URLEncoder.encode(lat, "UTF-8"));
			request.append("&long=").append(URLEncoder.encode(log, "UTF-8"));
			request.append("&price_id=").append(URLEncoder.encode(price_id, "UTF-8"));
			request.append("&category_id=").append(URLEncoder.encode(category_id, "UTF-8"));
			request.append("&type_id=").append(URLEncoder.encode(type_id, "UTF-8"));
			request.append("&time_id=").append(URLEncoder.encode(time_id, "UTF-8"));
			
			Debug.e("link aaaaaaaaaaaaaaaa: " + request.toString());
			str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
			Debug.e("Str: " + str);
			jsonObject = new JSONObject(str);
			obj.setCode(jsonObject.getInt("code"));
			obj.setMessage(jsonObject.getString("message"));
			obj.setSession_id(jsonObject.getString("session_id"));
			obj.setUser_Id(jsonObject.getString("user_id"));
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return obj;
	}
}
