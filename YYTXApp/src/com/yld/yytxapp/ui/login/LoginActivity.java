package com.yld.yytxapp.ui.login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.http.ResultInterface;
import com.yld.core.utils.AlertUtil;
import com.yld.core.utils.DeviceUtil;
import com.yld.core.utils.JsonUtil;
import com.yld.core.utils.Util;
import com.yld.yytxapp.entity.UserInfo;
import com.yld.yytxapp.ui.R;
import com.yld.yytxapp.ui.main.MainFragmentActivity;

public class LoginActivity extends BaseFragmentActivity implements OnClickListener {

	private EditText et_UserId;// 登录账号
	private EditText et_LoginPassword;// 密码
	private CheckBox checkBox_login;// 是否记住用户
	private Button btn_login;// 登录按钮
	private TextView tv_protocolYs;// 隐私政策
	private TextView tv_protocolFw;// 服务条款
	private TextView tv_LoginPasswordForget;// 忘记密码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		init();

		initUserState();
	}

	/**
	 * 初始化登录用户状态——是否自动登录
	 */
	private void initUserState() {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(constant.getToggleString("UserId"))) {
			checkBox_login.setChecked(false);
		} else {
			checkBox_login.setChecked(true);
			et_UserId.setText(constant.getToggleString("UserId"));
			et_LoginPassword.setText(constant.getToggleString("LoginPassword"));
		}
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		// TODO Auto-generated method stub
		et_UserId = (EditText) findViewById(R.id.et_UserId);
		et_LoginPassword = (EditText) findViewById(R.id.et_LoginPassword);
		checkBox_login = (CheckBox) findViewById(R.id.checkBox_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		tv_protocolYs = (TextView) findViewById(R.id.tv_protocolYs);
		tv_protocolYs.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		tv_protocolYs.setOnClickListener(this);
		tv_protocolFw = (TextView) findViewById(R.id.tv_protocolFw);
		tv_protocolFw.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		tv_protocolFw.setOnClickListener(this);
		tv_LoginPasswordForget = (TextView) findViewById(R.id.tv_LoginPasswordForget);
		tv_LoginPasswordForget.setOnClickListener(this);
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
			if (checkDate()) {
				requestlogin();
			} else {
				return;
			}

			break;
		case R.id.tv_protocolYs:
			Bundle bundle = new Bundle();
			bundle.putString("which", "0");
			StartActivity(LoginProtocolActivity.class, bundle);
			break;
		case R.id.tv_protocolFw:
			Bundle bundle1 = new Bundle();
			bundle1.putString("which", "1");
			StartActivity(LoginProtocolActivity.class, bundle1);
			break;
		case R.id.tv_LoginPasswordForget:
			if (StringUtils.isEmpty(et_UserId.getText().toString().trim())) {
				AlertUtil.ShowHintDialog(activity, "提示", "请先输入账号", "确定", false, new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

					}
				});
			} else {
				StartActivity(LoginPasswordForgetActivity.class, null);
			}
			break;

		}
	}

	/**
	 * 校验输入数据
	 * 
	 * @return 通过true 不通过false
	 */
	private boolean checkDate() {
		// TODO Auto-generated method stub
		boolean ischeck = false;

		if (StringUtils.isEmpty(et_UserId.getText().toString().trim())) {
			AlertUtil.ToastMessageShort(activity, "请输入账号");
		} else if (StringUtils.isEmpty(et_LoginPassword.getText().toString().trim())) {
			AlertUtil.ToastMessageShort(activity, "请输入密码");
		} else {
			ischeck = true;
		}

		return ischeck;
	}

	/**
	 * 登录交易
	 */
	protected void requestlogin() {
		// TODO Auto-generated method stub
		dialog.show();
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", et_UserId.getText().toString().trim());
		map.put("userpwd", et_LoginPassword.getText().toString().trim());
		map.put("client_id", GetDeviceInfo());
		map.put("ieme", DeviceUtil.getIMEI(context));

		requestPost(httpMiddleWare.Trade_Login, map, true, new ResultInterface() {

			@Override
			public void onSuccess(Object response) {
				// TODO Auto-generated method stub
				dialog.dismiss();

				if (null != response && !StringUtils.isEmpty(response.toString())) {
					// 是否记住账号
					if (checkBox_login.isChecked()) {
						constant.setToggleString("UserId", et_UserId.getText().toString().trim());
						constant.setToggleString("LoginPassword", et_LoginPassword.getText().toString().trim());
					} else {
						constant.setToggleString("UserId", "");
						constant.setToggleString("LoginPassword", "");
					}

					JSONObject jo = JsonUtil.parseJSONObject(response.toString());
					JSONObject joo = JsonUtil.getJSONObject(jo, "o");
					// 记住token和超时时间
					constant.setToggleString(constant.ACCESS_TOKEN, JsonUtil.getJSONString(joo, "access_token"));
					constant.setToggleString(constant.EXPIRED, JsonUtil.getJSONString(joo, "expired"));

					// 设置登录状态
					UserInfo userInfo = new UserInfo();
					userInfo.setUserName(et_UserId.getText().toString().trim());
					userInfo.setPassword(et_LoginPassword.getText().toString().trim());
					userInfo.setAccess_token(JsonUtil.getJSONString(joo, "access_token"));
					userInfo.setExpired(JsonUtil.getJSONString(joo, "expired"));
					constant.setLogin(true);

					// 登陆成功跳转
					StartActivity(MainFragmentActivity.class, null);
					FinishActivity();
				}
			}

			@Override
			public void onError(Object errorMsg) {
				// TODO Auto-generated method stub
				StartActivity(MainFragmentActivity.class, null);
				FinishActivity();
			}
		});

	}

	/**
	 * 获取设备信息
	 * @return
	 */
	private String GetDeviceInfo() {
		// TODO Auto-generated method stub
		return DeviceUtil.getDeviceInfo(context).toString();
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

}
