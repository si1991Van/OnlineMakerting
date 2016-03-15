package com.onlinemarketing.json;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.BackListVO;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;
import com.smile.android.gsm.utils.AndroidUtils;

public class JsonProduct {
	JSONObject jsonObject;
	StringBuilder request;

	public OutputProduct paserProduct(String user_id, String session_id, String device_id, int id, int status) {
		OutputProduct obj = new OutputProduct();
		String str = null;
		// check email password

		try {
			request = new StringBuilder(SystemConfig.API);
			if (status == SystemConfig.statusCategoryProduct)
				request.append(SystemConfig.Category + "/" + id);
			if(status == SystemConfig.statusListSaveProduct)
				request.append(SystemConfig.Product_log );
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
			Debug.e("link : " + request.toString());
			if (status == SystemConfig.statusCategoryProduct || status == SystemConfig.statusListSaveProduct)
				str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
			else
				str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httpget);
			jsonObject = new JSONObject(str);
			obj.setCode(jsonObject.getInt("code"));
			obj.setMessage(jsonObject.getString("message"));
			obj.setSession_id(jsonObject.getString("session_id"));
			JSONArray jsonProduct = jsonObject.getJSONArray("data");
			if (obj.getCode() == Constan.getIntProperty("success")) {
				ArrayList<ProductVO> arrProduct = new ArrayList<ProductVO>();
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
					Debug.e("objproduct: " + objproduct.getAvatar());
				}
				obj.setProductVO(arrProduct);
			}

		} catch (Exception e) {
			Debug.e(e.toString());
		}

		return obj;

	}

	public Output paserNews(String user_id, String session_id, String device_id) {
		Output obj = new Output();
		String str = null;
		try {
			request = new StringBuilder(SystemConfig.API);
			request.append(SystemConfig.Produc_Save + "/1");
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
			Debug.e("link aaaaaaaaaaaaaaaa: " + request.toString());
			str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
			Debug.e("Str: " + str);
			jsonObject = new JSONObject(str);
			obj.setCode(jsonObject.getInt("code"));
			obj.setMessage(jsonObject.getString("message"));
			obj.setSession_id(jsonObject.getString("session_id"));
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return obj;
	}
	
	
	
	public Output paserErrorReport(String user_id, String session_id, String device_id, int id, String message) {
		Output obj = new Output();
		String str = null;
		try {
			request = new StringBuilder(SystemConfig.API);
			request.append(SystemConfig.Product + "/1" + "/"+SystemConfig.Report);
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
			request.append("&message=").append(URLEncoder.encode(message, "UTF-8"));
			
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
	
	
	public Output paserDeleteBackListAndFavorite(String user_id, String session_id, String device_id, int id, int status) {
		Output obj = new Output();
		String str = null;
		try {
			request = new StringBuilder(SystemConfig.API);
			if (status == SystemConfig.statusDeleteBackList) {
				request.append(SystemConfig.BackList + "/" + id + "/"+SystemConfig.Delete);
			}else if (status == SystemConfig.statusDeleteFavorite) {
				request.append(SystemConfig.Favorite + "/" + id + "/"+SystemConfig.Delete);
			}
			else if (status == SystemConfig.statusDeleteSearch) {
				request.append(SystemConfig.SearchLog + "/" + id + "/"+SystemConfig.Delete);
			}
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
			
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
	
	public OutputProduct paserBackList(String user_id, String session_id, String device_id) {
		OutputProduct obj = new OutputProduct();
		String str = null;
		try {
			request = new StringBuilder(SystemConfig.API);
			request.append(SystemConfig.BackList);   
			request.append("?user_id=").append(URLEncoder.encode(user_id, "UTF-8"));
			request.append("&session_id=").append(URLEncoder.encode(session_id, "UTF-8"));
			request.append("&device_id=").append(URLEncoder.encode(device_id, "UTF-8"));
			
			Debug.e("link aaaaaaaaaaaaaaaa: " + request.toString());
			str = AndroidUtils.getjSonUrl(request.toString(), SystemConfig.httppost);
			Debug.e("Str: " + str);  
			jsonObject = new JSONObject(str);
			obj.setCode(jsonObject.getInt("code"));
			obj.setMessage(jsonObject.getString("message"));
			obj.setSession_id(jsonObject.getString("session_id"));
			obj.setUser_Id(jsonObject.getString("user_id"));
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			if (obj.getCode() == Constan.getIntProperty("success")) { 
				ArrayList<BackListVO> arrBacklist = new ArrayList<BackListVO>();
				for (int i = 0; i < jsonArray.length(); i++) {  
					JSONObject objjson_product = jsonArray.getJSONObject(i);
					BackListVO objBackList = new BackListVO();
					objBackList.setId(objjson_product.getInt("id"));
					objBackList.setUsername(objjson_product.get("username").toString());
					objBackList.setAvatar(
							"http://192.168.3.150/images/products/avatar/" + objjson_product.get("avatar").toString());
					arrBacklist.add(objBackList);
					Debug.e("objproduct: " + objBackList.getAvatar());
				}
				obj.setBackListVO(arrBacklist);
			}
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return obj;
	}

}
