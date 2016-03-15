package com.onlinemarketing.json;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProfileVO;
import com.onlinemarketing.util.Util;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonProfile {
	JSONObject jsonObject;
	StringBuilder request;
	public OutputProduct paserProfile(String user_id, String session_id, String device_id, int status) {
		OutputProduct obj = new OutputProduct();
		String str = null ;
		// check email password
		
				try {
					request = new StringBuilder(SystemConfig.API );
					if (status == SystemConfig.statusProfile) {
						request.append(SystemConfig.Profile);
					}else if (status == SystemConfig.statusFavorite) {
						request.append(SystemConfig.Favorite);
					}
					request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
					request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
					request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
					Debug.e("Link : " + request.toString());
					if (status == SystemConfig.statusProfile) {
						str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httpget);
					}else if (status == SystemConfig.statusFavorite) {
						str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
					}
					Debug.e("Str: "+str);
					jsonObject = new JSONObject(str);
					obj.setCode(jsonObject.getInt("code"));					
					obj.setMessage(jsonObject.getString("message"));
					obj.setSession_id(jsonObject.getString("session_id"));
					JSONArray jsonProfile = jsonObject.getJSONArray("data");
					if (obj.getCode() == Constan.getIntProperty("success")) {
						ArrayList<ProfileVO> arrayProfile = new ArrayList<ProfileVO>();
						for (int i = 0; i < jsonProfile.length(); i++) {
							JSONObject objjson_profile = jsonProfile.getJSONObject(i);
							ProfileVO objProfile = new ProfileVO();
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
							arrayProfile.add(objProfile);
						}
					obj.setProfileVO(arrayProfile);
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
	
	public Output doFileUpload(String user_id, String session_id, String device_id, String link) {
		Output output = new Output();
		output = Util.doFileUpload(user_id, session_id, device_id, link);
		return output;
	}
}
