package com.yld.yytxapp.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yld.yytxapp.entity.SlidemenuSetup;
import com.yld.yytxapp.ui.R;

public class SlidemenuSetupAdapter extends BaseAdapter {

	private Context ctx;
	private List<SlidemenuSetup> list;

	public SlidemenuSetupAdapter(final Context ctx, List<SlidemenuSetup> list) {
		this.ctx = ctx;
		this.list = list;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(ctx).inflate(R.layout.adapter_slidemenu_setup, null);
			holder = new ViewHolder();

			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(list.get(position).getTitle());
		holder.img.setImageResource(list.get(position).getImg());

		return convertView;
	}

	class ViewHolder {
		TextView title;
		ImageView img;
	}
}