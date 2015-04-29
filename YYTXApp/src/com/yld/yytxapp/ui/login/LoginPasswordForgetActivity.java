package com.yld.yytxapp.ui.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.http.ResultInterface;
import com.yld.core.utils.AlertUtil;
import com.yld.yytxapp.ui.R;

public class LoginPasswordForgetActivity extends BaseFragmentActivity implements OnClickListener {

	private EditText et_phone;// 手机号码
	private Button btn_sendSMS;// 发送短信按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpasswordforget);
		initTitleLayout("忘记密码");
		Show_homebtn(false);
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// TODO Auto-generated method stub
		et_phone = (EditText) findViewById(R.id.et_phone);
		btn_sendSMS = (Button) findViewById(R.id.btn_sendSMS);
		btn_sendSMS.setOnClickListener(this);
	}

	@Override
	protected void onTouchListenner(int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_sendSMS:
			if (check_Date()) {
				send_SMS();
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 发送短信交易
	 */
	private void send_SMS() {
		// TODO Auto-generated method stub
		dialog.show();
		Map<String, String> map = new HashMap<String, String>();
		map.put("date", et_phone.getText().toString().trim());
		requestPost(httpMiddleWare.Trade_Login, map, true, new ResultInterface() {

			@Override
			public void onSuccess(Object response) {
				// TODO Auto-generated method stub
				dialog.dismiss();

				if (null != response && !StringUtils.isEmpty(response.toString())) {
					StartActivity(LoginPasswordForgetSubmitActivity.class, null);
				}
			}

			@Override
			public void onError(Object errorMsg) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 校验数据
	 */
	private boolean check_Date() {
		// TODO Auto-generated method stub
		boolean ischeck = false;
		if (StringUtils.isEmpty(et_phone.getText().toString().trim())) {
			AlertUtil.ShowHintDialog(activity, "提示", "请输入手机号码或邮箱", "确定", false, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});
		} else {
			ischeck = true;
		}

		return ischeck;
	}

}
