package com.yld.yytxapp.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{

	/**@brief 用户名称   字段 UserName */
	public String UserName = "";
	/**@brief 用户电子账户*/
	public String UserAccount = "";
	/**@brief 用户绑定卡号*/
	public String UserCardNumber = "";
	/**@brief 用户性别*/
	public String Gender = "";
	/**@brief 上次登录时间*/
	public String LastLoginTime = "";
	/**@brief 总登录次数*/
	public String LoginTimes = "";
	/**@brief 当天登录次数*/
	public String LoginCount = "";
	/**@brief 用户性别*/
	public String UserSex = "";
	/**@brief 用户开户机构*/
	public String UserAccountOrganization = "";
	/**@brief 是否显示欢迎页*/
	public boolean isShowWelcome = false;
	/**@brief 用户    客户号   字段：UserSeq*/
	public String UserSeq = "";
	/**@brief 用户    手机号   字段：MobilePhone*/
	public String MobilePhone = "";
	/**@brief 用户    常住地址   字段:Addr*/
	public String Address = "";
	/**@brief 用户    邮编   字段：Post*/
	public String Post = "";
	/**@brief 用户    职业   字段：UserDuty*/
	public int UserDuty = 0;
	/**@brief 用户    固定电话  字段：Phone*/
	public String Phone = "";
	/**@brief 用户    邮箱  字段：Email*/
	public String Email = "";
	/**@brief 用户信息修改   是否需要短信验证码  字段：_AuthenticateType  */
	public String AuthenticateType = "";
	
	/**@brief LoginId */
	public String LoginId = "";
	
	/**@brief 是否开户 */
	public boolean isOpenAccount ;
	
	/**@brief 是否绑定提现卡 */
	public boolean hasTxCard ;
	
	/**@brief 是否绑定充值卡 */
	public boolean hasCzCard  ;
	
	/**@brief 实名认证状态 */
	public String uploadIdentity;
	
	/**@brief 电子帐户卡号 */
	public String virtualAcNo;
	
	/**@brief 电子帐户对象json串 */
	public String eAcct;
	
	
	
	public String geteAcct() {
		return eAcct;
	}
	public void seteAcct(String eAcct) {
		this.eAcct = eAcct;
	}
	public String getUploadIdentity() {
		return uploadIdentity;
	}
	public void setUploadIdentity(String uploadIdentity) {
		this.uploadIdentity = uploadIdentity;
	}
	public String getVirtualAcNo() {
		return virtualAcNo;
	}
	public void setVirtualAcNo(String virtualAcNo) {
		this.virtualAcNo = virtualAcNo;
	}
	public boolean isOpenAccount() {
		return isOpenAccount;
	}
	public void setOpenAccount(boolean isOpenAccount) {
		this.isOpenAccount = isOpenAccount;
	}
	public boolean isHasTxCard() {
		return hasTxCard;
	}
	public void setHasTxCard(boolean hasTxCard) {
		this.hasTxCard = hasTxCard;
	}
	public boolean isHasCzCard() {
		return hasCzCard;
	}
	public void setHasCzCard(boolean hasCzCard) {
		this.hasCzCard = hasCzCard;
	}
	public String getLoginId() {
		return LoginId;
	}
	public void setLoginId(String loginId) {
		LoginId = loginId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserAccount() {
		return UserAccount;
	}
	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}
	public String getUserCardNumber() {
		return UserCardNumber;
	}
	public void setUserCardNumber(String userCardNumber) {
		UserCardNumber = userCardNumber;
	}
	public String getLastLoginTime() {
		return LastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}
	public String getLoginCount() {
		return LoginCount;
	}
	public void setLoginCount(String loginCount) {
		LoginCount = loginCount;
	}
	public String getUserSex() {
		return UserSex;
	}
	public void setUserSex(String userSex) {
		UserSex = userSex;
	}
	public String getUserAccountOrganization() {
		return UserAccountOrganization;
	}
	public void setUserAccountOrganization(String userAccountOrganization) {
		UserAccountOrganization = userAccountOrganization;
	}
	public boolean isShowWelcome() {
		return isShowWelcome;
	}
	public void setShowWelcome(boolean isShowWelcome) {
		this.isShowWelcome = isShowWelcome;
	}
	public String getUserSeq() {
		return UserSeq;
	}
	public void setUserSeq(String userSeq) {
		UserSeq = userSeq;
	}
	public String getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPost() {
		return Post;
	}
	public void setPost(String post) {
		Post = post;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getUserDuty() {
		return UserDuty;
	}
	public void setUserDuty(int userDuty) {
		UserDuty = userDuty;
	}
	public String getAuthenticateType() {
		return AuthenticateType;
	}
	public void setAuthenticateType(String authenticateType) {
		AuthenticateType = authenticateType;
	}
	public String getLoginTimes() {
		return LoginTimes;
	}
	public void setLoginTimes(String loginTimes) {
		LoginTimes = loginTimes;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	
	
}
