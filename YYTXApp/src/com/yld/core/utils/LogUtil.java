package com.yld.core.utils;

import android.content.Context;
import android.util.Log;

/**
 * @brief 调试信息工具类
 * */
public class LogUtil {
	/**
	 * @description 是否是调试模式 ，调试模式下会在控制台打印log信息
	 * */
	public static boolean IsDebug = true;
	/**
	 * @brief 错误信息
	 * @param Context
	 * @param String
	 */
	public static void e(Context context, String msg) {
		e(context.getClass().getSimpleName(), msg);
	}
	/**
	 * @brief 错误信息
	 * @param String
	 * @param String
	 */
	public static void e(String tag, String msg) {
		if (IsDebug) {
			Log.e(tag, msg+"  ");
		}
	}
	/**
	 * @brief 普通信息
	 * @param Context
	 * @param Exception
	 */
	public static void i(Context context, String msg) {
		i(context.getClass().getSimpleName(), msg);
	}
	/**
	 * @brief 普通信息
	 * @param Context
	 * @param Exception
	 */
	private static void i(String tag, String msg) {
		if (IsDebug) {
			Log.i(tag, msg+"  ");
		}
	}
	/**
	 * @brief 详细信息
	 * @param Context
	 * @param String
	 */
	public static void v(Context context, String msg) {
		v(context.getClass().getSimpleName(), msg);
	}
	/**
	 * @brief 详细信息
	 * @param String
	 * @param String
	 */
	private static void v(String tag, String msg) {
		if (IsDebug) {
			Log.v(tag, msg+"  ");
		}
	}
	/**
	 * @brief DEBUG级信息
	 * @param Context
	 * @param String
	 */
	public static void d(Context context, String msg) {
		d(context.getClass().getSimpleName(), msg);
	}
	/**
	 * @brief DEBUG级信息
	 * @param Context
	 * @param String
	 */
	public static void d(String tag, String msg) {
		if (IsDebug) {
			Log.d(tag, msg+"  ");
		}
	}
	/**
	 * @brief System.out.println();信息
	 * @param Context
	 * @param String
	 */
	public static void out(Context context, String msg){
		out(context.getClass().getName(), msg);
	}
	/**
	 * @brief System.out.println();信息
	 * @param Context
	 * @param String
	 */
	private static void out(String tag, String msg){
		if (IsDebug) {
			System.out.println(tag+"----"+msg);
		}
	}
}
