package com.onlinemarketing.adapter;

import java.util.ArrayList;

import com.example.onlinemarketing.R;
import com.onlinemarketing.object.ProductVO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListSaveSearchAdapter extends BaseAdapter {
	private ArrayList<ProductVO> list;
	private LayoutInflater inflater;
	private CallbackPosition callback;
	public static int type = 0;
	private ViewHolder holder = null;

	public ListSaveSearchAdapter(LayoutInflater layoutInflater, ArrayList<ProductVO> data, CallbackPosition callback) {
		// TODO Auto-generated constructor stub
		this.setList(data);
		this.inflater = layoutInflater;
		this.callback = callback;
	}

	public ArrayList<ProductVO> getList() {
		return list;
	}

	public void setList(ArrayList<ProductVO> list) {
		if (list != null) {
			this.list = list;
		} else {
			list = new ArrayList<ProductVO>();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ProductVO getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return getItem(position).getId();
	}

	public class ViewHolder {
		TextView txtname;
		public CheckBox check;

		public void setData(ProductVO productVO) {
			txtname.setText(productVO.getName());
			check.setChecked(productVO.isCheck());
		}

		public ViewHolder(View view) {
			txtname = (TextView) view.findViewById(R.id.txtNameListSearch);
			check = (CheckBox) view.findViewById(R.id.imgDeleteListSearch);
		}

		public ViewHolder() {
			super();
		}
		
	}

	public interface CallbackPosition {
		void callbackDeletePosition(int position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.item_list_save_search, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (type == 0) {
			holder.check.setVisibility(View.GONE);
		} else if (type == 1) {
			holder.check.setVisibility(View.VISIBLE);
		}
		holder.setData(list.get(position));
		holder.check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.callbackDeletePosition(position);
			}
		});
		return convertView;
	}

}
