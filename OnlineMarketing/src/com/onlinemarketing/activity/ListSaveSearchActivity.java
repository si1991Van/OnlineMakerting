package com.onlinemarketing.activity;

import java.util.ArrayList;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.adapter.ListSaveSearchAdapter;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonSearch;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListSaveSearchActivity extends BaseActivity implements OnItemClickListener, OnItemLongClickListener{

	ListView listview;
	ListSaveSearchAdapter adapter;
	OutputProduct oOput;
	CheckBox check;
	Button btnDelete;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_save_search);
		check = (CheckBox) findViewById(R.id.imgDeleteListSearch);
		btnDelete = (Button) findViewById(R.id.button1);
		listview = (ListView) findViewById(R.id.listSaveSearch);
		
		listview.setOnItemClickListener(this);
		listview.setOnItemLongClickListener(this);
		if (isConnect()) {
			new ListSaveSearchAsystask().execute();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		startActivity(new Intent(ListSaveSearchActivity.this, BackListActivity.class));
//		
	}
	@Override 
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		check.setVisibility(View.VISIBLE);
		btnDelete.setVisibility(View.VISIBLE);
		Debug.showAlert(this, "Bối Rối quá");
		return true;
	}
	
	public class ListSaveSearchAsystask extends AsyncTask<Integer, String, OutputProduct> {
		JsonSearch jsonListSaveSearch;
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		@Override
		protected void onPreExecute() {
			jsonListSaveSearch = new JsonSearch();       
			super.onPreExecute();
		}
		@Override
		protected OutputProduct doInBackground(Integer... params) {
				oOput = jsonListSaveSearch.paserListSearch(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
				list = oOput.getProductVO();
				SystemConfig.oOputproduct.setProductVO(list);
			return oOput;
		}
		@Override
		protected void onPostExecute(OutputProduct result) {
			adapter = new ListSaveSearchAdapter(ListSaveSearchActivity.this, R.layout.item_list_save_search, list);
			listview.setAdapter(adapter);
		}
	}
	
	
}
