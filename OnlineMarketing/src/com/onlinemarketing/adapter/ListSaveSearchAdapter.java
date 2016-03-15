package com.onlinemarketing.adapter;

import java.util.List;

import com.example.onlinemarketing.R;
import com.onlinemarketing.object.ProductVO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListSaveSearchAdapter extends ArrayAdapter<ProductVO> {

	List<ProductVO> list;
	Context context;
	int layoutId;
	LayoutInflater inflater;
	ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.setData(list.get(position));
		return convertView;
	} 

	public ListSaveSearchAdapter(Context context, int resource, List<ProductVO> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutId = resource;
		this.list = objects;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public class ViewHolder {
		TextView txtname, txtprice, txtcategory, txttiemr;

		public void setData(ProductVO productVO) {
			txtname.setText(productVO.getName());
		}

		public ViewHolder(View view) {
			txtname = (TextView) view.findViewById(R.id.txtNameListSearch);
		}
	}

}
