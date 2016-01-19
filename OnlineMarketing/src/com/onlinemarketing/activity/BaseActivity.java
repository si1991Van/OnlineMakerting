package com.onlinemarketing.activity;

import com.lib.Debug;
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
//			Debug.showAlert(this, "mat ket noi internet");
			showProgressDialogCheckInternet();
		}
		return isconnect;
	}
	public void showProgressDialogCheckInternet() {
		@SuppressWarnings("deprecation")
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(BaseActivity.this, AlertDialog.THEME_HOLO_LIGHT);
		alertDialog.setTitle(Constan.ErrorConnectInterNet);
		alertDialog.setMessage(Constan.ErrorConnectInterNetMessage)
				.setCancelable(false).setPositiveButton(Constan.Cancel, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				}).setNegativeButton(Constan.Ok, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
		alertDialog.show();
	}

	
}
