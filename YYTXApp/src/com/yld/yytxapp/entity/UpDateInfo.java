package com.yld.yytxapp.entity;

public class UpDateInfo {

	/**@brief 更新方式（0：非强制更新，1：强制更新） */
	public String UpdateMode = "";
	/**@brief 更新客户端下载url */
	public String UpdateUrl = "";
	/**@brief 更新客户端描述*/
	public String UpdateHint = "";
	/**@brief 新客户端 更新版本名称*/
	public String UpdateVersionName = "";
	/**@brief 营销方式  0：不营销  、1：营销*/
	public String JumpFlag = "";
	/**@brief 营销图片下载url*/
	public String ImageURL = "";
	/**@brief 营销地址url*/
	public String JumpURL = "";
	
	public String getUpdateMode() {
		return UpdateMode;
	}
	public void setUpdateMode(String updateMode) {
		UpdateMode = updateMode;
	}
	public String getUpdateUrl() {
		return UpdateUrl;
	}
	public void setUpdateUrl(String updateUrl) {
		UpdateUrl = updateUrl;
	}
	public String getUpdateHint() {
		return UpdateHint;
	}
	public void setUpdateHint(String updateHint) {
		UpdateHint = updateHint;
	}
	public String getUpdateVersionName() {
		return UpdateVersionName;
	}
	public void setUpdateVersionName(String updateVersionName) {
		UpdateVersionName = updateVersionName;
	}
	public String getJumpFlag() {
		return JumpFlag;
	}
	public void setJumpFlag(String jumpFlag) {
		JumpFlag = jumpFlag;
	}
	public String getImageURL() {
		return ImageURL;
	}
	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}
	public String getJumpURL() {
		return JumpURL;
	}
	public void setJumpURL(String jumpURL) {
		JumpURL = jumpURL;
	}
	
}
