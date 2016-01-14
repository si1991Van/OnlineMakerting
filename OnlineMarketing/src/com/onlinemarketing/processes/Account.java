package com.onlinemarketing.processes;

import java.net.URLEncoder;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.AccountVO;
import com.onlinemarketing.object.OutputAccount;
import com.smile.android.gsm.utils.AndroidUtils;

public class Account {
	static AccountVO objAccount;
	static OutputAccount output;

	public OutputAccount Login(String user, String pass) {
		output = new OutputAccount();
		// check is null user and pass
		if (AndroidUtils.isNotNull(user) || AndroidUtils.isNotNull(pass)) {
			if (AndroidUtils.validate(user)) {
				// check call webservice
				processLogin(user, pass);
				return output;
			} else {
				output.setResult(98);
				output.setMessage(Constan.Error03);
				return output;
			}
		} else {
			output.setResult(99);
			output.setMessage(Constan.Error01);
			return output;
		}
	}

	/**
	 * @author tuanc_000
	 * @param email
	 * @param password
	 */
	private static void processLogin(String email, String password) {
		try {
			StringBuilder request = new StringBuilder(SystemConfig.apiLogin);
			request.append("User=").append(URLEncoder.encode(email, "UTF-8"));
			request.append("&Pass=").append(
					URLEncoder.encode(password, "UTF-8"));
			// call webservice
			String json = AndroidUtils.getjSonUrl(request.toString());
			
			JSONParser parser = new JSONParser();
			JSONObject objjson = (JSONObject) parser.parse(json);

			// set result Account on WS return
			output.setSession(objjson.get("sessionid").toString());
			output.setMessage(objjson.get("message").toString());
			output.setResult(Integer.parseInt(objjson.get("status").toString()));
		} catch (Exception e) {
			Debug.e(e.toString());
		}
	}

	private static void processRegiter(String email, String phone,
			String password) {
		try {
			// Set object to link WB
			StringBuilder request = new StringBuilder(SystemConfig.apiRegister);
			request.append("User=").append(URLEncoder.encode(email, "UTF-8"));
			request.append("&Phone=").append(URLEncoder.encode(phone, "UTF-8"));
			request.append("&Pass=").append(
					URLEncoder.encode(password, "UTF-8"));
			// Call WB Regiter
			String json = AndroidUtils.getjSonUrl(request.toString());
			JSONObject objjson = (JSONObject) new JSONParser().parse(json);
			output.setMessage(objjson.get("message").toString());
			output.setResult(Integer.parseInt(objjson.get("status").toString()));

		} catch (Exception e) {
			Debug.e(e.toString());
		}
	}

}
