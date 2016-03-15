/**
 * @author tuanc_000
 *
 */
package com.onlinemarketing.json;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.MessageVO;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputMessage;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProfileVO;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonMessage {
	JSONObject jsonObject;
	StringBuilder request;
	/**
	 * Show list message history
	 * @param user_id
	 * @param session_id
	 * @param device_id
	 * @return OutputProduct
	 */
	public OutputMessage paseListHistoryMessage(String user_id, String session_id, String device_id) {
		OutputMessage oOput = new OutputMessage();
		String str = null ;
		try{
			request = new StringBuilder(SystemConfig.API );
			request.append(SystemConfig.Message);
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
			str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
			jsonObject = new JSONObject(str);
			oOput.setCode(jsonObject.getInt("code"));					
			oOput.setMessage(jsonObject.getString("message"));
			oOput.setSession_id(jsonObject.getString("session_id"));
			oOput.setUser_Id(jsonObject.getString("user_id"));
			JSONObject objdata = jsonObject.getJSONObject("data");
			JSONArray jsonDatasend = objdata.getJSONArray("data_sent");
			JSONArray jsonDataRecived = objdata.getJSONArray("data_recived");
			if (oOput.getCode() == Constan.getIntProperty("success")) {
				ArrayList<MessageVO> arrayMessage = new ArrayList<MessageVO>();
				//data seand
				for (int i = 0; i < jsonDatasend.length(); i++) {
					JSONObject objjson_message = jsonDatasend.getJSONObject(i);
					MessageVO objmessage = new MessageVO();
					objmessage.setId(objjson_message.getInt("id"));
					objmessage.setReceiver_id(objjson_message.getInt("receiver_id"));
					objmessage.setMessage(objjson_message.getString("message"));
					objmessage.setCreate_at(objjson_message.getString("created_at"));
					objmessage.setUsername(objjson_message.getString("username"));
					objmessage.setAvatar(objjson_message.getString("avatar"));
					arrayMessage.add(objmessage);
				}
				//data recived
				for (int i = 0; i < jsonDataRecived.length(); i++) {
					JSONObject objjson_message = jsonDataRecived.getJSONObject(i);
					MessageVO objmessage = new MessageVO();
					objmessage.setId(objjson_message.getInt("id"));
					objmessage.setReceiver_id(objjson_message.getInt("sent_id"));
					objmessage.setMessage(objjson_message.getString("message"));
					objmessage.setCreate_at(objjson_message.getString("created_at"));
					objmessage.setUsername(objjson_message.getString("username"));
					objmessage.setAvatar(objjson_message.getString("avatar"));
					arrayMessage.add(objmessage);
				}
				oOput.setArrMessage(arrayMessage);
			}
		}catch (Exception e) {
			Debug.e(e.toString());
		}
		return oOput;
	}
	public OutputMessage SendMessage(String user_id, String session_id, String device_id, int iduser, String message) {
		OutputMessage output = new OutputMessage();
		try{
		String str;
		request = new StringBuilder(SystemConfig.API);
		request.append(SystemConfig.Message);
		request.append("/"+iduser+"/"+SystemConfig.SendMessage);
		request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
		request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
		request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
		request.append("&message=").append(URLEncoder.encode(message, "UTF-8"));
		Debug.e("Link : " + request.toString());
		str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
		jsonObject = new JSONObject(str);
		output.setCode(jsonObject.getInt("code"));					
		output.setMessage(jsonObject.getString("message"));
		output.setSession_id(jsonObject.getString("session_id"));
		output.setUser_Id(jsonObject.getString("user_id"));
		
		}catch (Exception e) {
			Debug.e(e.toString());
		}
		return output;
	}
}
