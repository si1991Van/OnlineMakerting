package com.onlinemarketing.json;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.CategoryVO;
import com.onlinemarketing.object.LoginRegister;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonCategory {
	JSONObject jsonObject;
	StringBuilder request;

	public OutputProduct paserCategory() {
		OutputProduct list_category = new OutputProduct();
		try {
			request = new StringBuilder(SystemConfig.API);
			request.append(SystemConfig.Category);
			request.append("?user_id=")
					.append(URLEncoder.encode(
							"", "UTF-8"));
			request.append("&session_id=").append(
					URLEncoder.encode("", "UTF-8"));
			request.append("&device_id=").append(
					URLEncoder.encode("", "UTF-8"));

			String str = AndroidUtils.getjSonUrl(request.toString(),
					SystemConfig.httppost);
			jsonObject = new JSONObject(str);
			list_category.setCode(jsonObject.getInt("code"));
			list_category.setMessage(jsonObject.getString("message"));
			list_category.setSession_id(jsonObject.getString("session_id"));
			list_category.setUser_Id(jsonObject.getString("user_id"));
			JSONArray jsonProduct = jsonObject.getJSONArray("data");
			if (list_category.getCode() == Constan.getIntProperty("success")) {
				ArrayList<CategoryVO> arrCategory = new ArrayList<CategoryVO>();
				for (int i = 0; i < jsonProduct.length(); i++) {
					CategoryVO categoryvo = new CategoryVO();
					JSONObject objjson_category = jsonProduct.getJSONObject(i);
					categoryvo.setId(objjson_category.getInt("id"));
					categoryvo.setName(objjson_category.getString("name"));
					arrCategory.add(categoryvo);
				}
				list_category.setCategoryVO(arrCategory);
			}
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return list_category;

	}
}
