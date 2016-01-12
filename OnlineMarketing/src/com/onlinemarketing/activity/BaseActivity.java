package com.onlinemarketing.activity;

import com.lib.Debug;
import com.smile.android.gsm.utils.AndroidUtils;

import android.app.Activity;


public class BaseActivity extends Activity{

	public boolean isConnect(){
		
		boolean isconnect = AndroidUtils.isConnectedToInternet(this);
		if (!isconnect) {
			Debug.showAlert(this, "mat ket noi internet");
		}
		return isconnect;
		
	}
}
