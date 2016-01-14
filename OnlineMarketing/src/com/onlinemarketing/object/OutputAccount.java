package com.onlinemarketing.object;

import java.util.List;

/**
 * @author tuanc_000
 * 	@category Output result object (code, message, Object) 
 */
public class OutputAccount {
	private int result;
	private String message;
	private String session;
	private AccountVO AccountVO;
	public int getResult() {
		return result;
	}
	public AccountVO getAccountVO() {
		return AccountVO;
	}
	public void setAccountVO(AccountVO accountVO) {
		AccountVO = accountVO;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}

	public OutputAccount() {
		super();
	}
	
}
