package com.example.onlinemarketing;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lib.Debug;
import com.onlinemarketing.adapter.HomePageAdapter;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.json.JsonSetting;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;
import com.onlinemarketing.object.SettingVO;

public class HomePageActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	List<SettingVO> listSetting = new ArrayList<SettingVO>();
	static OutputProduct oOput;
	private NavigationDrawerFragment mNavigationDrawerFragment;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	public static int id_category;
	public static int status = SystemConfig.statusHomeProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {

		// Debug.e("vao roi");
		mTitle = SystemConfig.oOputproduct.getCategoryVO().get(number - 1)
				.getName();
		id_category = SystemConfig.oOputproduct.getCategoryVO().get(number - 1)
				.getId();
		Debug.e("id category:" + id_category);
		status = SystemConfig.statusCategoryProduct;

	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
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
		return super.onOptionsItemSelected(item);
	}

	public void findByID(View view){
		
	}
	public class SettingAsystask extends
			AsyncTask<Integer, Integer, OutputProduct> {
		String Device_id;
		JsonSetting setting;
		

		@Override
		protected void onPreExecute() {
			setting = new JsonSetting();
			super.onPreExecute();
		}

		@Override
		protected OutputProduct doInBackground(Integer... params) {
			oOput = setting.paserSetting(SystemConfig.user_id,
					SystemConfig.session_id, SystemConfig.device_id);

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
	public static class PlaceholderFragment extends Fragment implements
			OnItemClickListener {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		ListView listview;
		HomePageAdapter adapter;
		List<ProductVO> list = new ArrayList<ProductVO>();

		Context context;
		View rootView;
		ProgressDialog progressDialog;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.fragment_home_page, container,
					false);
			context = rootView.getContext();
			listview = (ListView) rootView.findViewById(R.id.listHomePage);
			listview.setOnItemClickListener(this);

			new HomeAsystask().execute(HomePageActivity.status);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((HomePageActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
			// ((HomePageActivity)
			// activity).onSectionAttached(SystemConfig.statusHomeProduct);
			Debug.e("click lan ");

		}

		public class HomeAsystask extends
				AsyncTask<Integer, Integer, OutputProduct> {
			String Device_id;
			JsonProduct product;

			public HomeAsystask() {
				super();
			}

			@Override
			protected void onPreExecute() {
				product = new JsonProduct();
				progressDialog = new ProgressDialog(context);
				// Set progressdialog message
				progressDialog.setMessage("Loading...");
				progressDialog.setIndeterminate(false);
				// Show progressdialog
				progressDialog.show();
				super.onPreExecute();
			}

			@Override
			protected OutputProduct doInBackground(Integer... params) {
				switch (params[0]) {
				case SystemConfig.statusHomeProduct:
					oOput = product.paserProduct("", "",
							SystemConfig.device_id, 0,
							SystemConfig.statusHomeProduct);
					break;
				case SystemConfig.statusCategoryProduct:
					oOput = product.paserProduct("", "",
							SystemConfig.device_id,
							HomePageActivity.id_category,
							SystemConfig.statusCategoryProduct);
					break;
				default:
					break;
				}

				list = oOput.getProductVO();
				return null;
			}

			@Override
			protected void onPostExecute(OutputProduct result) {
				adapter = new HomePageAdapter(context, R.layout.item_trang_chu,
						list);
				listview.setAdapter(adapter);
				progressDialog.dismiss();

			}
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Debug.showAlert(context, "Vãi cường");

		}
	}
}
