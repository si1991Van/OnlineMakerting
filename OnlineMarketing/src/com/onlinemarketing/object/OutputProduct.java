package com.onlinemarketing.object;

import java.util.ArrayList;

public class OutputProduct {
	private int code, user_Id;
	private String message, session_id;
	public ArrayList<ProductVO> productVO;
	private ArrayList<CategoryVO> categoryVO;
	private ArrayList<SettingVO> settingVO;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public ArrayList<ProductVO> getProductVO() {
		return productVO;
	}
	public void setProductVO(ArrayList<ProductVO> productVO) {
		this.productVO = productVO;
	}
	public ArrayList<CategoryVO> getCategoryVO() {
		return categoryVO;
	}
	public void setCategoryVO(ArrayList<CategoryVO> categoryVO) {
		this.categoryVO = categoryVO;
	}
	public ArrayList<SettingVO> getSettingVO() {
		return settingVO;
	}
	public void setSettingVO(ArrayList<SettingVO> settingVO) {
		this.settingVO = settingVO;
	}
	
	
	
}
