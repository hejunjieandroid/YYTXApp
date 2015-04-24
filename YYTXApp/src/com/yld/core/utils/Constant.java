/**
 * 
 */
package com.yld.core.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.yld.yytxapp.entity.UserInfo;
import com.yld.yytxapp.ui.R;

/**
 * @author tyj
 * @brief 常量类 需要在清单文件中配置
 */
public class Constant extends Application
{
    /**
     * @brief 客户端是否在线版（默认在线版）
     * */
    public static boolean isOnline = true;
    /**
     * @brief 客户端是否是测试模式
     * */
    // public static boolean isDebug = false;
    public static boolean isDebug = true;
    /**
     * @brief 主界面底部选中项
     * */
    public static int bottomPress = 0;
    /**
     * sharedpreferences 存储文件名
     * */
    public static final String SPConfig = "app_config";

    public SharedPreferences preferences;

    public static final int SMS_TIME = 60;
    public static final String KEY_ACTIONID = "ActionId";// vx 浏览器传参 actionid key值
    public static final String KEY_ACTIONNAME = "ActionName";// vx 浏览器传参 title key值
    public static final int REQUESTCODE = 100;// activity回调 请求码
    public static final int RESULTCODE = 101;// activity回调 返回码
    public static final String KEY_USERINFO = "UserInfo";
    public static final String KEY_WEB_TITLE = "WebTitle";// 给自定义浏览器传参 title key值
    public static final String KEY_WEB_URL = "WebUrl";// 给自定义浏览器传参 url key值
//    public static final int contentId = R.id.content_ll;
    public static final long timeout = 35 * 1000;// 网络超时时间
    public static final String JSONRESULT = "JsonResult";// json串的key
    public static final String JSONRESULTList = "JsonResultList";// list形式的key
    public static final String JsonModel = "JSONModel";// 数据模型
    public static final String GATHERJSON = "gatherJson";// 聚利宝的json数据
    public static final String OUTFUNDJSON = "OutFundJSON";// 资金转出json数据

    public static final String KEY_USER_USERNAME = "UserName";// 用户信息报文 用户名字段
    public static final String KEY_USER_USERSEQ = "UserSeq";// 用户信息报文 客户号字段
    public static final String KEY_USER_MOBILEPHONE = "MobilePhone";// 用户信息报文 手机号字段
    public static final String KEY_USER_ADDRESS = "Addr";// 用户信息报文 常住地址字段
    public static final String KEY_USER_POST = "Post";// 用户信息报文 邮编字段
    public static final String KEY_USER_USERDUTY = "UserDuty";// 用户信息报文 职业字段
    public static final String KEY_USER_PHONE = "Phone";// 用户信息报文 固定电话字段
    public static final String KEY_USER_EMAIL = "Email";// 用户信息报文 邮箱字段
    public static final String KEY_USER_ISSMSAUTH = "_AuthenticateType";// 用户信息确认报文 是否需要短信验证码字段 000010：判断第五位 1：需要 0：不需要

    public static final String KEY_LOCALE = "_locale";// 报文 手机号字段
    public static final String KEY_MOBILEPHONE = "MobilePhone";// 报文 手机号字段
    public static final String KEY_TOKENMESSAGE = "TokenMessage";// 报文 短信验证码标示字段TokenMessage
    public static final String KEY_EXISTCHECKFLAG = "ExistCheckFlag";// 报文 短信验证码字段
    public static final String KEY_TOKENINDEX = "TokenIndex";// 报文 短信验证码字段
    public static final String KEY_BANKID = "BankId";// 报文 银行行号字段
    public static final String KEY_CARDNUMBER = "AccountNo";// 报文 卡号字段
    public static final String KEY_SMSAUTHCODE = "_pTokenName";// 报文 短信验证码字段
    public static final String KEY_BANKNAME = "BankName";// 报文 开户行字段
    public static final String KEY_QUERYDATE = "QueryDate";// 查询日期

    public static final String ChannelId = "PMBS";
    public static final String BankId = "9998";// 银行id
    public static final String LoginType = "P";// 登陆类型
    public static final String ExistCheckFlag = "false";// 短信验证码时需要上传字段
    public static final String TokenIndex = "1";// 短信验证码时需要上传字段
    public static final String TokenMessage_Card = "sms.FirstLogin.P";// 短信验证码时短信内容
    public static final String TokenMessage_LoginPw = "sms.updatepassword.msg";// 短信验证码时短信内容 登录密码修改
    public static final String TokenMessage_TradePw = "sms.modtrspassword.msg";// 短信验证码时短信内容 交易密码修改
    public static final String TokenMessage_UserInfo = "sms.UpdateInfo.U";// 短信验证码时短信内容 个人信息变更
    public static final String TokenMessage_TransferOut = "sms.TransferOut.P";// 资金转出发送短信 资金转出
    public static final String TokenMessage_Register = "sms.RegisterPre.P";// 资金转出发送短信 注册
    public static final String TokenMessage_FindPw = "sms.findPassword.P";// 资金转出发送短信 密码找回
    public static final String TokenMessage_ChangePhone = "sms.ChangePhoneNo.P";// 资金转出发送短信 变更手机号
    public static final String TokenMessage_ChangeCard = "sms.ChangeBindCard.P";// 资金转出发送短信 变更绑定卡

    public static final String ProgressHint = "加载中...";
    public static final String ProgressTimeOutHint = "数据加载失败，请检查网络！";
    public static final String SmsAuthBtnHint = "发送验证码";

    public static int INTRANCE_FUNDOUT = 0x0000;// 全局资金转出入口
    public static final int NORMAL_FUNDOUT = 0x0000;// 正常情况
    public static final int REFRESH_DATA = 0x0001;// 是否刷新数据标识
    public static final int JUBAOPEN_MSG = 201;// 聚宝盆业务
    public static final int JUBAOPEN_DEL = 202;// 聚宝盆业务删除ItemView
    public static final int JUBAOPEN_BANKS = 203;// 聚宝盆查询银行列表
    public static final int IntelligentDepositSuccess = 204;// 智能存款 （定期存款）

    public static String yearrate_json = "";// 聚利宝图片json数据

    /**
     * 易得利开通、关闭标识字段名称
     */
    public static String YDL_OPENFLAG = "OpenFlag";

    /**
     * 易得利开通状态：开通
     */
    public static String YDL_STATUS_OPEN = "1";

    /**
     * 易得利开通状态：关闭
     */
    public static String YDL_STATUS_CLOSE = "0";

    /**
     * 易得利操作类型：开通
     */
    public static String YDL_OPERATEFLAG_OPEN = "0";

    /**
     * 易得利操作类型：关闭
     */
    public static String YDL_OPERATEFLAG_CLOSE = "1";

    /**
     * 易得利响应报文“操作成功”字段名称
     */
    public static String YDL_RESULT_FLAG = "_ProcessState";

    /**
     * 易得利操作成功响应码
     */
    public static String YDL_RESULT_SUCCESS = "OK";

    /**
     * 易得利响应报文“虚拟账号”字段名称
     */
    public static String YDL_VIRTUALACNO = "VirtualAcNo";

    /**
     * 易得利响应报文“开通日期”字段名称
     */
    public static String YDL_OPEN_DATE = "CreateTime";

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
        preferences = getSharedPreferences(SPConfig, Context.MODE_PRIVATE);
    }

    /**
     * 服务器时间戳信息
     * */
    private String Timestamp = "";
    /**
     * 用户信息
     * */
    private UserInfo userInfo;
    /**
     * 登录状态
     * */
    private boolean isLogin = false;

    /**
     * SharedPreferences本地存储
     * 
     * @return boolean
     * */
    public boolean getToggleState(String key)
    {
        return preferences.getBoolean(key, false);
    }

    /**
     * SharedPreferences本地存储
     * 
     * @param boolean
     * */
    public void setToggleState(String key, boolean state)
    {
        preferences.edit().putBoolean(key, state).commit();
    }

    /**
     * SharedPreferences本地存储
     * 
     * @return String
     * */
    public String getToggleString(String key)
    {
        return preferences.getString(key, "");
    }

    /**
     * SharedPreferences本地存储
     * 
     * @param String
     * */
    public void setToggleString(String key, String value)
    {
        preferences.edit().putString(key, value).commit();
    }

    /**
     * SharedPreferences本地存储
     * 
     * @return int
     * */
    public int getToggleInt(String key)
    {
        return preferences.getInt(key, -1);
    }

    /**
     * SharedPreferences本地存储
     * 
     * @param int
     * */
    public void setToggleInt(String key, int value)
    {
        preferences.edit().putInt(key, value).commit();
    }

    public Editor getToggleEdit()
    {
        return preferences.edit();
    }

    public String getTimestamp()
    {
        return Timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        Timestamp = timestamp;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo)
    {
        this.userInfo = userInfo;
    }

    public boolean isLogin()
    {
        return isLogin;
    }

    public void setLogin(boolean isLogin)
    {
        this.isLogin = isLogin;
    }
    
    /**
	 * 用户名手机号加*，前三，后四
	 * 
	 * @author 刘淏卿
	 * @return tel 电话号码
	 */
	public static String getEncryptTel(String tel) {
		if (tel.length() > 8) {
			return tel.substring(0, 3) + "****"
					+ tel.substring(tel.length() - 4, tel.length());
		} else {
			return tel;
		}
	}
	
	/**
	 * 用户名手机号加*，前三，后四
	 * 
	 * @author 刘淏卿
	 * @return tel 电话号码
	 */
	public static String getEncrypt(String name) {
//		if (name.length() > 8) {
//			return name.substring(0, 3) + "****"
//					+ name.substring(name.length() - 4, name.length());
//		} else {
//			return name;
//		}
		
		return name.replaceFirst(name.substring(0,1), "*");
	}
	
	/**
	 * 用户名手机号加*，前三，后四
	 * 
	 * @author 刘淏卿
	 * @return tel 电话号码
	 */
	public static String getEncryptNo(String acNo) {
		
		if(!"".equals(acNo) && acNo != null ){
			return acNo.substring(acNo.length() - 4, acNo.length());
		}
			return acNo;
	}

}
