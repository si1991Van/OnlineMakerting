package com.onlinemarketing.activity;

import java.util.ArrayList;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.adapter.BackListAdapter;
import com.onlinemarketing.adapter.ListSaveSearchAdapter;
import com.onlinemarketing.adapter.ListSaveSearchAdapter.CallbackPosition;
import com.onlinemarketing.adapter.ListSaveSearchAdapter.ViewHolder;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.json.JsonSearch;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ListView;

public class ListSaveSearchActivity extends BaseActivity
		implements OnItemClickListener, OnItemLongClickListener, CallbackPosition, OnClickListener {

	ListView listview;
	ListSaveSearchAdapter adapter;
	OutputProduct oOput;
	CheckBox check;
	Button btnDelete;
	Output out;
	ViewHolder viewHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_save_search);
		check = (CheckBox) findViewById(R.id.imgDeleteListSearch);
		btnDelete = (Button) findViewById(R.id.button1);
		listview = (ListView) findViewById(R.id.listSaveSearch);
		listview.setOnItemClickListener(this);
		listview.setOnItemLongClickListener(this);
		btnDelete.setOnClickListener(this);
		if (isConnect()) {
			new ListSaveSearchAsystask().execute();
		}
	}

	@Override
	protected void onResume() {
		ListSaveSearchAdapter.type = 0;
		super.onResume();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		startActivity(new Intent(ListSaveSearchActivity.this, BackListActivity.class));
		//
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
		if (ListSaveSearchAdapter.type == 0) {
			ListSaveSearchAdapter.type = 1;
			btnDelete.setVisibility(View.VISIBLE);

			listview.post(new Runnable() {

				@Override
				public void run() {
					listview.setSelection(arg2);
				}
			});
			adapter.notifyDataSetChanged();
		}
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
			oOput = jsonListSaveSearch.paserListSearch(SystemConfig.user_id, SystemConfig.session_id,
					SystemConfig.device_id);
			list = oOput.getProductVO();
			SystemConfig.oOputproduct.setProductVO(list);
			return oOput;
		}

		@Override
		protected void onPostExecute(OutputProduct result) {
			adapter = new ListSaveSearchAdapter(getLayoutInflater(), list, ListSaveSearchActivity.this);
			listview.setAdapter(adapter);
		}
	}

	@Override
	public void callbackDeletePosition(int position) {
	}

	@Override
	public void onClick(View v) {
//		ArrayList<ProductVO> deletelist = new ArrayList<ProductVO>();
//		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
//		for (int i = 0; i < adapter.getList().size(); i++) {
//			if (!adapter.getList().get(i).isCheck()) {
//				list.add(adapter.getList().get(i));
//			} else {
//				deletelist.add(adapter.getList().get(i));
//			}
//		}
		if (isConnect()) {
			new DeleteAsynTask().execute();
		}
	}
	public class DeleteAsynTask extends AsyncTask<Integer, String, Output> {

		JsonProduct jsonProduct;

		@Override
		protected void onPreExecute() {
			jsonProduct = new JsonProduct();
			super.onPreExecute();
		 }

		@Override
		protected Output doInBackground(Integer... params) {
			Debug.e("User: " + SystemConfig.user_id + " Session: " + SystemConfig.session_id + " device: "
					+ SystemConfig.device_id);
			out = jsonProduct.paserDeleteBackListAndFavorite(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id,
					4, SystemConfig.statusDeleteBackList);
			return out;
		}   

		@Override
		protected void onPostExecute(Output result) {
			if (result.getCode() == Constan.getIntProperty("success")) { 
				Debug.showAlert(ListSaveSearchActivity.this, result.getMessage());
//				list.remove(positon);
//				adapter = new BackListAdapter(ListSaveSearchActivity.this, R.layout.item_backlist, list);
//				Debu     g.e("danh sach: " + list.size());
//				listview.setAdapter(adapter);
			}
			super.onPostExecute(result);
		}
	}   
}
