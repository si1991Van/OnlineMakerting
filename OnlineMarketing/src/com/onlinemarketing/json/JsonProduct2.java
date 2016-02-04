package com.onlinemarketing.json;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.ProductVO;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonProduct2 {
	JSONObject jsonObject;
	StringBuilder request;

	public List<ProductVO> paserProduct(String user_id, String session_id, String device_id) {
		List<ProductVO> listProduct = new ArrayList<ProductVO>();
		try {
			request = new StringBuilder(SystemConfig.API);
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));

			Debug.e("Link Home: " + request.toString());
			String str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httpget);
			Debug.e("Str: " + str);
			jsonObject = new JSONObject(str);
//			 obj.setCode(jsonObject.getInt("code"));
//			 obj.setMessage(jsonObject.getString("message"));
//			 obj.setSession_id(jsonObject.getString("session_id"));
			JSONArray jsonProduct = jsonObject.getJSONArray("data");
			for (int i = 0; i < jsonProduct.length(); i++) {
				ProductVO arrProduct = new ProductVO();
				arrProduct.getJson(jsonProduct.getJSONObject(i));
				listProduct.add(arrProduct);
			}
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return listProduct;

	}
}
