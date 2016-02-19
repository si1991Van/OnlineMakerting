package com.onlinemarketing.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonCategory;
import com.onlinemarketing.object.OutputProduct;
import com.smile.android.gsm.utils.AndroidUtils;

public class SplashActivity extends BaseActivity {

	TimerTask myTimerTask;
	Handler handler = new Handler();
	Timer myTimer = new Timer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Debug.e(AndroidUtils.getHashKey(this));
		if (isConnect()) {
			new CategoryAsystask().execute();
		}
	}

	public class CategoryAsystask extends
			AsyncTask<String, String, OutputProduct> {

		String Device_id;
		JsonCategory product;

		@Override
		protected void onPreExecute() {
			product = new JsonCategory();
			super.onPreExecute();
		}

		@Override
		protected OutputProduct doInBackground(String... params) {
			try {
				SystemConfig.oOputproduct = product.paserCategory();
			} catch (Exception e) {
				Debug.e(e.toString());
			}
			// SystemConfig.oOputproduct.setCategoryVO(list);
			return null;
		}

		@Override
		protected void onPostExecute(OutputProduct result) {
			if (!SystemConfig.oOputproduct.getCategoryVO().isEmpty()) {
				Intent intent = new Intent(SplashActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		}
	}
}
