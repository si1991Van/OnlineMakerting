package com.onlinemarketing.activity;

import com.example.onlinemarketing.R;
import com.onlinemarketing.asystask.LoginRegisterAsystask;
import com.onlinemarketing.object.OutputAccount;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements OnClickListener {

	EditText txtusername, txtpass;
	Button btnlogin, btnRegister;
	boolean loginStatus;
	OutputAccount Ooput;
	Dialog objdealog;
	AlertDialog.Builder mProgressDialog;
	LoginRegisterAsystask account;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtusername = (EditText) findViewById(R.id.txtusername);
		txtpass = (EditText) findViewById(R.id.txtpassword);
		btnlogin = (Button) findViewById(R.id.btnlogin);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnlogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		account = new LoginRegisterAsystask(txtusername.getText().toString().trim(), txtpass.getText().toString().trim(), "987710727ACFAB0C0B00E82DEFAD0085");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnlogin:
			new LoginRegisterAsystask(txtusername.getText().toString().trim(), txtpass.getText().toString().trim(), "987710727ACFAB0C0B00E82DEFAD0085").execute(1);
			break;
		case R.id.btnRegister:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			break;

		default:
			break;
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
