package com.onlinemarketing.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.onlinemarketing.HomePageActivity;
import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.lib.SharedPreferencesUtils;
import com.onlinemarketing.activity.BaseFragment;
import com.onlinemarketing.activity.LoginActivity;
import com.onlinemarketing.activity.ProfileActivity;
import com.onlinemarketing.adapter.HomePageAdapter;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.json.JsonProfile;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;
import com.onlinemarketing.object.ProfileVO;
import com.smile.android.gsm.utils.AndroidUtils;

public class FragmentCategory extends Fragment implements OnItemClickListener,
		OnClickListener {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	ListView listview;
	HomePageAdapter adapter;
	List<ProductVO> list = new ArrayList<ProductVO>();
	Context context;
	View rootView;
	ProgressDialog progressDialog;
	Button btnHome, btnChat, btnFavorite, btnProfile;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static FragmentCategory newInstance(int sectionNumber) {
		FragmentCategory fragment = new FragmentCategory();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		SharedPreferencesUtils.getString(context, SystemConfig.USER_ID);
//		SharedPreferencesUtils.getString(context, SystemConfig.SESSION_ID);
//		Debug.e("ID SharedPre---------------: "+SharedPreferencesUtils.getString(context, SystemConfig.USER_ID));
//		Debug.e("session SharedPre---------------: "+SharedPreferencesUtils.getString(context, SystemConfig.SESSION_ID));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home_page, container,
				false);
		context = rootView.getContext();
		btnHome = (Button) rootView.findViewById(R.id.btnHome_FragmentCategory);
		btnChat = (Button) rootView.findViewById(R.id.btnChat_FragmentCategory);
		btnFavorite = (Button) rootView
				.findViewById(R.id.btnFavorite_FragmentCategory);
		btnProfile = (Button) rootView
				.findViewById(R.id.btnProfile_FragmentCategory);
		listview = (ListView) rootView.findViewById(R.id.listHomePage);
		listview.setOnItemClickListener(this);
		btnHome.setOnClickListener(this);
		btnChat.setOnClickListener(this);
		btnFavorite.setOnClickListener(this);
		btnProfile.setOnClickListener(this);
		new HomeAsystask().execute(HomePageActivity.status);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		((HomePageActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));

		super.onAttach(activity);
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
				HomePageActivity.oOput = product.paserProduct(SystemConfig.user_id, SystemConfig.session_id,
						SystemConfig.device_id, 0,
						SystemConfig.statusHomeProduct);
				break;
			case SystemConfig.statusCategoryProduct:
				HomePageActivity.oOput = product.paserProduct(SystemConfig.user_id, SystemConfig.session_id,
						SystemConfig.device_id, HomePageActivity.id_category,
						SystemConfig.statusCategoryProduct);
				break;
			default:
				break;
			}

			list = HomePageActivity.oOput.getProductVO();
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Debug.showAlert(context, "Vãi cường");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnHome_FragmentCategory:
			if (AndroidUtils.isConnectedToInternet(context)) {
				new HomeAsystask().execute(SystemConfig.statusHomeProduct);
			}
			break;

		case R.id.btnChat_FragmentCategory:
			break;
		case R.id.btnFavorite_FragmentCategory:
			break;
		case R.id.btnProfile_FragmentCategory:
			new getProfileAsystask().execute();
			break;
		}
	}

	public class getProfileAsystask extends AsyncTask<String, String, OutputProduct> {
		JsonProfile profile;
		ProfileVO listProfile = new ProfileVO();
		
		@Override
		protected void onPreExecute() {
			profile = new JsonProfile();
			super.onPreExecute();
		}

		@Override
		protected OutputProduct doInBackground(String... params) {
			HomePageActivity.oOput = profile.paserProfile(SystemConfig.user_id, SystemConfig.session_id, SystemConfig.device_id);
			listProfile = HomePageActivity.oOput.getProfileVO();
			SystemConfig.oOputproduct.setProfileVO(listProfile);
			return HomePageActivity.oOput;
		}
		@Override
		protected void onPostExecute(OutputProduct result) {
			if (result.getCode() == Constan.getIntProperty("success")) {
				startActivity(new Intent(context, ProfileActivity.class));
			}else {
				startActivity(new Intent(context, LoginActivity.class));
			}
			super.onPostExecute(result);
		}
	}
}