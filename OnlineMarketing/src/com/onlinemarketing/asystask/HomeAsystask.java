package com.onlinemarketing.asystask;

import com.example.onlinemarketing.HomePageActivity;
import com.example.onlinemarketing.HomePageActivity.PlaceholderFragment;
import com.lib.Debug;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.object.OutputProduct;

import android.os.AsyncTask;

public class HomeAsystask extends AsyncTask<Void, Void, OutputProduct> {
	OutputProduct outputProduct;
	String Device_id;
	JsonProduct product;

	public HomeAsystask() {
		super();
	}

	@Override
	protected void onPreExecute() {
		product = new JsonProduct();
		super.onPreExecute();
	}

	@Override
	protected synchronized OutputProduct doInBackground(Void... params) {
		Debug.e("vãi căc: 000000000000000000000000000000000");
		SystemConfig.oOputproduct = product.paserProduct("", "", SystemConfig.device_id);
		return SystemConfig.oOputproduct;
	}

	@Override
	protected synchronized void onPostExecute(OutputProduct result) {
		Debug.e("vãi căc: 11111111111111111111111111111111111");
		if (SystemConfig.oOputproduct.getProductVO().isEmpty()) {
			PlaceholderFragment.adapter.addAll(SystemConfig.oOputproduct.getProductVO());
			PlaceholderFragment.adapter.notifyDataSetChanged();
			Debug.e("vãi căc: 3333333333333333333333333333333");
		}
		Debug.e("vãi căc: 4444444444444444444444444444444444444");
		SystemConfig.oOputproduct = result;
		super.onPostExecute(SystemConfig.oOputproduct);
	}
}
