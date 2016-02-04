package com.onlinemarketing.asystask;

import java.util.List;

import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.json.JsonProduct2;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;

import android.os.AsyncTask;

public class HomeAsystask extends AsyncTask<Void, Void, List<ProductVO>> {
	List<ProductVO> productvo;
	OutputProduct outputProduct;
	String Device_id;
	JsonProduct product;
	
	public HomeAsystask() {
		super();
	}

	public HomeAsystask(List<ProductVO> oOputproduct) {
		super();
		this.productvo = oOputproduct;
	}

	@Override
	protected void onPreExecute() {
		product = new JsonProduct();
		super.onPreExecute();
	}

	@Override
	protected List<ProductVO> doInBackground(Void... params) {
		outputProduct = product.paserProduct(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
		productvo = outputProduct.getProductVO();
		return productvo;
	}

	@Override
	protected void onPostExecute(List<ProductVO> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
}
