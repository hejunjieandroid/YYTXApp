package com.yld.yytxapp.ui.main;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.utils.AlertUtil;
import com.yld.yytxapp.adapter.SlidemenuSetupAdapter;
import com.yld.yytxapp.biz.SlidemenuSetupBiz;
import com.yld.yytxapp.entity.SlidemenuSetup;
import com.yld.yytxapp.ui.R;
import com.yld.yytxapp.ui.main.fragment.MainCenterFragment;
import com.yld.yytxapp.ui.main.fragment.MainCenterFragment.MainCenterFragmentListener;
import com.yld.yytxapp.ui.main.fragment.MainLeftFragment;
import com.yld.yytxapp.ui.main.fragment.MainLeftFragment.MainLeftFragmentListener;
import com.yld.yytxapp.ui.main.fragment.MainRightFragment;
import com.yld.yytxapp.ui.main.fragment.MainRightFragment.MainRightFragmentListener;

public class MainFragmentActivity extends BaseFragmentActivity implements OnPageChangeListener, MainLeftFragmentListener, MainCenterFragmentListener, MainRightFragmentListener, OnItemClickListener, OnClickListener {

	private ViewPager viewpager_fragment;// fragment容器
	private ArrayList<Fragment> fragmentlist;

	private SlidingMenu menu;//
	private ListView lv_slidemenu;// 侧滑菜单

	private RelativeLayout rl_bottom_left, rl_bottom_center, rl_bottom_right;
	private ImageView img_bottom_left, img_bottom_center, img_bottom_right;
	private TextView tv_bottom_left, tv_bottom_center, tv_bottom_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		menu = new SlidingMenu(this);
		setContentView(R.layout.activity_mainfragment);

		initSlidemenu();

		initBottom();
		initFragmentList();

	}

	/**
	 * 初始化底部导航
	 */
	private void initBottom() {
		// TODO Auto-generated method stub
		rl_bottom_left = (RelativeLayout) findViewById(R.id.rl_bottom_left);
		rl_bottom_left.setOnClickListener(this);
		rl_bottom_center = (RelativeLayout) findViewById(R.id.rl_bottom_center);
		rl_bottom_center.setOnClickListener(this);
		rl_bottom_right = (RelativeLayout) findViewById(R.id.rl_bottom_right);
		rl_bottom_right.setOnClickListener(this);
		img_bottom_left = (ImageView) findViewById(R.id.img_bottom_left);
		img_bottom_center = (ImageView) findViewById(R.id.img_bottom_center);
		img_bottom_right = (ImageView) findViewById(R.id.img_bottom_right);
		tv_bottom_left = (TextView) findViewById(R.id.tv_bottom_left);
		tv_bottom_center = (TextView) findViewById(R.id.tv_bottom_center);
		tv_bottom_right = (TextView) findViewById(R.id.tv_bottom_right);
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
		lv_slidemenu = (ListView) findViewById(R.id.lv_slidemenu);
		SlidemenuSetupAdapter adapter = new SlidemenuSetupAdapter(activity, new SlidemenuSetupBiz().getSlidemenuSetups());
		lv_slidemenu.setAdapter(adapter);
		lv_slidemenu.setOnItemClickListener(this);
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
		viewpager_fragment.setCurrentItem(0);
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
		setBottomBg(arg0);
	}

	/**
	 * 设置底部导航状态
	 */
	private void setBottomBg(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			img_bottom_left.setImageResource(R.drawable.bottom_left_img_p);
			img_bottom_center.setImageResource(R.drawable.bottom_center_img_n);
			img_bottom_right.setImageResource(R.drawable.bottom_right_img_n);
			tv_bottom_left.setTextColor(getResources().getColor(R.color.tv_bottom_p));
			tv_bottom_center.setTextColor(getResources().getColor(R.color.tv_bottom_n));
			tv_bottom_right.setTextColor(getResources().getColor(R.color.tv_bottom_n));
			break;
		case 1:
			img_bottom_left.setImageResource(R.drawable.bottom_left_img_n);
			img_bottom_center.setImageResource(R.drawable.bottom_center_img_p);
			img_bottom_right.setImageResource(R.drawable.bottom_right_img_n);
			tv_bottom_left.setTextColor(getResources().getColor(R.color.tv_bottom_n));
			tv_bottom_center.setTextColor(getResources().getColor(R.color.tv_bottom_p));
			tv_bottom_right.setTextColor(getResources().getColor(R.color.tv_bottom_n));
			break;
		case 2:
			img_bottom_left.setImageResource(R.drawable.bottom_left_img_n);
			img_bottom_center.setImageResource(R.drawable.bottom_center_img_n);
			img_bottom_right.setImageResource(R.drawable.bottom_right_img_p);
			tv_bottom_left.setTextColor(getResources().getColor(R.color.tv_bottom_n));
			tv_bottom_center.setTextColor(getResources().getColor(R.color.tv_bottom_n));
			tv_bottom_right.setTextColor(getResources().getColor(R.color.tv_bottom_p));
			break;
		}
	}

	/**
	 * 左fragment交互接口
	 */
	@Override
	public void onLeftFragment(Object obj) {
		// TODO Auto-generated method stub
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 返回键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertUtil.ShowAlertDialog(activity, "提示", "退出应用?", false, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					FinishActivity();
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			}, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		AlertUtil.ToastMessage(activity, ((SlidemenuSetup) parent.getAdapter().getItem(position)).getTitle() + "开发中...", 1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_bottom_left:
			viewpager_fragment.setCurrentItem(0, true);
			break;
		case R.id.rl_bottom_center:
			viewpager_fragment.setCurrentItem(1, true);
			break;
		case R.id.rl_bottom_right:
			viewpager_fragment.setCurrentItem(2, true);
			break;

		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setBottomBg(viewpager_fragment.getCurrentItem());
	}
}
