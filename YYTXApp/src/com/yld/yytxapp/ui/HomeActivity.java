package com.yld.yytxapp.ui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.utils.AlertUtil;
import com.yld.core.utils.Util;
import com.yld.core.view.ViewPagerScroller;
import com.yld.yytxapp.adapter.MyPagerAdapter;
import com.yld.yytxapp.entity.UserInfo;
import com.yld.yytxapp.ui.login.LoginActivity;

public class HomeActivity extends BaseFragmentActivity implements OnClickListener {
	private Button btn_login;// 登录按钮
	private Button btn_stk;// 激活卡片按钮
	private Button btn_buystk;// 购买贴膜卡按钮

	private ViewPager viewpager_gg;// 页卡内容
	private List<View> listViews; // Tab页面列表
	private int imgcount = 7;// 广告数量-2

	private LinearLayout imggroup;// 圆点
	private ArrayList<ImageView> dians = new ArrayList<ImageView>();// 广告栏小点

	// private static Handler handlergg;// 控制广告切换
	// private Runnable viewpagerRunable;
	private int current = 0;

	// private int WHAT = 131;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		init();

	}

	/**
	 * 初始化控件
	 */
	private void init() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_stk = (Button) findViewById(R.id.btn_stk);
		btn_stk.setOnClickListener(this);
		btn_buystk = (Button) findViewById(R.id.btn_buystk);
		btn_buystk.setOnClickListener(this);

		imggroup = (LinearLayout) findViewById(R.id.imggroup);
		viewpager_gg = (ViewPager) findViewById(R.id.viewpager_gg);
		// initViewPagerScroll();
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		for (int i = 0; i < imgcount - 2; i++) {
			ImageView view = (ImageView) mInflater.inflate(R.layout.img_gg, null);
			if (i == 0) {
				view.setImageResource(R.drawable.img_gg1);
			} else if (i == 1) {
				view.setImageResource(R.drawable.img_gg2);
			} else if (i == 2) {
				view.setImageResource(R.drawable.img_gg3);
			} else if (i == 3) {
				view.setImageResource(R.drawable.img_gg4);
			} else if (i == 4) {
				view.setImageResource(R.drawable.img_gg5);
			}

			listViews.add(view);
		}

		viewpager_gg.setAdapter(new MyPagerAdapter(listViews));
		viewpager_gg.setCurrentItem(0);
		viewpager_gg.setOffscreenPageLimit(imgcount - 2);
		viewpager_gg.setOnPageChangeListener(new YdOnPageChangeListener());

		// 圆点

		addPoint();
		findPoint();

		// handlergg = new Handler() {
		// @Override
		// public void handleMessage(Message msg) {
		// // TODO Auto-generated method stub
		// super.handleMessage(msg);
		// if (msg.what == 131) {
		// if (current == imgcount - 1) {
		// current = 1;
		// }
		// viewpager_gg.setCurrentItem(msg.arg1, true);
		// current++;
		// } else if (msg.what == 132) {
		//
		// }
		// handlergg.postDelayed(viewpagerRunable, 3000);
		// }
		// };
		//
		// viewpagerRunable = new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		// // while (true) {
		// try {
		// Message msg = handlergg.obtainMessage();
		// msg.arg1 = current;
		// msg.what = WHAT;
		//
		// handlergg.sendMessage(msg);
		//
		// // Thread.sleep(3000);
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// // }
		// };
		// handlergg.postDelayed(viewpagerRunable, 3000);
	}

	@Override
	protected void onTouchListenner(int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			if (goLogin()) {
				StartActivity(LoginActivity.class, null);
				FinishActivity();
			} else {
				UserInfo userInfo = new UserInfo();
				userInfo.setUserName(constant.getToggleString("UserId"));// 不到登录页（是否存储id,checkbox导致）
				userInfo.setPassword(constant.getToggleString("LoginPassword"));
				userInfo.setAccess_token(constant.getToggleString(constant.ACCESS_TOKEN));
				userInfo.setExpired(constant.getToggleString(constant.EXPIRED));

				constant.setUserInfo(userInfo);
				constant.setLogin(true);
			}

			break;
		case R.id.btn_stk:
			try {
				Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.stk");
				startActivity(intent);
			} catch (Exception e) {
				AlertUtil.ToastMessageShort(activity, "未安装应用程序");
			}
			break;
		case R.id.btn_buystk:

			break;

		}
	}

	/**
	 * 判断是否到登录界面
	 * 
	 * @return
	 */
	private boolean goLogin() {
		// TODO Auto-generated method stub
		boolean gotologin = false;
		if ("".equals(constant.getToggleString("UserId"))) {
			gotologin = true;
		} else if ("".equals(constant.getToggleString(constant.ACCESS_TOKEN)) || "".equals(constant.getToggleString(constant.EXPIRED))) {
			gotologin = true;
		} else if (Util.timeFormattimeFormatString2Date(constant.getToggleString(constant.EXPIRED), constant.TIMEFORMAT).before(new Date())) {
			gotologin = true;
		}

		return gotologin;
	}

	/**
	 * 动态添加广告栏的小点。
	 */
	private void addPoint() {
		for (int i = 0; i < imgcount - 2; i++) {
			FrameLayout frameLayout = new FrameLayout(this);
			LayoutParams pa = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			android.widget.FrameLayout.LayoutParams lp = new android.widget.FrameLayout.LayoutParams(android.widget.FrameLayout.LayoutParams.WRAP_CONTENT, android.widget.FrameLayout.LayoutParams.WRAP_CONTENT);
			if (i == 0) {

			} else {
				pa.leftMargin = 10;
			}
			ImageView dian0 = new ImageView(this);
			dian0.setImageResource(R.drawable.dian2);
			ImageView dian1 = new ImageView(this);
			dian1.setImageResource(R.drawable.dian1);
			dian1.setVisibility(View.INVISIBLE);
			frameLayout.addView(dian0, lp);
			frameLayout.addView(dian1, lp);
			imggroup.addView(frameLayout, pa);
		}
	}

	/**
	 * 找到广告栏添加的小点。
	 */
	private void findPoint() {
		FrameLayout frameLayout;
		dians.clear();
		for (int i = 0; i < imgcount - 2; i++) {
			frameLayout = (FrameLayout) imggroup.getChildAt(i);
			dians.add((ImageView) frameLayout.getChildAt(1));
		}
		dians.get(0).setVisibility(View.VISIBLE);
	}

	/**
	 * 设置当前的点
	 * 
	 * @param position
	 *            位置
	 */
	private void setCurrentPoint(int position) {
		switch (position) {
		case 0:
			dians.get(0).setVisibility(View.VISIBLE);
			dians.get(1).setVisibility(View.INVISIBLE);
			dians.get(2).setVisibility(View.INVISIBLE);
			dians.get(3).setVisibility(View.INVISIBLE);
			dians.get(4).setVisibility(View.INVISIBLE);
			break;
		case 1:
			dians.get(0).setVisibility(View.INVISIBLE);
			dians.get(1).setVisibility(View.VISIBLE);
			dians.get(2).setVisibility(View.INVISIBLE);
			dians.get(3).setVisibility(View.INVISIBLE);
			dians.get(4).setVisibility(View.INVISIBLE);
			break;
		case 2:
			dians.get(0).setVisibility(View.INVISIBLE);
			dians.get(1).setVisibility(View.INVISIBLE);
			dians.get(2).setVisibility(View.VISIBLE);
			dians.get(3).setVisibility(View.INVISIBLE);
			dians.get(4).setVisibility(View.INVISIBLE);
			break;
		case 3:
			dians.get(0).setVisibility(View.INVISIBLE);
			dians.get(1).setVisibility(View.INVISIBLE);
			dians.get(2).setVisibility(View.INVISIBLE);
			dians.get(3).setVisibility(View.VISIBLE);
			dians.get(4).setVisibility(View.INVISIBLE);
			break;
		case 4:
			dians.get(0).setVisibility(View.INVISIBLE);
			dians.get(1).setVisibility(View.INVISIBLE);
			dians.get(2).setVisibility(View.INVISIBLE);
			dians.get(3).setVisibility(View.INVISIBLE);
			dians.get(4).setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	/**
	 * 圆点广告切换监听
	 */
	public class YdOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			// if (arg0 == 1) {
			// WHAT = 132;
			// } else if (arg0 == 0) {
			// WHAT = 131;
			// } else if (arg0 == 2) {
			// WHAT = 131;
			// }
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			// WHAT = 131;
			current = arg0;
			// if (arg0 == 0) {
			// current = imgcount - 2;
			// viewpager_gg.setCurrentItem(imgcount - 2, false);
			// } else if (arg0 == imgcount - 1) {
			// current = 1;
			// viewpager_gg.setCurrentItem(1, false);
			// }
			if (arg0 == 4) {
				btn_login.setVisibility(View.VISIBLE);
				btn_stk.setVisibility(View.VISIBLE);
				btn_buystk.setVisibility(View.VISIBLE);
			} else {
				btn_login.setVisibility(View.GONE);
				btn_stk.setVisibility(View.GONE);
				btn_buystk.setVisibility(View.GONE);
			}
			setCurrentPoint(current);

			// for (int i = 0; i < imgcount-2; i++) {
			// int a = 0;
			// if (arg0 == 0) {
			// a = imgcount-3;
			// } else if (arg0 == (imgcount-1)) {
			// a = 0;
			// } else {
			// a = arg0 - 1;
			// }
			// if (i == a) {
			// ((ImageView)
			// imggroup.getChildAt(i)).setImageResource(R.drawable.dian1);
			// } else {
			// ((ImageView)
			// imggroup.getChildAt(i)).setImageResource(R.drawable.dian2);
			// }
			// }
		}

	}

	/**
	 * 设置ViewPager的滑动速度
	 * 
	 * */
	private void initViewPagerScroll() {
		try {
			Field mScroller = null;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			ViewPagerScroller scroller = new ViewPagerScroller(viewpager_gg.getContext(), new LinearInterpolator());
			mScroller.set(viewpager_gg, scroller);
		} catch (NoSuchFieldException e) {

		} catch (IllegalArgumentException e) {

		} catch (IllegalAccessException e) {

		}
	}
}
