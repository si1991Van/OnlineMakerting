package com.onlinemarketing.activity;

import java.io.IOException;

import com.onlinemarketing.config.Constan;
import com.smile.android.gsm.utils.AndroidUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;


public class BaseActivity extends Activity{

	Dialog objdealog;
	public boolean isConnect(){
		
		boolean isconnect = AndroidUtils.isConnectedToInternet(this);
		if (!isconnect) {
		
			showProgressDialogCheckInternet();
		}
		return isconnect;
	}
	public void showProgressDialogCheckInternet() {
		@SuppressWarnings("deprecation")
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this, AlertDialog.THEME_HOLO_LIGHT);
		try {
			alertDialog.setTitle(Constan.getProperty("ErrorConnectInterNet", getApplicationContext()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//ErrorConnectInterNet);
		
		try {
			alertDialog.setMessage(Constan.getProperty("ErrorConnectInterNetMessage",getApplicationContext()))
					.setCancelable(false).setPositiveButton(Constan.getProperty("Cancel",getApplicationContext()), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}

					}).setNegativeButton(Constan.getProperty("Ok",getApplicationContext()), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alertDialog.show();
	}

	
}
