package com.onlinemarketing.object;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductVO {
	private int id;
	private String name;
	private String avatar;
	private String price;
	private int price_id;
	private int category_id;
	private int user_id;
	private int type_id;
	private int city_id;
	private String startdate;
	private int status;
	private int position;
	private String delete_at;
	private String create_at;

	public void getJson(JSONObject object) {
		try {
			id = object.getInt("id");
			name = object.get("name").toString();
			avatar = "http://192.168.3.151/images/products/avatar/"+object.get("avatar").toString();
			price = object.get("price").toString();
			price_id = object.getInt("price_id");
			category_id = object.getInt("category_id");
			user_id = object.getInt("user_id");
			type_id = object.getInt("type_id");
			city_id = object.getInt("city_id");
			startdate = object.get("start_time").toString();
			status = object.getInt("status");
			position = object.getInt("position");
			delete_at = object.get("deleted_at").toString();
			create_at = object.get("created_at").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPrice_id() {
		return price_id;
	}

	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getDelete_at() {
		return delete_at;
	}

	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}

	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}

}
