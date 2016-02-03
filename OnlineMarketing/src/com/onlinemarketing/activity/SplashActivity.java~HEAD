package com.onlinemarketing.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.smile.android.gsm.utils.AndroidUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {

	private Timer myTimer = new Timer();
	private TimerTask myTimerTask;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
//		Debug.e("SHA for google: "+AndroidUtils.getSHA1(this));
		myTimerTask = new TimerTask() {
			
			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (isConnect()) {
							Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
							startActivity(intent);
						}
					}
				});
			}
		};
		myTimer.schedule(myTimerTask, 5000);
	}
}
