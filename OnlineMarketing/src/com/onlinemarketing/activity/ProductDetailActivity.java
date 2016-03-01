package com.onlinemarketing.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.adapter.ProductDetailAdapter;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.fragment.FragmentProductDetail;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.object.Output;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ProductDetailActivity extends FragmentActivity implements OnClickListener {
	CirclePageIndicator mIndicator;
	ArrayList<Fragment> fragments;
	Button btnSendSMS_Detail, btnCall, btnPoster, btnProducSave, btnErrorReport;
	EditText editErrorReport;
	Dialog dialog;
	Button btnOk, btnCancle;
	Intent intent;
	Output out;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_product_detail);
		fragments = new ArrayList<Fragment>();
		fragments.add(new FragmentProductDetail(R.drawable.ic_launcher));
		fragments.add(new FragmentProductDetail(R.drawable.ic_plusone_tall_off_client));
		String strs[] = new String[] { "Tab 1", "Tab 2 ", "Tab 3" };
		ProductDetailAdapter mAdapter = new ProductDetailAdapter(getSupportFragmentManager(), fragments,
				Arrays.asList(strs));
		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
		autoChange();
		findById();
	}

	public void findById() {
		btnSendSMS_Detail = (Button) findViewById(R.id.btnSendSMS_Detail);
		btnCall = (Button) findViewById(R.id.btnCall_Detail);
		btnPoster = (Button) findViewById(R.id.btnPoster_Detail);
		btnProducSave = (Button) findViewById(R.id.btnSave_Detail);
		btnErrorReport = (Button) findViewById(R.id.btnReportViolations_Detail);
		btnErrorReport.setOnClickListener(this);
		btnProducSave.setOnClickListener(this);
		btnSendSMS_Detail.setOnClickListener(this);
		btnCall.setOnClickListener(this);
		btnPoster.setOnClickListener(this);
	}

	private int index = 0;

	private void autoChange() {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						index += 1;
						index %= fragments.size();
						mIndicator.setCurrentItem(index);
					}
				});

			}
		}, 5000, 5000);

	}

	@Override
	public void onClick(View v) {
		String phone = "01658002108";
		switch (v.getId()) {
		case R.id.btnSendSMS_Detail:
			intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
			startActivity(intent);
			break;

		case R.id.btnCall_Detail:
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
			startActivity(intent);
			break;
		case R.id.btnPoster_Detail:
			startActivity(new Intent(ProductDetailActivity.this, PosterDetailActivity.class));
			break;
		case R.id.btnSave_Detail:
			if (SystemConfig.user_id.isEmpty() && SystemConfig.session_id.isEmpty()) {
				startActivity(new Intent(ProductDetailActivity.this, LoginActivity.class));
			} else {
				new ProductSaveAndReportAsynTask().execute(SystemConfig.statusProductSave);
			}
			break;

		case R.id.btnReportViolations_Detail:
			dialogErrorReport();
			break;

		}

	}

	public void dialogErrorReport() {
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_errorreport);
		dialog.setTitle("Thông Báo");
		editErrorReport = (EditText) dialog.findViewById(R.id.editErrorReport);
		btnOk = (Button) dialog.findViewById(R.id.btn_Ok_ErrorReport);
		btnCancle = (Button) dialog.findViewById(R.id.btn_Cancle_ErrorReport);
		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new ProductSaveAndReportAsynTask().execute(SystemConfig.statusErrorReport);
				dialog.dismiss();
			}
		});
		btnCancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public class ProductSaveAndReportAsynTask extends AsyncTask<Integer, String, Output> {

		JsonProduct jsonProduct;

		@Override
		protected void onPreExecute() {
			jsonProduct = new JsonProduct();
			super.onPreExecute();
		}

		@Override
		protected Output doInBackground(Integer... params) {
			switch (params[0]) {
			case SystemConfig.statusProductSave:

				Debug.e("User: " + SystemConfig.user_id + " Session: " + SystemConfig.session_id + " device: "
						+ SystemConfig.device_id);
				out = jsonProduct.paserNews(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
				break;

			case SystemConfig.statusErrorReport:
				Debug.e("User: " + SystemConfig.user_id + " Session: " + SystemConfig.session_id + " device: "
						+ SystemConfig.device_id);
				out = jsonProduct.paserErrorReport(SystemConfig.user_id, SystemConfig.session_id,
						SystemConfig.device_id, 1, editErrorReport.getText().toString());

				break;
			}

			return out;
		}

		@Override
		protected void onPostExecute(Output result) {
			Debug.e("bbbbbbbbbbbbbb: " + Constan.getIntProperty("success"));
			Debug.e("aaaaaaaaaaaaaa: " + result.getCode());
			if (result.getCode() == Constan.getIntProperty("success")) {
				Debug.showAlert(ProductDetailActivity.this, result.getMessage());
			}
			super.onPostExecute(result);
		}
	}

}