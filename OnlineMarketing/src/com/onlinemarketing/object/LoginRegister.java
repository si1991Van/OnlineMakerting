package com.onlinemarketing.object;

public class LoginRegister {

	private int code, user_Id;
	private String message, session_id;
	public LoginRegister(int code, int user_Id, String message, String session_id) {
		super();
		this.code = code;
		this.user_Id = user_Id;
		this.message = message;
		this.session_id = session_id;
	}
	
	public LoginRegister() {
		super();
	}

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
	
	
	
}
