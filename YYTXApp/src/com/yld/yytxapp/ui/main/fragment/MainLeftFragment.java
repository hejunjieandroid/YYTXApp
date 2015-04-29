package com.yld.yytxapp.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yld.core.base.BaseFragment;
import com.yld.yytxapp.ui.R;

public class MainLeftFragment extends BaseFragment {
private View view;
	private MainLeftFragmentListener listener;
	
	public interface MainLeftFragmentListener{
		void onLeftFragment(Object obj);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		listener=(MainLeftFragmentListener) activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.fragment_mainleft, null,false);
		String s="left";
		listener.onLeftFragment(s);
		return view;
		
		
	}
}
