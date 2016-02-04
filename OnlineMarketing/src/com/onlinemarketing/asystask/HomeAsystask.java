package com.onlinemarketing.asystask;

import android.os.AsyncTask;

import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.object.OutputProduct;

public class HomeAsystask extends AsyncTask<Void, Void, OutputProduct> {
	OutputProduct oOputproduct;
	String Device_id;
	JsonProduct product;
	
	public HomeAsystask() {
		super();
	}

	public HomeAsystask(OutputProduct oOputproduct) {
		super();
		this.oOputproduct = oOputproduct;
	}

	@Override
	protected void onPreExecute() {
		product = new JsonProduct();
		super.onPreExecute();
	}

	@Override
	protected OutputProduct doInBackground(Void... params) {
		oOputproduct = product.paserProduct(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
		return oOputproduct;
	}

	@Override
	protected void onPostExecute(OutputProduct result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
}
