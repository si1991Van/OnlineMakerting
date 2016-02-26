package com.onlinemarketing.json;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProfileVO;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonProfile {
	JSONObject jsonObject;
	StringBuilder request;
	public OutputProduct paserProfile(String user_id, String session_id, String device_id) {
		OutputProduct obj = new OutputProduct();
		String str = null ;
		// check email password
		
				try {
					request = new StringBuilder(SystemConfig.API );
					request.append(SystemConfig.Profile);
					request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
					request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
					request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
					
					str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httpget);
					Debug.e("Str: "+str);
					jsonObject = new JSONObject(str);
					obj.setCode(jsonObject.getInt("code"));					
					obj.setMessage(jsonObject.getString("message"));
					obj.setSession_id(jsonObject.getString("session_id"));
					JSONArray jsonProfile = jsonObject.getJSONArray("data");
					if (obj.getCode() == Constan.getIntProperty("success")) {
						ProfileVO objProfile = new ProfileVO();
						for (int i = 0; i < jsonProfile.length(); i++) {
							JSONObject objjson_profile = jsonProfile.getJSONObject(i);
							objProfile.setId(objjson_profile.getInt("id"));
							objProfile.setFacebook_id(objjson_profile.getString("facebook_id"));
							objProfile.setGoogle_id(objjson_profile.get("google_id").toString());
							objProfile.setUsername(objjson_profile.get("username").toString());
							objProfile.setAvatar(objjson_profile.get("avatar").toString());
							objProfile.setEmail(objjson_profile.get("email").toString());
							objProfile.setPhone(objjson_profile.get("phone").toString());
							objProfile.setAddress(objjson_profile.get("address").toString());
							objProfile.setLat(objjson_profile.get("lat").toString());
							objProfile.setLongs(objjson_profile.get("long").toString());
							objProfile.setType(objjson_profile.get("type").toString());
							objProfile.setStatus(objjson_profile.get("status").toString());
							objProfile.setCreated_at(objjson_profile.get("created_at").toString());
						}
					obj.setProfileVO(objProfile);
					}
				} catch (Exception e) {
					Debug.e(e.toString());
				}
		return obj;

	}
	public Output postPaserProfile(String user_id, String session_id, String device_id, 
			ProfileVO profile) {
		Output obj = new Output();
		String str = null ;
		// check email password
		
				try {
					request = new StringBuilder(SystemConfig.API );
					request.append(SystemConfig.Profile);
					request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
					request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
					request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
					
					request.append("&username=").append(URLEncoder.encode(profile.getUsername(), "UTF-8"));
					request.append("&email=").append(profile.getEmail());
					request.append("&phone=").append(URLEncoder.encode(profile.getPhone(), "UTF-8"));
					request.append("&address=").append(URLEncoder.encode(profile.getAddress(), "UTF-8"));
					request.append("&password=").append(URLEncoder.encode(profile.getPass(), "UTF-8"));
					request.append("&old_password=").append(URLEncoder.encode(profile.getOld_pass(), "UTF-8"));
					request.append("&avatar=").append(URLEncoder.encode(profile.getAvatar(), "UTF-8"));
					Debug.e("link aaaaaaaaaaaaaaaa: "+ request.toString());
					str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
					Debug.e("Str: "+str);
					jsonObject = new JSONObject(str);
					obj.setCode(jsonObject.getInt("code"));					
					obj.setMessage(jsonObject.getString("message"));
					obj.setSession_id(jsonObject.getString("session_id"));
				} catch (Exception e) {
					Debug.e(e.toString());
				}
		return obj;

	} 
	
}
