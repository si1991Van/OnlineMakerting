package com.onlinemarketing.activity;

import com.example.onlinemarketing.R;
import com.onlinemarketing.asystask.LoginRegisterAsystask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener{

	EditText txtusername, txtpass;
	Button register;
	LoginRegisterAsystask account;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		txtusername = (EditText) findViewById(R.id.txtusername);
		txtpass = (EditText) findViewById(R.id.txtpassword);
		register = (Button) findViewById(R.id.btnRegister);
		register.setOnClickListener(this);
		account = new LoginRegisterAsystask(txtusername.getText().toString(), txtpass.getText().toString(), "987710727ACFAB0C0B00E82DEFAD0085");
		
	}

	@Override
	public void onClick(View v) {
		new LoginRegisterAsystask(txtusername.getText().toString(), txtpass.getText().toString(), "987710727ACFAB0C0B00E82DEFAD0085").execute(2);
	}

	

	
}
