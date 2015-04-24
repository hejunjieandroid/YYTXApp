package com.yld.yytxapp.ui.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import android.graphics.Paint;
import android.os.Bundle;
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
import com.yld.core.utils.JsonUtil;
import com.yld.yytxapp.ui.R;

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

			break;
		case R.id.tv_protocolFw:

			break;
		case R.id.tv_LoginPasswordForget:

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
		map.put("UserId", et_UserId.getText().toString().trim());
		map.put("LoginPassword", et_LoginPassword.getText().toString().trim());
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
					// JSONObject json = null;
					// JSONObject account = null;
					// try {
					// json = new JSONObject(response.toString());
					// account = new JSONObject(parserJSONObject(json,
					// "Account"));
					// } catch (JSONException e) {
					// // TODO Auto-generated catch block
					//
					// }
					JSONObject jo=JsonUtil.parseJSONObject(response.toString());
					JSONObject jo1=JsonUtil.getJSONObject(jo, "o");
					StringBuffer sb=new StringBuffer();
					sb.append(JsonUtil.getJSONString(jo, "success")+"\n");
					sb.append(JsonUtil.getJSONString(jo, "msg")+"\n");
					sb.append(JsonUtil.getJSONString(jo1, "cess_token")+"\n");
					sb.append(JsonUtil.getJSONString(jo1, "refresh_token")+"\n");
					sb.append(JsonUtil.getJSONString(jo1, "token_type")+"\n");
					sb.append(JsonUtil.getJSONString(jo1, "expires_in")+"\n");
					sb.append(JsonUtil.getJSONString(jo1, "uid")+"\n");
					sb.append(response.toString()+"\n");
					AlertUtil.ShowHintDialog(activity, "提示", sb.toString(), "确定", false, new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub

						}
					});
				}
			}

			@Override
			public void onError(Object errorMsg) {
				// TODO Auto-generated method stub

			}
		});
	}
}
