package com.onlinemarketing.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.onlinemarketing.R;
import com.onlinemarketing.adapter.HomePageAdapter;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonListNewsPoster;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PosterDetailActivity extends BaseActivity implements OnClickListener, OnItemClickListener{
	List<ProductVO> list = new ArrayList<ProductVO>();
	ProgressDialog progressDialog;
	ListView listview;
	HomePageAdapter adapter;
	OutputProduct oOput;
	Button btnSendSMS_Detail, btnCall;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poster_detail);
		listview = (ListView) findViewById(R.id.listPoster);
		btnSendSMS_Detail = (Button) findViewById(R.id.btnSendSMS_Detail);
		btnCall = (Button) findViewById(R.id.btnCall_Detail);
		btnCall.setOnClickListener(this);
		btnSendSMS_Detail.setOnClickListener(this);
		listview.setOnItemClickListener(this);
		if (isConnect()) {
			new NewsPosterAsystask().execute();
		}
	}

	public class NewsPosterAsystask extends AsyncTask<Integer, Integer, OutputProduct> {
		String Device_id;
		JsonListNewsPoster newsPoster;

		@Override
		protected void onPreExecute() {
			newsPoster = new JsonListNewsPoster();
			progressDialog = new ProgressDialog(PosterDetailActivity.this);
			// Set progressdialog message
			progressDialog.setMessage("Loading...");
			progressDialog.setIndeterminate(false);
			// Show progressdialog
			progressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected OutputProduct doInBackground(Integer... params) {
			oOput = newsPoster.paserListNewsPoster(SystemConfig.user_id, SystemConfig.session_id,
					SystemConfig.device_id, 1);
			list = oOput.getProductVO();
			return oOput;
		}

		@Override
		protected void onPostExecute(OutputProduct result) {
			adapter = new HomePageAdapter(PosterDetailActivity.this, R.layout.item_trang_chu, list);
			listview.setAdapter(adapter);
			progressDialog.dismiss();

		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		startActivity(new Intent(PosterDetailActivity.this, ProductDetailActivity.class));
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
		}
	}
}
