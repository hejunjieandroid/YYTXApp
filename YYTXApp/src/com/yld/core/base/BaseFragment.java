package com.yld.core.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment{

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
}
