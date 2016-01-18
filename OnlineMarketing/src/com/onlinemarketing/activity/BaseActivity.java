package com.onlinemarketing.activity;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.TextView;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.smile.android.gsm.utils.AndroidUtils;


public class BaseActivity extends Activity{
	Dialog objdealog;
	public boolean isConnect(){
		
		boolean isconnect = AndroidUtils.isConnectedToInternet(this);
		if (!isconnect) {
			Debug.showAlert(this, "mat ket noi internet");
		}
		return isconnect;
	}
	public void alert(String text) {
		objdealog = new Dialog(this);
//		 objdealog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		objdealog.setContentView(R.layout.alert);
		TextView textView1 = (TextView) objdealog.findViewById(R.id.textView1);
		textView1.setText(text);
		objdealog.setTitle("abc");
//		 objdealog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
//		 R.drawable.success_icon_36x36);
		objdealog.show();

	}
	
}
