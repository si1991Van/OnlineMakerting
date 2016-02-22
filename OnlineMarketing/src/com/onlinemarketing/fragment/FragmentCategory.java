package com.onlinemarketing.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.onlinemarketing.HomePageActivity;
import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.adapter.HomePageAdapter;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.json.JsonProduct;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;

public class FragmentCategory extends Fragment implements
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
public static FragmentCategory newInstance(int sectionNumber) {
FragmentCategory fragment = new FragmentCategory();
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
	((HomePageActivity) activity).onSectionAttached(getArguments()
		     .getInt(ARG_SECTION_NUMBER));
		   // ((HomePageActivity)
		   // activity).onSectionAttached(SystemConfig.statusHomeProduct);
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
		HomePageActivity.oOput = product.paserProduct("", "",
				SystemConfig.device_id, 0,
				SystemConfig.statusHomeProduct);
		break;
	case SystemConfig.statusCategoryProduct:
		HomePageActivity.oOput = product.paserProduct("", "",
				SystemConfig.device_id,
				HomePageActivity.id_category,
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
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	long arg3) {
Debug.showAlert(context, "Vãi cường");

}
}