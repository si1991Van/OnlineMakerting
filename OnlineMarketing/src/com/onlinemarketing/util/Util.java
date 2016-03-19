package com.onlinemarketing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

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
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.Output;

public class Util {
	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	/**
	 * Hàm trả về JSONObject
	 * 
	 * @param url
	 *            - Truyền link URL có định dạng JSON
	 * @return - Trả về JSONOBject
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		try {
			// đọc nội dung với Unicode:
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			Debug.e("jsonText" + jsonText);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	public static Output doFileUpload(String user_id, String session_id, String device_id, String link) {
		Output output = new Output();
		File file_path = new File(link);
		try {
			StringBuilder request = new StringBuilder(SystemConfig.API );
			request.append(SystemConfig.Upload_image);
			HttpClient client = new DefaultHttpClient();
			// use your server path of php file
			HttpPost post = new HttpPost(request.toString());


			FileBody bin1 = new FileBody(file_path);
			Log.e("Enter", "Filebody complete " + bin1);

			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("image_url[]", bin1);
			reqEntity.addPart("user_id", new StringBody(user_id));
			reqEntity.addPart("device_id", new StringBody(device_id));
			reqEntity.addPart("session_id", new StringBody(session_id));
			post.setEntity(reqEntity);
			HttpResponse response = client.execute(post);
			HttpEntity resEntity ;
			resEntity = response.getEntity();
			Log.e("user_id", "user_id:"+ user_id+ "/n device_id: "+ device_id+ "/nsession_id:"+session_id );
			Log.e("Enter", "Get Response");
			try {
				String response_str = EntityUtils.toString(resEntity);
				Log.e( "Get Response",response_str);
				if (resEntity != null) {
					JSONObject jsonObject = new JSONObject(response_str);
					output.setCode(jsonObject.getInt("code"));					
					output.setMessage(jsonObject.getString("message"));
					output.setSession_id(jsonObject.getString("session_id"));
					JSONArray jsonArrAvatar = jsonObject.getJSONArray("data");
					if (output.getCode() == Constan.getIntProperty("success")) {
						for (int i = 0; i < jsonArrAvatar.length(); i++) {
							JSONObject objjsonAvatar = jsonArrAvatar.getJSONObject(i);
							if(objjsonAvatar.has("image_url"))
								SystemConfig.Avatar =  objjsonAvatar.getString("image_url");
							
						}
					}
				}
			} catch (Exception ex) {
				Log.e("Debug", "error: " + ex.getMessage(), ex);
			}
		} catch (Exception e) {
			Log.e("Upload Exception", "");
		}
		return output;
	}
	public static Output doFileUploadArr(String user_id, String session_id, String device_id, String link) {
		Output output = new Output();
		File file_path = new File(link);
//		File[] file = new File
//		for (String string : link) {
//			
//		}
		try {
			StringBuilder request = new StringBuilder(SystemConfig.API );
			request.append(SystemConfig.Upload_image);
			HttpClient client = new DefaultHttpClient();
			// use your server path of php file
			HttpPost post = new HttpPost(request.toString());


			FileBody bin1 = new FileBody(file_path);
			Log.e("Enter", "Filebody complete " + bin1);

			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("image_url[]", bin1);
			reqEntity.addPart("user_id", new StringBody(user_id));
			reqEntity.addPart("device_id", new StringBody(device_id));
			reqEntity.addPart("session_id", new StringBody(session_id));
			post.setEntity(reqEntity);
			HttpResponse response = client.execute(post);
			HttpEntity resEntity ;
			resEntity = response.getEntity();
			Log.e("user_id", "user_id:"+ user_id+ "/n device_id: "+ device_id+ "/nsession_id:"+session_id );
			Log.e("Enter", "Get Response");
			try {
				String response_str = EntityUtils.toString(resEntity);
				Log.e( "Get Response",response_str);
				if (resEntity != null) {
					JSONObject jsonObject = new JSONObject(response_str);
					output.setCode(jsonObject.getInt("code"));					
					output.setMessage(jsonObject.getString("message"));
					output.setSession_id(jsonObject.getString("session_id"));
					JSONArray jsonArrAvatar = jsonObject.getJSONArray("data");
					if (output.getCode() == Constan.getIntProperty("success")) {
						for (int i = 0; i < jsonArrAvatar.length(); i++) {
							JSONObject objjsonAvatar = jsonArrAvatar.getJSONObject(i);
							if(objjsonAvatar.has("image_url"))
								SystemConfig.Avatar =  objjsonAvatar.getString("image_url");
							
						}
					}
				}
			} catch (Exception ex) {
				Log.e("Debug", "error: " + ex.getMessage(), ex);
			}
		} catch (Exception e) {
			Log.e("Upload Exception", "");
		}
		return output;
	}
}
