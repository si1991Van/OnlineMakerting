package com.onlinemarketing.processes;

import com.onlinemarketing.config.Constan;
import com.smile.android.gsm.utils.AndroidUtils;

public class Account {
	private String Login(String user, String pass) {
		String result="";
		//check is null user and pass
		if(!AndroidUtils.isNotNull(user) || !AndroidUtils.isNotNull(pass)){
			if (AndroidUtils.validate(user)) {
//				check tài khoản
			}else {
				return Constan.Error02;
			}
			return Constan.Error01;
		}
		return result;
	}
	
	
	
}
