package com.onlinemarketing.json;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.CategoryVO;
import com.onlinemarketing.object.LoginRegister;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;
import com.onlinemarketing.object.SettingVO;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonProduct {
	JSONObject jsonObject;
	StringBuilder request;

	public OutputProduct paserProduct(String user_id, String session_id, String device_id, int indext) {
		OutputProduct obj = new OutputProduct();
		// check email password
		
				try {
					request = new StringBuilder(SystemConfig.API );
					request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
					request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
					request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));

					Debug.e("Link Home: " + request.toString());
					String str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
					Debug.e("Str: " + str);
					jsonObject = new JSONObject(str);
					obj.setCode(jsonObject.getInt("code"));					
					obj.setMessage(jsonObject.getString("message"));
					obj.setSession_id(jsonObject.getString("session_id"));
					JSONObject jsonData = jsonObject.getJSONObject("data");
					if (obj.getCode() == Constan.getIntProperty("success")) {
						// set product
						JSONArray jsonProduct = jsonData.getJSONArray("product");
						ArrayList<ProductVO> arrProduct = new ArrayList<ProductVO>();
						ArrayList<SettingVO> arrSetting = new ArrayList<SettingVO>();
						ArrayList<CategoryVO> arrCategory = new ArrayList<CategoryVO>();
						for (int i = 0; i < jsonProduct.length(); i++) {
							JSONObject objjson_product = jsonProduct.getJSONObject(i);
							ProductVO objproduct = new ProductVO();
							objproduct.setId(objjson_product.getInt("id"));
							objproduct.setName(objjson_product.get("name").toString());
							objproduct.setAvatar(objjson_product.get("avatar").toString());
							objproduct.setPrice(objjson_product.get("price").toString());
							objproduct.setPrice_id(objjson_product.getInt("price_id"));
							objproduct.setCategory_id(objjson_product.getInt("category_id"));
							objproduct.setUser_id(objjson_product.getInt("user_id"));
							objproduct.setType_id(objjson_product.getInt("type_id"));
							objproduct.setCity_id(objjson_product.getInt("city_id"));
							objproduct.setStartdate(objjson_product.get("start_time").toString());
							objproduct.setStatus(objjson_product.getInt("status"));
							objproduct.setPosition(objjson_product.getInt("position"));
							objproduct.setDelete_at(objjson_product.get("deleted_at").toString());
							objproduct.setCreate_at(objjson_product.get("created_at").toString());
							arrProduct.add(objproduct);
						}
						//set category
						JSONArray objcategory = jsonData.getJSONArray("category");
						for (int i = 0; i < objcategory.length(); i++) {
							JSONObject objjson_category = objcategory.getJSONObject(i);
							CategoryVO objCategory = new CategoryVO();
							objCategory.setId(objjson_category.getInt("id"));
							objCategory.setName(objjson_category.get("name").toString());
							arrCategory.add(objCategory);
						}
						//set setting
						JSONArray objsetting = jsonData.getJSONArray("setting");
						for (int i = 0; i < objsetting.length(); i++) {
							JSONObject objjson_setting = objsetting.getJSONObject(i);
							SettingVO objSetting = new SettingVO();
							objSetting.setName(objjson_setting.get("name").toString());
							objSetting.setLink(objjson_setting.get("link").toString());
							objSetting.setMethod(objjson_setting.get("method").toString());
							objSetting.setQuantily(objjson_setting.get("quantity").toString());
						}
						obj.setProductVO(arrProduct);
						obj.setCategoryVO(arrCategory);
						obj.setSettingVO(arrSetting);
					}
					
				} catch (Exception e) {
					Debug.e(e.toString());
				}
			
		
		return obj;

	}
}
