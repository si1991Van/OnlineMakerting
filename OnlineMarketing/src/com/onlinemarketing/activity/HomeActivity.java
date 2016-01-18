package com.onlinemarketing.activity;

import com.example.onlinemarketing.R;
import com.example.onlinemarketing.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		try {
			// check internet
			if (isConnect()) {
				Thread.sleep(10000);
				Intent intent = new Intent(HomeActivity.this,
						LoginActivity.class);
				startActivity(intent);
			} else {

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
