package com.onlinemarketing.activity;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.object.OutputAccount;
import com.onlinemarketing.processes.Account;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements OnClickListener {

	EditText txtusername, txtpass;
	Button btnlogin;
	boolean loginStatus;
	OutputAccount Ooput;
	Dialog objdealog;
	AlertDialog.Builder mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtusername = (EditText) findViewById(R.id.txtusername);
		txtpass = (EditText) findViewById(R.id.txtpassword);
		btnlogin = (Button) findViewById(R.id.btnlogin);
		btnlogin.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnlogin:
			if (isConnect()) {
				new LoginAsystask().execute(1);
			}
			break;
		case R.id.btnRegister:
			if (isConnect()) {
				new LoginAsystask().execute(2);
			}
			break;

		default:
			break;
		}
	}


	public class LoginAsystask extends AsyncTask<Integer, Void, Void> {
		Account json;

		@Override
		protected void onPreExecute() { 
			json = new Account();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Integer... params) {
			switch (params[0]) {
			case 1:
				Ooput = new OutputAccount();

				Ooput = json.Login(txtusername.getText().toString().trim(), txtpass.getText().toString(), LoginActivity.this);
				break;

			case 2:

				break;
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			try{
			if (Ooput.getResult() == Integer.parseInt(Constan.getProperty("loginSuccess", getApplicationContext()))){
				Intent intObj = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intObj);
			}else{
				mProgressDialog = new AlertDialog.Builder(LoginActivity.this, AlertDialog.THEME_HOLO_LIGHT);
				mProgressDialog.setTitle("Thông Báo");
				mProgressDialog.setMessage(Ooput.getMessage());
				mProgressDialog.show();
			}
			} catch (Exception e) {
				Debug.e(e.toString());
			}
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
