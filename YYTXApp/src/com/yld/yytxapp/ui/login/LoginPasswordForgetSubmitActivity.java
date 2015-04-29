package com.yld.yytxapp.ui.login;

import org.apache.commons.lang.StringUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.utils.AlertUtil;
import com.yld.yytxapp.ui.R;

public class LoginPasswordForgetSubmitActivity extends BaseFragmentActivity implements OnClickListener {

	private EditText et_submit;// 短信验证码
	private Button btn_submit;// 提交按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpasswordforgetsubmit);
		initTitleLayout("忘记密码");
		Show_homebtn(false);
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// TODO Auto-generated method stub
		et_submit = (EditText) findViewById(R.id.et_submit);
		btn_submit= (Button) findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
	}

	@Override
	protected void onTouchListenner(int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_submit:
			if (check_Date()) {
				password_reset();
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 重置密码交易
	 */
	private void password_reset() {
		// TODO Auto-generated method stub

	}

	/**
	 * 校验数据
	 */
	private boolean check_Date() {
		// TODO Auto-generated method stub
		boolean ischeck = false;
		if (StringUtils.isEmpty(et_submit.getText().toString().trim())) {
			AlertUtil.ShowHintDialog(activity, "提示", "请输入短信验证码", "确定", false, new OnClickListener() {

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
