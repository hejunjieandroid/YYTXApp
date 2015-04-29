package com.yld.yytxapp.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yld.core.base.BaseFragment;
import com.yld.yytxapp.ui.R;

public class MainRightFragment extends BaseFragment {
private View view;
	private MainRightFragmentListener listener;
	
	public interface MainRightFragmentListener{
		void onRightFragment(Object obj);
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		listener=(MainRightFragmentListener) activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view=inflater.inflate(R.layout.fragment_mainright, null,false);
		String s="right";
		listener.onRightFragment(s);
		return view;
		
		
	}
}
