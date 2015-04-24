package com.yld.core.http;

/**
 * @brief 请求结果回调接口
 * */
public interface ResultInterface {
	/**
	 * @brief 请求成功返回数据
	 * */
	public void onSuccess(Object response);

	/**
	 * @brief 请求异常
	 * */
	public void onError(Object errorMsg);
}