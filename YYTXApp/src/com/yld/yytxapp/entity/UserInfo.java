package com.yld.yytxapp.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{

	/**@brief 用户名称   */
	public String UserName = "";
	/**@brief 密码   */
	public String Password = "";
	/**@brief 授权码*/
	public String Access_token = "";
	/**@brief 超时时间*/
	public String Expired = "";
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getAccess_token() {
		return Access_token;
	}
	public void setAccess_token(String access_token) {
		Access_token = access_token;
	}
	public String getExpired() {
		return Expired;
	}
	public void setExpired(String expired) {
		Expired = expired;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	
}
