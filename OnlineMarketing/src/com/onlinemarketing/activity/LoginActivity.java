package com.onlinemarketing.activity;

import com.example.onlinemarketing.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.model.people.Person;
import com.lib.Debug;
import com.onlinemarketing.asystask.LoginRegisterAsystask;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.object.OutputAccount;
import com.smile.android.gsm.utils.AndroidDeviceInfo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements OnClickListener {

	EditText txtusername, txtpass;
	Button btnlogin, btnRegister;
	boolean loginStatus;
	OutputAccount Ooput;
	Dialog objdealog;
	AlertDialog.Builder mProgressDialog;
	LoginRegisterAsystask account;
	//google
	SignInButton btngoogle;
	private PlusClient mPlusClient;  
	private int REQUEST_CODE_RESOLVE_ERR=301;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtusername = (EditText) findViewById(R.id.txtusername);
		txtpass = (EditText) findViewById(R.id.txtpassword);
		btnlogin = (Button) findViewById(R.id.btnlogin);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btngoogle = (SignInButton) findViewById(R.id.googlebtn);
		btnlogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		account = new LoginRegisterAsystask(txtusername.getText().toString().trim(), txtpass.getText().toString().trim(), SystemConfig.device_id);
		
		//process login google
		btngoogle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!mPlusClient.isConnected()) {

					mPlusClient.connect();
					showMsg("signed in google+");					

				} 
				else if (mPlusClient.isConnected()) {
					{
						mPlusClient.clearDefaultAccount();
						mPlusClient.disconnect();
						showMsg("signed  out of google+");

					}
				}
			}
		});
		
		mPlusClient=new PlusClient.Builder(getApplicationContext(), new ConnectionCallbacks() {
			
			@Override
			public void onDisconnected() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onConnected(Bundle arg0) {
				// TODO Auto-generated method stub
				Person person=mPlusClient.getCurrentPerson();	
			    showMsg(person.getDisplayName());
			    showMsg(person.getId());
			    showMsg(person.getGender()+"");
			}
		}, new OnConnectionFailedListener() {
			
			@Override
			public void onConnectionFailed(ConnectionResult result) {
				// TODO Auto-generated method stub
				if (result.hasResolution()) {
					   
		            try {
		                result.startResolutionForResult(LoginActivity.this, REQUEST_CODE_RESOLVE_ERR);
		            } catch (SendIntentException e) {
		                mPlusClient.disconnect();
		                mPlusClient.connect();
		            }
		        }
			}
		}).build();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnlogin:
			new LoginRegisterAsystask(txtusername.getText().toString().trim(), txtpass.getText().toString().trim(), SystemConfig.device_id).execute(1);
			break;
		case R.id.btnRegister:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			break;
		case R.id.btnSkip:
			startActivity(new Intent(LoginActivity.this, HomeActivity.class));
			break;
		
		default:
			break;
		}
	}

	void showMsg(String string)
	{
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
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
