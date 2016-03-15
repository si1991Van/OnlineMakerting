package com.onlinemarketing.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.onlinemarketing.R;
import com.onlinemarketing.object.MessageVO;

public class ListMessageAdapter extends ArrayAdapter<MessageVO>{

	Context context;
	List<MessageVO> listData;
	int layoutId;
	LayoutInflater inflater;
	ViewHolder holder;

	public ListMessageAdapter(Context context, int resource, List<MessageVO> objects) {
		super(context, resource, objects);
		this.context = context;
		this.layoutId = resource;
		this.listData = objects;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private class ViewHolder {
		ImageView img;
		TextView txtUserName, txtDecripMessage, txtDateMessage;
		private AQuery aQuery = null;

		public ViewHolder(View view) {
			img = (ImageView) view.findViewById(R.id.itemAvatarMessage);
			txtUserName = (TextView) view.findViewById(R.id.txtNameMessage);
			txtDecripMessage = (TextView) view.findViewById(R.id.txtDescripMessage);
			txtDateMessage = (TextView) view.findViewById(R.id.txtDateMessage);
			aQuery = new AQuery(view);
		}

		public void init(MessageVO item) {
			Bitmap bitmap = aQuery.getCachedImage(item.getAvatar());
			if (bitmap != null) {
				aQuery.id(img).image(bitmap);
			} else {
				aQuery.id(img).image(item.getAvatar(), true, true, 0, R.drawable.ic_launcher);
			}
			txtUserName.setText(item.getUsername());
			txtDecripMessage.setText(item.getMessage());
			txtDateMessage.setText(item.getCreate_at());
		}
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(layoutId, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.init(listData.get(position));
		return convertView;
	}
}
