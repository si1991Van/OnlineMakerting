package com.onlinemarketing.activity;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.config.Constan;

import android.content.Intent;
import android.os.Bundle;

public class HomeActivity extends BaseActivity {

	private Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				if (isConnect()) {
					try {
						Debug.e("log 0");
						thread.sleep(Constan.sleep);
						Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
						startActivity(intent);
					} catch (InterruptedException e) {
						Debug.e("log 1");
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
}
