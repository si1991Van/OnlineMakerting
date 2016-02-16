package com.onlinemarketing.asystask;

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
	protected OutputProduct doInBackground(Void... params) {
		SystemConfig.oOputproduct = product.paserProduct("", "", SystemConfig.device_id);
		return SystemConfig.oOputproduct;
	}

	@Override
	protected void onPostExecute(OutputProduct result) {
		// TODO Auto-generated method stub
		SystemConfig.oOputproduct = result;
		super.onPostExecute(SystemConfig.oOputproduct);
	}
}
