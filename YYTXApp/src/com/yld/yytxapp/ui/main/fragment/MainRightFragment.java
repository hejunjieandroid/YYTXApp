package com.yld.yytxapp.ui.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yld.core.base.BaseFragment;
import com.yld.core.utils.AlertUtil;
import com.yld.yytxapp.ui.R;

public class MainRightFragment extends BaseFragment implements OnClickListener {
	private View view;
	private MainRightFragmentListener listener;

	private Button btn_phone_cmhk;// 电话
	private Button btn_phone_cmhktw;// 电话（台湾）
	private LinearLayout ll_weixin;// 微信客服
	private LinearLayout ll_help;// 使用教学
	private Button btn_we;// 关于我们

	private String phonenum = "123654";
	private String phonenumtw = "654123";

	public interface MainRightFragmentListener {
		void onRightFragment(Object obj);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		listener = (MainRightFragmentListener) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_mainright, null, false);

		initTitleLayout(view, "客户服务", new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		}, new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});

		Show_backbtn(false);

		init(view);

		return view;

	}

	/**
	 * 初始化控件
	 * 
	 * @param view2
	 */
	private void init(View view) {
		// TODO Auto-generated method stub
		btn_phone_cmhk = (Button) view.findViewById(R.id.btn_phone_cmhk);
		btn_phone_cmhk.setOnClickListener(this);
		btn_phone_cmhktw = (Button) view.findViewById(R.id.btn_phone_cmhktw);
		btn_phone_cmhktw.setOnClickListener(this);
		ll_weixin = (LinearLayout) view.findViewById(R.id.ll_weixin);
		ll_weixin.setOnClickListener(this);
		ll_help = (LinearLayout) view.findViewById(R.id.ll_help);
		ll_help.setOnClickListener(this);
		btn_we = (Button) view.findViewById(R.id.btn_we);
		btn_we.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_phone_cmhk:
			AlertUtil.ShowAlertDialog(activity, "拨打电话", "是否拨打客服电话" + phonenum + "?", false, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenum));
					startActivity(intent);
				}
			}, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});

			break;
		case R.id.btn_phone_cmhktw:
			AlertUtil.ShowAlertDialog(activity, "拨打电话", "是否拨打客服电话" + phonenumtw + "?", false, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenumtw));
					startActivity(intent);
				}
			}, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
			break;
		case R.id.ll_weixin:
			try {
				Intent intent = activity.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
				startActivity(intent);
			} catch (Exception e) {
				AlertUtil.ToastMessageShort(activity, "未安装应用程序");
			}
			break;
		case R.id.ll_help:

			break;
		case R.id.btn_we:

			break;

		}
	}
}
