package com.onlinemarketing.activity;

import com.example.onlinemarketing.R;
import com.onlinemarketing.adapter.FavoriteAdapter;
import com.onlinemarketing.config.SystemConfig;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class FavoriteActivity extends Activity {

	ListView listview;
	FavoriteAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite);
		listview = (ListView) findViewById(R.id.listFavorite);
		adapter = new FavoriteAdapter(this, R.layout.item_favorite, SystemConfig.oOputproduct.getProfileVO());
		listview.setAdapter(adapter);
	}

}
