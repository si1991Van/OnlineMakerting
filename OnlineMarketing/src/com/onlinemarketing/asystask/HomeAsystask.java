package com.onlinemarketing.asystask;

import java.util.List;

import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.json.JsonProduct2;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;

import android.os.AsyncTask;

public class HomeAsystask extends AsyncTask<Void, Void, OutputProduct> {
	OutputProduct outputProduct;
	String Device_id;
	JsonProduct product;
	
	public HomeAsystask() {
		super();
	}

	public HomeAsystask(OutputProduct oOputproduct) {
		super();
		this.outputProduct = oOputproduct;
	}

	@Override
	protected void onPreExecute() {
		product = new JsonProduct();
		super.onPreExecute();
	}

	@Override
	protected OutputProduct doInBackground(Void... params) {
		SystemConfig.oOputproduct = product.paserProduct(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
		return SystemConfig.oOputproduct;
	}

	@Override
	protected void onPostExecute(OutputProduct result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
}
