package com.onlinemarketing.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.onlinemarketing.R;
import com.example.onlinemarketing.R.id;
import com.example.onlinemarketing.R.layout;
import com.example.onlinemarketing.R.menu;
import com.onlinemarketing.activity.PosterDetailActivity.NewsPosterAsystask;
import com.onlinemarketing.adapter.HomePageAdapter;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonListNewsPoster;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SaveNewsListActivity extends BaseActivity implements OnClickListener, OnItemClickListener{
	List<ProductVO> list = new ArrayList<ProductVO>();
	ProgressDialog progressDialog;
	ListView listview;
	HomePageAdapter adapter;
	Button btnSendSMS_Detail, btnCall;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_news_list);
		listview = (ListView) findViewById(R.id.listPoster);
		adapter = new HomePageAdapter(SaveNewsListActivity.this, R.layout.item_trang_chu, SystemConfig.oOputproduct.getProductVO());
		listview.setAdapter(adapter);
		btnSendSMS_Detail = (Button) findViewById(R.id.btnSendSMS_Detail);
		btnCall = (Button) findViewById(R.id.btnCall_Detail);
		btnCall.setOnClickListener(this);
		btnSendSMS_Detail.setOnClickListener(this);
		listview.setOnItemClickListener(this);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		startActivity(new Intent(SaveNewsListActivity.this, ProductDetailActivity.class));		
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
