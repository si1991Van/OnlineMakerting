package com.example.onlinemarketing;

import java.util.ArrayList;
import java.util.List;

import com.lib.Debug;
import com.lib.SharedPreferencesUtils;
import com.onlinemarketing.activity.BaseActivity;
import com.onlinemarketing.activity.FavoriteActivity;
import com.onlinemarketing.activity.ListSaveSearchActivity;
import com.onlinemarketing.activity.LoginActivity;
import com.onlinemarketing.activity.ProfileActivity;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.fragment.FragmentCategory;
import com.onlinemarketing.json.JsonProfile;
import com.onlinemarketing.json.JsonSearch;
import com.onlinemarketing.json.JsonSetting;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;
import com.onlinemarketing.object.ProfileVO;
import com.onlinemarketing.object.SettingVO;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomePageActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	List<SettingVO> listSetting = new ArrayList<SettingVO>();
	public static OutputProduct oOput;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	public static int id_category;
	public static int status = SystemConfig.statusHomeProduct;
	String[] action = { "a", "b", "c" };
	ArrayAdapter<String> objSettingAdapter;
	TextView txt_saveSearch, txtlistSaveSearch;
	Button btn_search, btn_addSearch;
	Dialog dialog;
	EditText edit_namSPSearch;
	static Output out;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		SharedPreferencesUtils.getString(this, SystemConfig.USER_ID);
		SharedPreferencesUtils.getString(this, SystemConfig.SESSION_ID);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
		Debug.e("lisst setting: " + listSetting.size());

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
				android.R.layout.simple_spinner_dropdown_item, action);

		/** Enabling dropdown list navigation for the action bar */
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		OnNavigationListener navigationListener = new OnNavigationListener() {

			@Override
			public boolean onNavigationItemSelected(int itemPosition, long itemId) {

				Debug.showAlert(HomePageActivity.this, "VL");
				return true;
			}
		};

		/**
		 * Setting dropdown items and item navigation listener for the actionbar
		 */
		getActionBar().setListNavigationCallbacks(adapter, navigationListener);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, FragmentCategory.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {

		mTitle = SystemConfig.oOputproduct.getCategoryVO().get(number - 1).getName();
		id_category = SystemConfig.oOputproduct.getCategoryVO().get(number - 1).getId();
		status = SystemConfig.statusCategoryProduct;

	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

		for (int i = 0; i < listSetting.size(); i++) {
			// menu.add(Menu.NONE, 0, 0 , listSetting);
		}

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			getMenuInflater().inflate(R.menu.global, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			new SettingAsystask().execute();
			return true;
		}
		if (id == R.id.action_search) {
			dialogSearch();
		}
		return super.onOptionsItemSelected(item);
	}

	public void findByID(View view) {

	}

	public void dialogSearch() {
		dialog = new Dialog(this);
		dialog.setCanceledOnTouchOutside(false);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_search);
		btn_addSearch = (Button) dialog.findViewById(R.id.btn_addressSearch);
		btn_search = (Button) dialog.findViewById(R.id.btn_Search);
		txt_saveSearch = (TextView) dialog.findViewById(R.id.txt_saveSearch);
		edit_namSPSearch = (EditText) dialog.findViewById(R.id.edit_namSPSearch);
		txtlistSaveSearch = (TextView) dialog.findViewById(R.id.txt_listSaveSearch);
		txt_saveSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new SaveSearchAsysTask().execute();
			}
		});
		txtlistSaveSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(HomePageActivity.this, ListSaveSearchActivity.class));
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public class getProfileAndFavoriteAsystask extends AsyncTask<Integer, String, OutputProduct> {
		JsonSearch jsonListSaveSearch;
		ArrayList<ProductVO> listProfile = new ArrayList<ProductVO>();
		
		@Override
		protected void onPreExecute() {
			jsonListSaveSearch = new JsonSearch();
			super.onPreExecute();
		}

		@Override
		protected OutputProduct doInBackground(Integer... params) {
				oOput = jsonListSaveSearch.paserListSearch(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
				listProfile = oOput.getProductVO();
				SystemConfig.oOputproduct.setProductVO(listProfile);

			return oOput;
		}
		@Override
		protected void onPostExecute(OutputProduct result) {
			if (result.getCode() == Constan.getIntProperty("success") ) {
				startActivity(new Intent(HomePageActivity.this, ProfileActivity.class));
			}
			else {
				startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
			}
			super.onPostExecute(result);
		}
	}
	
	public class SaveSearchAsysTask extends AsyncTask<String, String, Output> {
		JsonSearch jsonSearch;

		@Override
		protected void onPreExecute() {
			jsonSearch = new JsonSearch();
			super.onPreExecute();
		}

		@Override
		protected Output doInBackground(String... params) {
			out = jsonSearch.paserSaveSearch(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id, 
					"sản phẩm store", "","", "", "", "", "");
			return out;
		}

		@Override
		protected void onPostExecute(Output result) {
			if (result.getCode() == Constan.getIntProperty("success")) {
				Debug.showAlert(HomePageActivity.this, result.getMessage());
			}
			super.onPostExecute(result);
		}

	}

	public class SettingAsystask extends AsyncTask<Integer, Integer, OutputProduct> {
		JsonSetting setting;

		@Override
		protected void onPreExecute() {
			setting = new JsonSetting();

			super.onPreExecute();
		}

		@Override
		protected OutputProduct doInBackground(Integer... params) {
				oOput = setting.paserSetting(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
				listSetting = oOput.getSettingVO();
			return null;
		}

		@Override
		protected void onPostExecute(OutputProduct result) {

		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */

}
