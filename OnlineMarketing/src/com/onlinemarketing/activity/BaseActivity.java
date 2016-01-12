package com.onlinemarketing.activity;

import android.app.Activity;

import com.lib.Debug;
import com.smile.android.gsm.utils.AndroidUtils;

public class BaseActivity extends Activity{

	public boolean isConnect(){
		
		boolean isconnect = AndroidUtils.isConnectedToInternet(this);
		if (!isconnect) {
			Debug.showAlert(this, "mat ket noi internet");
		}
		return isconnect;
		
	}
}
