package com.onlinemarketing.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlinemarketing.R;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.object.OutputAccount;
import com.onlinemarketing.processes.Account;

public class LoginActivity extends BaseActivity implements OnClickListener {

	EditText txtusername, txtpass;
	Button btnlogin;
	boolean loginStatus;
	OutputAccount Ooput;
	Dialog objdealog;

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
			new LoginAsystask().execute(1);
			break;
		case R.id.btnRegister:
			new LoginAsystask().execute(2);
			break;

		default:
			break;
		}
	}

	public void alert(String text) {
		objdealog = new Dialog(this);
		objdealog.setContentView(R.layout.alert);
		TextView textView1 = (TextView) objdealog.findViewById(R.id.textView1);
		textView1.setText(text);
		objdealog.setTitle("Thông báo");
		objdealog.show();

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
				Ooput = json.Login(txtusername.getText().toString(), txtpass
						.getText().toString());
				break;

			case 2:

				break;
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			switch (Ooput.getResult()) {
			case 0:
				Intent intObj = new Intent(LoginActivity.this,
						HomeActivity.class);
				startActivity(intObj);
				break;
			case 1:
				alert(Ooput.getMessage());
				break;
			}
		}

	}

}
