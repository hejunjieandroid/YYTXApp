package com.yld.core.utils;

import java.util.HashMap;
import java.util.Map;

import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.http.ResultInterface;
import com.yld.core.httphelper.HttpMiddleWare;

/**
 * http工具类
 * */
public class HttpUtil {

	private static HttpUtil httpUtil;

	public static HttpUtil getInstance() {
		if (httpUtil == null) {
			httpUtil = new HttpUtil();
			return httpUtil;
		}
		return httpUtil;
	}
	
	/**
	 * @brief 发送时间戳交易
	 * @param activity BaseFragmentActivity对象
	 * @param resultInterface 结果回调
	 * */
	public void sendTimeStamp(BaseFragmentActivity activity,final ResultInterface resultInterface){
		activity.requestGet(HttpMiddleWare.Trade_Timestamp, null, true,new ResultInterface() {
			
			@Override
			public void onSuccess(Object response) {
				// TODO Auto-generated method stub
				resultInterface.onSuccess(response);
			}
			
			@Override
			public void onError(Object errorMsg) {
				// TODO Auto-generated method stub
				resultInterface.onError(errorMsg);
			}
		});
	}
	/**
	 * @brief 发送放重复提交码交易
	 * @param activity BaseFragmentActivity对象
	 * @param resultInterface 结果回调
	 * */
	public void sendGenToken(BaseFragmentActivity activity,final ResultInterface resultInterface){
		activity.requestGet(HttpMiddleWare.Trade_GenToken, null,false, new ResultInterface() {
			
			@Override
			public void onSuccess(Object response) {
				// TODO Auto-generated method stub
				resultInterface.onSuccess(response);
			}
			
			@Override
			public void onError(Object errorMsg) {
				// TODO Auto-generated method stub
				resultInterface.onError(errorMsg);
			}
		});
	}
	
	/**
	 * 找回密码以外的所有交易发送短信验证码方法
	 * @param activity
	 * @param mobilePhone
	 * @param TokenMessage
	 * @return
	 */
	public boolean SendSMSAuthTrade(final BaseFragmentActivity activity,String mobilePhone,String TokenMessage){
		if ("".equals(mobilePhone)) {
			AlertUtil.ToastMessageShort(activity, "手机号码不能为空！");
			return false;
		}else if (mobilePhone.length()<11) {
			AlertUtil.ToastMessageShort(activity, "手机号码位数不正确！");
			return false;
		}else if (!mobilePhone.matches("^[1][3-8]+\\d{9}")) {
			AlertUtil.ToastMessageShort(activity, "手机号码格式不正确！");
			return false;
		}else {
			Map<String, String> map = new HashMap<String, String>();
//			map.put(Constant.KEY_BANKID, Constant.BankId);
			map.put(Constant.KEY_TOKENINDEX, "1");
			map.put(Constant.KEY_TOKENMESSAGE, TokenMessage);
//			map.put(Constant.KEY_EXISTCHECKFLAG, "false");
			map.put(Constant.KEY_MOBILEPHONE, mobilePhone);
			map.put(Constant.KEY_LOCALE, "zh_CN");
			map.put("ExistCheckFlag", "true");
			activity.requestPost(HttpMiddleWare.Trade_SMSAuthCode, map,false,new ResultInterface() {
				
				@Override
				public void onSuccess(Object response) {
					// TODO Auto-generated method stub
					System.out.println("LHQ====验证码返回结果=="+ response);
					JSONObject jsonObject = JsonUtil.parseJSONObject((String)response);
					String content = JsonUtil.getJSONString(jsonObject, "Content");
					if ("".equals(content)) {
						AlertUtil.ToastMessageShort(activity, "手机动态码发送成功，请注意查收！");
					}else {
						AlertUtil.ToastMessageShort(activity, content);
					}
				}
				
				@Override
				public void onError(Object errorMsg) {
					// TODO Auto-generated method stub
					System.out.println("LHQ====验证码返回结果==222222222");
					Toast.makeText(activity, (String)errorMsg, Toast.LENGTH_LONG).show();
				}
			});
			return true;
		}
		
	}
	
	/**
	 * 找回密码的交易发送短信验证码方法
	 * @param activity
	 * @param mobilePhone
	 * @param TokenMessage
	 * @return
	 */
	public boolean SendSMSAuthTradeFogetPassword(final BaseFragmentActivity activity,String mobilePhone,String TokenMessage){
		if ("".equals(mobilePhone)) {
			AlertUtil.ToastMessageShort(activity, "手机号码不能为空！");
			return false;
		}else if (mobilePhone.length()<11) {
			AlertUtil.ToastMessageShort(activity, "手机号码位数不正确！");
			return false;
		}else if (!mobilePhone.matches("^[1][3-8]+\\d{9}")) {
			AlertUtil.ToastMessageShort(activity, "手机号码格式不正确！");
			return false;
		}else {
			Map<String, String> map = new HashMap<String, String>();
//			map.put(Constant.KEY_BANKID, Constant.BankId);
			map.put(Constant.KEY_TOKENINDEX, "1");
			map.put(Constant.KEY_TOKENMESSAGE, TokenMessage);
//			map.put(Constant.KEY_EXISTCHECKFLAG, "false");
			map.put(Constant.KEY_MOBILEPHONE, mobilePhone);
			map.put(Constant.KEY_LOCALE, "zh_CN");
			map.put("NotExistFlag", "true");
			activity.requestPost(HttpMiddleWare.Trade_SMSAuthCode, map,false,new ResultInterface() {
				
				@Override
				public void onSuccess(Object response) {
					// TODO Auto-generated method stub
					System.out.println("LHQ====验证码返回结果=="+ response);
					JSONObject jsonObject = JsonUtil.parseJSONObject((String)response);
					String content = JsonUtil.getJSONString(jsonObject, "Content");
					if ("".equals(content)) {
						AlertUtil.ToastMessageShort(activity, "手机动态码发送成功，请注意查收！");
					}else {
						AlertUtil.ToastMessageShort(activity, content);
					}
				}
				
				@Override
				public void onError(Object errorMsg) {
					// TODO Auto-generated method stub
					System.out.println("LHQ====验证码返回结果==222222222");
				}
			});
			return true;
		}
		
	}
	
}
