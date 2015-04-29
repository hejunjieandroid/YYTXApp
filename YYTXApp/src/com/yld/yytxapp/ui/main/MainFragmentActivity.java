package com.yld.yytxapp.ui.main;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.yld.core.base.BaseFragmentActivity;
import com.yld.yytxapp.ui.R;
import com.yld.yytxapp.ui.main.fragment.MainCenterFragment;
import com.yld.yytxapp.ui.main.fragment.MainCenterFragment.MainCenterFragmentListener;
import com.yld.yytxapp.ui.main.fragment.MainLeftFragment;
import com.yld.yytxapp.ui.main.fragment.MainLeftFragment.MainLeftFragmentListener;
import com.yld.yytxapp.ui.main.fragment.MainRightFragment;
import com.yld.yytxapp.ui.main.fragment.MainRightFragment.MainRightFragmentListener;

public class MainFragmentActivity extends BaseFragmentActivity implements OnPageChangeListener, MainLeftFragmentListener, MainCenterFragmentListener, MainRightFragmentListener {

	private ViewPager viewpager_fragment;// fragment容器
	private ArrayList<Fragment> fragmentlist;

	private SlidingMenu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		menu = new SlidingMenu(this);
		setContentView(R.layout.activity_mainfragment);

		initSlidemenu();

		initFragmentList();
	}

	/**
	 * 初始化slidemenu
	 */
	private void initSlidemenu() {
		// TODO Auto-generated method stub
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		menu.setShadowWidthRes(R.dimen.shadow_width);// slide与主界面接触的地方过度背景的宽度
		menu.setShadowDrawable(R.drawable.shadow);// slide与主界面接触的地方过度背景
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// 主界面能显示出来的宽度
		// menu.setBehindWidth(1000);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.layout_slidemenu);

		menu.setOnClosedListener(new OnClosedListener() {

			@Override
			public void onClosed() {
				// TODO Auto-generated method stub

			}
		});

		initSlidemenuList();
	}

	/**
	 * 初始化slidemenu菜单
	 */
	private void initSlidemenuList() {
		// TODO Auto-generated method stub

	}

	/**
	 * 初始化fragment页
	 */
	private void initFragmentList() {
		// TODO Auto-generated method stub
		viewpager_fragment = (ViewPager) findViewById(R.id.viewpager_fragment);
		fragmentlist = new ArrayList<Fragment>();

		Fragment fragmentLeft = new MainLeftFragment();
		Fragment fragmentCenter = new MainCenterFragment();
		Fragment fragmentRight = new MainRightFragment();
		fragmentlist.add(fragmentLeft);
		fragmentlist.add(fragmentCenter);
		fragmentlist.add(fragmentRight);

		viewpager_fragment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragmentlist.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return fragmentlist.get(arg0);
			}
		});
		viewpager_fragment.setOffscreenPageLimit(3);
		viewpager_fragment.setOnPageChangeListener(this);
	}

	@Override
	protected void onTouchListenner(int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 左fragment交互接口
	 */
	@Override
	public void onLeftFragment(Object obj) {
		// TODO Auto-generated method stub
		menu.toggle();
	}

	/**
	 * 中fragment交互接口
	 */
	@Override
	public void onCenterFragment(Object obj) {
		// TODO Auto-generated method stub

	}

	/**
	 * 右fragment交互接口
	 */
	@Override
	public void onRightFragment(Object obj) {
		// TODO Auto-generated method stub

	}

}
