package com.yld.core.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.yld.yytxapp.ui.R;

public abstract class BaseFragment extends Fragment {
	public ImageView head_back, head_home;
	public TextView head_title;
	public boolean isShow_backbtn = true;// 是否显示返回按钮
	public boolean isShow_homebtn = true;// 是否显示回到主页按钮
	/**
	 * @brief
	 * */
	public BaseFragmentActivity activity;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.activity = (BaseFragmentActivity) activity;
	}

	/**
	 * 初始化标题栏
	 * */
	public void initTitleLayout(View view, String title, OnClickListener leftonclickListener, OnClickListener rightonclickListener) {
		head_back = (ImageView) view.findViewById(R.id.head_back);
		head_home = (ImageView) view.findViewById(R.id.head_home);
		head_title = (TextView) view.findViewById(R.id.head_title);
		if (head_back != null) {
			head_back.setOnClickListener(leftonclickListener);
		}
		if (head_home != null) {
			head_home.setOnClickListener(rightonclickListener);
		}
		if (head_title != null) {
			head_title.setText(title);
		}
	}

	/**
	 * 是否显示返回按钮
	 * 
	 * @param isShow_backbtn
	 */
	public void Show_backbtn(boolean isShow_backbtn) {
		if (head_back != null) {
			this.isShow_backbtn = isShow_backbtn;
			if (isShow_backbtn) {
				head_back.setVisibility(View.VISIBLE);
			} else {
				head_back.setVisibility(View.INVISIBLE);
			}
		}
	}

	/**
	 * 是否显示回到主页按钮
	 * 
	 * @param isShow_homebtn
	 */
	public void Show_homebtn(boolean isShow_homebtn) {
		if (head_home != null) {
			this.isShow_homebtn = isShow_homebtn;
			if (isShow_homebtn) {
				head_home.setVisibility(View.VISIBLE);
			} else {
				head_home.setVisibility(View.INVISIBLE);
			}
		}
	}
}
