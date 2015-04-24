package com.yld.core.httphelper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.http.ResultInterface;
import com.yld.core.http.httpControl;
import com.yld.core.http.volley.toolbox.NetworkImageView;
import com.yld.core.utils.AlertUtil;
import com.yld.core.utils.Constant;
import com.yld.core.utils.DeviceUtil;
import com.yld.core.utils.JsonUtil;
import com.yld.yytxapp.ui.HomeActivity;

/**
 * @brief 与手机银行服务器通讯中间件，处理与服务器交互操作，转换报文格式等。
 * */
public class HttpMiddleWare
{

    /**
     * @brief 手机银行服务器ip+端口号+工程名
     * */
    // public static String initIp = "http://172.16.0.45:9090/portal/";//小卢
    // public static String initIp = "http://172.16.0.22:9104/portal/";
    // public static String initIp = "http://172.16.0.23:9088/portal/";//丁毅
    // public static String initIp = "http://172.16.0.19:9104/portal/";//菜金虎
    // public static String initIp = "http://172.16.0.17:9104/portal/";//先宇
    // public static String initIp = "http://172.16.64.229:9104/portal/";// 测试环境
    // public static String initIp = "https://zhixiao.cqcbank.com/portal/";// 内部上线
    // public static String initIp = "http://172.16.64.70:3201/portal/";// 测试环境
    // public static String initIp = "http://172.16.0.18:9104/portal/";//唐顺利

 
     public static String initIp = "http://192.168.1.105:8080/yld-base-webapp-usermodule/oauth2/";// 南京银行测试地址

    /** 登录接口 */
    public static final String Trade_Login = "token";
    /** 登录接口用于注册跳转用 */
    public static final String Trade_Login2 = "login2.do";
    /** 签退接口 */
    public static final String Trade_SignOut = "logout.do";
    /** 图片验证码接口 */
    public static final String Trade_VerifyImage = "GenTokenImg.do";
    /** 时间戳接口 */
    public static final String Trade_Timestamp = "Timestamp.do";
    /** 放重复提交码接口 */
    public static final String Trade_GenToken = "GenToken.do";
    public static final String Trade_webindex = "samples/index.html";// web模块入口页
    public static final String HttpError = "jsonError";// 用来判断返回成功和失败
    public static final String ErrorKey = "_exceptionMessage";// error key
    public static final String ErrorKeyCode = "_exceptionMessageCode";// error
                                                                      // key
    public static final String TRADE_TRANSFEROUTCONFIRM = "TransferOutConfirm.do";// error
    public static final String TRADE_SCHEDULETRANSFEROUTCONFIRM = "ScheduleTransferOutConfirm.do";// 异常处理交易接口
    // key
    public static final String TRADE_TRANSFEROUT = "TransferOut.do";// error key
    public static final String TRADE_SCHEDULETRANSFEROUT = "ScheduleTransferOut.do";// 异常处理交易接口y
    public static final String TRADE_AUTOGENPHONETOKEN = "AutoGenPhoneToken.do";// 资金转出手机验证码
    public static final String TRADE_EACCTDETAILQUERYPRE = "EacctDetailQueryPre.do";//
    public static final String TRADE_GENTOKEN = "GenToken.do";// 防止重复提交
    public static final String TRADE_UPDATE = "MobilePhoneVersionQuery.do";// 客户端版本检测
    public static final String TRADE_BANKLIST = "SupportBankList.do";// 支持银行卡列表
    public static final String TRADE_ACTIVETIXIANCARD = "ActiveTiXianCard.do";// 我的账户待验证接口
    // 优惠活动url
    public static final String favorableUrl = "";
    /**
     * 乐惠存接口
     */
    public static final String DEPOSITBALINFOQRYKEY = "PliesRichesBalInfoQry.do";
    public static final String DEPOSITSIGNKEY = "PliesRichesSign.do";
    public static final String DEPOSITACTBANLANCEQRYKEY = "PliesRichesActBalanceQry.do";
    public static final String DEPOSITDETAILSBALQRYKEY = "PliesRichesDetailsBalQry.do";
    public static final String DEPOSITBREAKCONFIRMKEY = "PliesRichesBreakConfirm.do";
    public static final String DEPOSITSIGNPRODUCTINTROKEY = "PliesRichesSignProductIntro.do";
    public static final String DEPOSITSIGNCONFIRMKEY = "PliesRichesSignConfirm.do";
    public static final String DEPOSITEARNINGSQRYPREKEY = "PliesRichesEarningsQryPre.do";
    public static final String DEPOSITEARNINGSQRYKEY = "PliesRichesEarningsQry.do";
    public static final String DEPOSITBREAKKEY = "PliesRichesBreak.do";
    public static final String DEPOSITTRSQRYPREKEY = "PliesRichesTrsQryPre.do";
    public static final String DEPOSITTRSQRYKEY = "PliesRichesTrsQry.do";
    public static final String DEPOSITSIGNJLBQRYKEY = "PliesRichesSignJlbQry.do";
    public static final String DEPOSITRATEQRYKEY = "PliesRichesRateQry.do";
    // public static final String DEPOSITREAMOREPHONEKEY=initIp+"PliesRichesReaMorePhone.do";

   
    public static final String HOLDFUND = "MemberFund.do"; //持有查询
    public static final String BEFOREENTRUST = "FinancingTrsList.do"; //当前委托

    public static final String DEPOSITREAMOREPHONEKEY = "file:///android_asset/CheerfulDepositWhat.html";

    public static final String DIYLOANWHATKEY = "file:///android_asset/diy_loan_what.html";

    public static final String GATHERPROFITWHATKEY = "file:///android_asset/gather_profit_what.html";
    /**
     * 短信验证码接口
     * 
     * @参数 手机号码：MobilePhone,标示：TokenMessage：,ExistCheckFlag:false,BankId:9999, TokenIndex:1
     * */
    public static final String Trade_SMSAuthCode = "GenPhoneTokenForPublic.do";
    /**
     * 个人信息变更：个人信息查询接口
     * 
     * @参数 无
     * */
    public static final String Trade_UserInfoQuery = "InfoModifyPre.do";
    /**
     * 个人信息变更：个人信息变更提交接口
     * 
     * @参数 手机动态码：_pTokenName+客户信息（字段参照个人信息返回接口）
     * */
    public static final String Trade_UserInfoResetSubmit = "InfoModify.do";
    /**
     * 个人信息变更：个人信息确认接口
     * 
     * @参数 客户信息（字段参照个人信息返回接口）
     * */
    public static final String Trade_UserInfoConfirm = "InfoModifyConfirm.do";
    /**
     * 绑定卡变更：开户行查询接口
     * 
     * @参数 新卡号：AccountNo,BankId:9999
     * */
    public static final String Trade_CardBankQuery = "queryBankInfoLogOn.do";
    /**
     * 绑定卡变更：变更卡号接口
     * 
     * @参数 新卡号NewAcNo，交易密码TrsPassword，开户行BankName，手机验证码_pTokenName，1本行或0他行BankInner
     * */
    public static final String Trade_CardResetSubmit = "RelaAcctUpd.do";
    /**
     * 绑定卡变更：信息查询接口
     * 
     * @参数 无
     * */
    public static final String Trade_CardResetInfo = "RelaAcctUpdConfirm.do";
    /**
     * 绑定手机号变更：电子账户和手机号码获取接口
     * 
     * @参数 无
     * */
    public static final String Trade_PhoneInfoGet = "BindMobPhoneModifyPConfirm.do";
    /**
     * 绑定卡变更：电话号码校验接口
     * 
     * @参数 手机号码：MobilePhone 短信验证码：_pTokenName
     * */
    public static final String Trade_PhoneOldAuth = "BindMobPhoneModifyConfirm.do";
    /**
     * 绑定卡变更：绑定电话号码变更接口
     * 
     * @参数 新手机号码：NewMobilePhone 短信验证码：_pTokenName
     * */
    public static final String Trade_PhoneResetSubmit = "BindMobPhoneModify.do";
    /**
     * 登录密码修改：登录密码修改接口
     * 
     * @参数 原登录密码OldPassword，新登录密码NewPassword，确认登录密码ConfirmPassword，手机动态码_pTokenName
     * */
    public static final String Trade_PassLoginReset = "PassModify.do";
    /**
     * 登录密码修改：首交易接口
     * 
     * @参数 无
     * */
    public static final String Trade_PassLoginInfo = "PassModifyConfirm.do";
    /**
     * 交易密码修改：交易密码修改接口
     * 
     * @参数 原交易密码TrsPassword，新交易密码NewTrsPassword，确认交易密码ConfirmTrsPassword， 手机动态码_pTokenName
     * */
    public static final String Trade_PassTrsReset = "EAcPswReset.do";
    /**
     * 交易密码修改：交易密码重置接口
     * 
     * @参数 姓名UserName，证件号码IdNo，证件类型IdType，交易密码NewTrsPassword，确认交易密码ConfirmTrsPassword， 手机动态码_pTokenName，Activation=1
     * */
    public static final String Trade_PassTradeReset = "EAcPswModify.do";
    /**
     * 交易密码修改：首交易接口
     * 
     * @参数 无
     * */
    public static final String Trade_PassTradeInfo = "EAcPswModify.do";
    /**
     * 重置手机号码：交易密码
     * 
     * @参数 无
     */
    public static final String Trade_PhoneTradeInfo = "BindMobPhoneModifyToPassword.do";
    /**
     * 重置手机号码：录入新手机号
     * 
     * @参数 无
     */
    public static final String Trade_PhoneTradeReset = "BindMobPhoneModify.do";

    /**
     * 注册用户：首交易接口
     * 
     * @参数 无
     * */
    public static final String Trade_RegisterInfo = "RegisterConfirm.do";
    /**
     * 注册用户：注册提交接口
     * 
     * @参数 无
     * */
    public static final String Trade_RegisterSubmit = "Register.do";
    /**
     * 注册用户：注册提交接口行内
     * 
     * @参数 无
     * */
    public static final String Trade_RegisterSubmitBankInner = "RelaAcctRegister.do";
    /**
     * 注册用户：注册提交接口行外
     * 
     * @参数 无
     * */
    public static final String Trade_RegisterSubmitBankOut = "RelaAcctRegisterV1.do";
    /**
     * 注册用户：查询行号接口
     * 
     * @参数 无
     * */
    public static final String Trade_RegisterQueryBankInfo = "queryBankInfo.do";
    /**
     * 密码找回：首交易接口
     * 
     * @参数 无
     * */
    public static final String Trade_ForgetPasswordInfo = "ResetUserPwdConfirm.do";
    /**
     * 密码找回：信息验证接口
     * 
     * @参数 无
     * */
    public static final String Trade_ForgetPasswordAuth = "UserPhoneValidate.do";
    /**
     * 密码找回：密码找回提交接口
     * 
     * @参数 无
     * */
    public static final String Trade_ForgetPasswordSubmit = "ResetUserPwd.do";
    /**
     * DIY贷：抵押贷款 省份列表 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_PledgeProvince = "ProvicetList.do";
    /**
     * DIY贷：抵押贷款 城市列表 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_PledgeCity = "CityAreaList.do";
    /**
     * DIY贷：抵押贷款 区列表 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_PledgeDistrict = "AreaList.do";
    /**
     * DIY贷：抵押贷款 网点列表 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_PledgePoint = "AreaAddrQuery.do";
    /**
     * DIY贷：抵押贷款 交易 利率 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_InterestCal = "LoanInterestCal.do";
    /**
     * DIY贷：抵押贷款 交易 申请 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_PledgeLoanConfirm = "ZyLoanApplyConfirm.do";
    /**
     * DIY贷：抵押贷款 交易 提交 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_PledgeLoanSubmit = "ZyLoanApply.do";
    /**
     * DIY贷：信用贷款 交易 申请 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_CreditLoanConfirm = "XyLoanApplyConfirm.do";
    /**
     * DIY贷：信用贷款 交易 提交 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_CreditLoanSubmit = "XyLoanApply.do";
    /**
     * DIY贷：其他贷款 交易 申请 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_OtherLoanConfirm = "FrLoanApplyConfirm.do";
    /**
     * DIY贷：其他贷款 交易 提交 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_OthertLoanSubmit = "FrLoanApply.do";
    /**
     * DIY贷：贷款 查看申请 接口
     * 
     * @参数 无
     * */
    public static final String Trade_DIY_LoanApplyQuery = "LoanApplyQry.do";

    /**
     * 我的账户交易
     */
    public static final String TRADE_ERRORKEY = "EAcctInfoQry.do";// error key
    public static final String TRADE_TRANSFEROUTPRE = "TransferOutPre.do";// error
                                                                          // key

    /**
     * 绑定提现卡交易
     */
    public static final String RelaAcctAddPre = "RelaAcctAddPre.do";// 银行列表查询
    public static final String RelaAcctListQry = "RelaAcctListQry.do";// 我的银行卡列表查询
    public static final String RelaAcctAdd = "RelaAcctAdd.do";// 绑定提现卡
    public static final String QueryBankCZtype = "QueryBankCZtype.do";// 根据银行名称查询支持的充值方式
    /**
     * 聚利宝交易
     */
    public static final String TRADE_JULIBAOSIGNPRE = "JuliBaoSignPre.do";// 聚利宝签约前
    public static final String TRADE_JULIBAOSIGN = "JuliBaoSign.do";// 聚利宝签约
    public static final String TRADE_JULIBAOUNSIGNCONFIRM = "JuliBaoUnSignConfirm.do";// 聚利宝解约前数据
    public static final String TRADE_JULIBAOUNSIGN = "JuliBaoUnSign.do";// 聚利宝解约
    public static final String TRADE_DATEPARAMQRY = "DateParamQry.do";// 聚利宝交易查询日期
    public static final String TRADE_JULIBAOTRSDETAILQRY = "JuliBaoTrsDetailQry.do";// 聚利宝余额交易查询
    public static final String TRADE_JULIBAOPROFITLIST = "JuliBaoProfitList.do";// 聚利宝收益查询
    public static final String TRADE_JULIBAOHISTRATEQRY = "JuliBaoHistRateQry.do";// 聚利宝收益率查询
    public static final String TRADE_VALIDATIONISSIGN = "ValidationIsSign.do";// 是否签约乐惠存
    public static final String TRADE_AVAILBALQRY = "AvailBalQry.do";// 出金余额查询
    public static final String TRADE_JULIBAOHISTRATEQRY4PUBLIC = "JuliBaoHistRateQry4Public.do";// 聚利宝展示页的数据查询接口

    /**
     * 我的账户
     */
    public static final String TRADE_PROVINCELISTQRY = "cnaps.ProvinceListQry.do";// 资金转出省份
    public static final String TRADE_CITYLISTQRY = "cnaps.CityListQry.do";// 资金转出市区
    public static final String TRADE_BANKLISTQRY = "cnaps.BankListQry.do";// 资金转出网点
    public static final String TRADE_EACTRSHISTORYQRY = "EAcTrsHistoryQry.do";// 交易明细查询
    /**
     * 我的账户njzx
     */
    public static final String EAcctInfoQry = "EAcctInfoQry.do";// 电子账户查询 余额查询
    public static final String WithdrawPre = "WithdrawPre.do";// 提现卡激活
    public static final String ActiveTiXianCard = "ActiveTiXianCard.do";// 提现卡激活
    public static final String Withdraw = "Withdraw.do";// 提现
    /**
     * 聚宝盆njzx 充值
     */
    public static final String PayTransfer = "PayTransfer.do";
    /**
     * 聚宝盆 解绑
     */
    public static final String RelaAcctDel = "RelaAcctDel.do";
    /**
     * 聚宝盆 帮卡银行查询
     */
    public static final String RelaAcctAddCZPre = "RelaAcctAddCZPre.do";
    /**
     * 聚宝盆绑定充值卡
     */
    public static final String RelaAcctCzAdd = "RelaAcctCzAdd.do";
    /**
	 * 智能充值 查询（定期存款）
	 */
	public static final String RegularAndCurrentConfirm = "RegularAndCurrentConfirm.do";
	/**
	 * 智能充值  充值交易
	 */
	public static final String CurrentToRegular = "CurrentToRegular.do";

    /********************************* 易得利相关交易 Start ************************************/
    /**
     * 查询易得利是否开通
     */
    public static final String YiDeLiRegConfirm = "YiDeLiRegConfirm.do";

    /**
     * 易得利开通、关闭
     */
    public static final String YiDeLiRegisterAndClose = "YiDeLiRegisterAndClose.do";
    /********************************* 易得利相关交易 Start ************************************/

    public BaseFragmentActivity activity;
    public Context context;
    public httpControl httpControl;

    public HttpMiddleWare(BaseFragmentActivity activity)
    {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.httpControl = new httpControl(context);
    }

    public void stopRequestQueue()
    {
        if (httpControl != null)
        {
            httpControl.stopRequestQueue();
            httpControl = null;
        }
    }

    /**
     * @brief get请求服务器
     * @param trade 请求交易名
     * @param map 请求参数
     * @param resultInterface 请求结果回调方法
     * */
    public void get(String trade, Map<String, String> map, final boolean isAlowHint, final ResultInterface resultInterface)
    {
        if (!DeviceUtil.IsNetWork(context))
        {
            AlertUtil.ToastMessageShort(context, "网络异常，请检查手机网络！");
            return;
        }
        String params = "?_ChannelId=" + Constant.ChannelId + "&LoginType=" + Constant.LoginType + "&BankId=" + Constant.BankId;
        httpControl.HttpExcute(initIp + trade + params, httpControl.RequestGet, map, new ResultInterface()
        {
            @Override
            public void onSuccess(Object response)
            {
                // TODO Auto-generated method stub
                if (resultInterface != null)
                {
                    parseResponse(response, isAlowHint, resultInterface);
                }
            }

            @Override
            public void onError(Object errorMsg)
            {
                // TODO Auto-generated method stub
                requestErrorHint(activity, isAlowHint, (String) errorMsg);
                if (resultInterface != null)
                {
                    resultInterface.onError(errorMsg);
                }
            }
        });
    }

    /**
     * @brief post请求服务器
     * @param trade 请求交易名
     * @param map 请求参数
     * @param resultInterface 请求结果回调方法
     * */
    public void post(String trade, Map<String, String> map, final boolean isAlowHint, final ResultInterface resultInterface)
    {
        if (!DeviceUtil.IsNetWork(context))
        {
            AlertUtil.ToastMessageShort(context, "网络异常，请检查手机网络！");
            return;
        }
        if (map == null)
        {
            map = new HashMap<String, String>();
//            map.put("_ChannelId", Constant.ChannelId);
//            map.put("LoginType", Constant.LoginType);
//            map.put("BankId", Constant.BankId);
        }
        else
        {
//            map.put("_ChannelId", Constant.ChannelId);
//            map.put("LoginType", Constant.LoginType);
//            map.put("BankId", Constant.BankId);
        }
        httpControl.HttpExcute(initIp + trade, httpControl.RequestPost, map, new ResultInterface()
        {
            @Override
            public void onSuccess(Object response)
            {
                // TODO Auto-generated method stub
                if (resultInterface != null)
                {
                    parseResponse(response, isAlowHint, resultInterface);
                }
            }

            @Override
            public void onError(Object errorMsg)
            {
                // TODO Auto-generated method stub
                requestErrorHint(activity, isAlowHint, (String) errorMsg);
                if (resultInterface != null)
                {
                    resultInterface.onError(errorMsg);
                }
            }
        });
    }

    /**
     * @brief 图片异步加载
     * @param trade 交易名
     * @param imageView 图片显示view对象
     * @param defaultImageResId 默认图片id交
     * @param errorImageResId 请求错误情况图片id
     * */
    public void ImageExcute(String trade, ImageView imageview, int defaultImageResId, int errorImageResId)
    {
        if (!DeviceUtil.IsNetWork(context))
        {
            AlertUtil.ToastMessageShort(context, "网络异常，请检查手机网络！");
            return;
        }
        httpControl.ImageExcute(initIp + trade, imageview, defaultImageResId, errorImageResId);
    }

    /**
     * @brief 图片异步加载
     * @param trade 交易名
     * @param networkimageview 图片显示view对象( volley提供的封装的imageview )
     * @param defaultImageResId 默认图片id
     * @param errorImageResId 请求错误情况图片id
     * */
    public void ImageExcute(String trade, NetworkImageView networkimageview, int defaultImageResId, int errorImageResId)
    {
        if (!DeviceUtil.IsNetWork(context))
        {
            AlertUtil.ToastMessageShort(context, "网络异常，请检查手机网络！");
            return;
        }
        httpControl.ImageExcute(initIp + trade, networkimageview, defaultImageResId, errorImageResId);
    }

    /**
     * @brief 图片异步加载
     * @param trade 交易名
     * @param map 请求参数
     * @param resultInterface 请求结果回调方法（回调返回类型bitmap）
     * */
    public void ImageExcute(String trade, Map<String, String> map, final boolean isAlowHint, final ResultInterface resultInterface)
    {
        if (!DeviceUtil.IsNetWork(context))
        {
            AlertUtil.ToastMessageShort(context, "网络异常，请检查手机网络！");
            return;
        }
        if (map == null)
        {
            map = new HashMap<String, String>();
            map.put("_ChannelId", Constant.ChannelId);
            map.put("LoginType", Constant.LoginType);
            map.put("BankId", Constant.BankId);
        }
        else
        {
            map.put("_ChannelId", Constant.ChannelId);
            map.put("LoginType", Constant.LoginType);
            map.put("BankId", Constant.BankId);
        }
        httpControl.ImageExcute(initIp + trade, map, new ResultInterface()
        {

            @Override
            public void onSuccess(Object response)
            {
                // TODO Auto-generated method stub
                if (resultInterface != null)
                {
                    resultInterface.onSuccess(response);
                }
            }

            @Override
            public void onError(Object errorMsg)
            {
                // TODO Auto-generated method stub
                requestErrorHint(activity, isAlowHint, (String) errorMsg);
                if (resultInterface != null)
                {
                    resultInterface.onError(errorMsg);
                }
            }
        });
    }

    /**
     * @brief 服务器返回数据格式转换
     * @param response 服务器返回字符串数据
     * @return String
     * */
    public String formatConversion(String response)
    {
        return response;
    }

    /**
     * 验证请求结果成功或失败
     * */
    public void parseResponse(Object response, Boolean isAlowHint, ResultInterface resultInterface)
    {
        if ("".equals((String) response))
        {
            // requestErrorHint(activity, isAlowHint, "通讯错误，请稍后再试！");
            return;
        }
        if (((String) response).indexOf("<html") > 0)
        {
            requestErrorHint(activity, isAlowHint, "服务器返回报文格式异常!");
            return;
        }
        JSONObject repJson = JsonUtil.parseJSONObject((String) response);
        if (repJson.containsKey(HttpError))
        {
            JSONArray errorArray = repJson.getJSONArray(HttpError);
            if (errorArray != null && errorArray.size() > 0)
            {
                JSONObject jsonObject = (JSONObject) errorArray.get(0);
                String errorCode = jsonObject.getString("_exceptionMessageCode");
                if (!StringUtils.isEmpty(errorCode) && errorCode.equals("GA10015"))
                {// 资金转出特殊处理
                    resultInterface.onError(jsonObject.getString(ErrorKey) + jsonObject.getString(ErrorKeyCode));
                }
                else
                {
                    resultInterface.onError(jsonObject.getString(ErrorKey));
                }
                requestErrorHint(activity, isAlowHint, ((JSONObject) errorArray.get(0)).getString(ErrorKey));
            }
            else
            {
                resultInterface.onError("通讯错误，请稍后再试！");
                requestErrorHint(activity, isAlowHint, "通讯错误，请稍后再试！");
            }
        }
        else if (repJson.containsKey("logoutFlag"))
        {
            requestErrorHint(activity, isAlowHint, "会话已超时");
        }
        else
        {
            // if (activity.dialog != null && activity.dialog.isShowing()) {
            // activity.dialog.dismiss();
            // }
            resultInterface.onSuccess(formatConversion((String) response));

        }
    }

    /**
     * 请求失败处理
     * */
    public void requestErrorHint(BaseFragmentActivity activity, boolean isAlowHint, String errorMsg)
    {
        if ("会话已超时".equals(errorMsg))
        {
            errorMsg = errorMsg + ",请重新登录！";
            activity.constant.setUserInfo(null);
            activity.constant.setLogin(false);
            if (httpControl != null)
            {
                httpControl.ClearCookie();
            }
            activity.StartActivity(HomeActivity.class, null);
        }
        if (activity.dialog != null && activity.dialog.isShowing())
        {
            activity.dialog.dismiss();
        }
        if (isAlowHint)
        {
//            AlertUtil.ToastMessageShort(activity.getApplicationContext(), (String) errorMsg);
            AlertUtil.ShowHintDialog(activity, "提示", errorMsg, "确定", true, new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
				}
			});
        }
    }
}
