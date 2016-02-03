package com.onlinemarketing.object;

public class OutputProduct {
	private int code, user_Id;
	private String message, session_id;
	private ProductVO[] productVO;
	private CategoryVO[] categoryVO;
	private SettingVO[] settingVO;
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
	public ProductVO[] getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO[] productVO) {
		this.productVO = productVO;
	}
	public CategoryVO[] getCategoryVO() {
		return categoryVO;
	}
	public void setCategoryVO(CategoryVO[] categoryVO) {
		this.categoryVO = categoryVO;
	}
	public SettingVO[] getSettingVO() {
		return settingVO;
	}
	public void setSettingVO(SettingVO[] settingVO) {
		this.settingVO = settingVO;
	}
	
	
}
