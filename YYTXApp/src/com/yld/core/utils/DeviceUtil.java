package com.yld.core.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * @author 田裕杰
 * @brief 提供一些方法 用于获取移动设备信息
 * */
public class DeviceUtil {
	/**
	 * @biref 获取手机型号
	 * 
	 * @return String
	 */
	public static String getModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * @biref 获取手机MAC地址
	 * @param context
	 *            上下文
	 * @return String
	 */
	public static String getMac(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifi.getConnectionInfo();

		return info.getMacAddress();
	}

	/**
	 * @brief 获取手机设别android SDK版本号
	 * 
	 * @return int
	 * */
	public static int getSDKVersion() {
		int version = 0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return version;
	}

	/**
	 * @brief 硬件设备类型
	 * 
	 * @return String
	 * */
	public static String getDeviceType() {
		return "ANDROID";
	}

	/**
	 * @brief 获取手机设备IMEI
	 * @param context
	 *            上下文
	 * @return String
	 * */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService("phone");
		return tm.getDeviceId();
	}

	/**
	 * @brief 获取手机屏幕尺寸
	 * @param context
	 *            上下文
	 * @return String(480*800)
	 */
	public static String getDisplayMetrics(Context context) {
		// String str = "";
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;// 屏幕宽（像素，如：480px）
		int screenHeight = dm.heightPixels;// 屏幕高（像素，如：800px）
		return String.valueOf(screenWidth) + "*" + String.valueOf(screenHeight);
	}

	/**
	 * @brief 获取手机屏幕尺寸 高度
	 * @param context
	 *            上下文
	 * @return int
	 */
	public static int getMetricsHeight(Context context) {
		// String str = "";
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenHeight = dm.heightPixels;// 屏幕高（像素，如：800px）
		return screenHeight;
	}

	/**
	 * @brief 获取手机屏幕尺寸 宽度
	 * @param context
	 *            上下文
	 * @return int
	 */
	public static int getMetricsWidth(Context context) {
		// String str = "";
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;// 屏幕高（像素，如：800px）
		return screenWidth;
	}

	/**
	 * @brief 判断手机当前网络开关状态 wifi gprs
	 * @param context
	 *            上下文
	 * @return boolean
	 * */
	public static boolean IsNetWork(Context context) {
		// 判断手机当前网络开关状态 wifi gprs
		boolean isnetwork = true;
		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			boolean isWifiConnected = cm.getNetworkInfo(
					ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ? true
					: false;
			boolean isGprsConnected = cm.getNetworkInfo(
					ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ? true
					: false;
			if (isWifiConnected || isGprsConnected) {
				isnetwork = true;
			} else {
				isnetwork = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			isnetwork = true;
		}
		return isnetwork;
	}

	/**
	 * @brief 获取指定Activity的截屏
	 * @param activity
	 *            activity对象
	 * @return Bitmap
	 * */
	public static Bitmap getScreenShot(Activity activity) {

		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap b1 = view.getDrawingCache();
		// 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		// 获取屏幕长和高
		int width = activity.getWindowManager().getDefaultDisplay().getWidth();
		int height = activity.getWindowManager().getDefaultDisplay()
				.getHeight();
		// 去掉标题栏
		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
		Bitmap b = Bitmap.createBitmap(b1, 0, 0, width, height);
		view.destroyDrawingCache();
		return b;
	}

	/**
	 * @brief 判断系统是否root
	 * 
	 * @return boolean
	 * */
	public static boolean isRoot() {
		boolean isroot = false;
		try {
			if (Runtime.getRuntime().exec("su").getOutputStream() == null) {
				// 没有root
				isroot = false;
			} else {
				isroot = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isroot;
	}

	/**
	 * @brief 判断sd卡是否存在
	 * 
	 * @return boolean
	 */
	public static boolean hasSDCard() {
		String SDState = android.os.Environment.getExternalStorageState();
		if (SDState.equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @brief 判断手机GPS是否开启
	 * @param context
	 *            上下文
	 * @return boolean
	 */
	public static boolean isOpenGPS(Context context) {
		LocationManager alm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			return false;
			// Intent myIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
			// mContext.startActivity(myIntent);
		}
	}

	/**
	 * @brief 获取应用程序版本号
	 * @param context
	 *            上下文
	 * @return String
	 * */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			// 获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * @brief 获取应用程序版本名
	 * @param context
	 *            上下文
	 * @return String
	 * */
	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			// 获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionName = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * @brief 获取应用程序包 MD5值
	 * @param context
	 *            上下文
	 * @return Map（key：SIGNATURE_FLAG，value：apk包签名信息；key：APKMD5_FLAG，value：apk
	 *         MD5值）
	 * */
	public static Map<String, String> getAPKMD5Info(Context context) {
		Map<String, String> map = new HashMap<String, String>();
		String SIGNATURE_FLAG = "";
		String APKMD5_FLAG = "";
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packageInfo;
		try {
			packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), PackageManager.GET_SIGNATURES);
			File f = new File(packageInfo.applicationInfo.sourceDir);
			for (Signature signature : packageInfo.signatures) {
				// 取到Package的签名
				SIGNATURE_FLAG = MD5Util
						.getMD5String(signature.toCharsString());
			}
			// 取到APKMD5
			APKMD5_FLAG = MD5Util.getFileMD5String(f);
			map.put("SIGNATURE_FLAG", SIGNATURE_FLAG);
			map.put("APKMD5_FLAG", APKMD5_FLAG);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * @brief 获取手机屏幕密度
	 * @param activity
	 *            activity对象
	 * @return int
	 * */
	public static int getMetricDpi(Activity activity) {
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
		return densityDpi;
	}

	/**
	 * @brief 获取设备信息
	 * @param context
	 *            上下文
	 * @return Map<String,String>
	 * */
	public static Map<String, String> getDeviceInfo(Context context) {
		Map<String, String> map = new HashMap<String, String>();
		map.clear();
		// IMEI
		map.put("DeviceId", getIMEI(context));
		// 判断sd卡是否存在,1存在，0不存在
		map.put("SDFlag", String.valueOf(hasSDCard()));
		// 获取设备型号
		map.put("Model", getModel());
		// 设备类型,0：手机1：平板2：电视3：其他
		map.put("DeviceType", "0");
		// Mac地址
		map.put("MacAddress", getMac(context));
		// 系统版本号
		map.put("SYSVersion", String.valueOf(getSDKVersion()));
		// 版本号
		map.put("VersionCode", String.valueOf(getVersionCode(context)));
		// 版本名称
		map.put("VersionName", getVersionName(context));
		// 客户端类型
		map.put("ClientType", "Android");
		// 屏幕信息
		map.put("DisplayMetrics", getDisplayMetrics(context));
		// Root标志0：没有root（未越狱）1：已经root（越狱）
		map.put("RootFlag", String.valueOf(isRoot()));
		return map;

	}
}
