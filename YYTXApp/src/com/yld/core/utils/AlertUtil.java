/**
 * 
 */
package com.yld.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yld.core.view.UserDefinedDialog;
import com.yld.yytxapp.ui.R;

/**
 * @brief Util：控制提示信息弹出
 * @author tyj
 * 
 * 
 */
@SuppressLint("ResourceAsColor")
public class AlertUtil {

	/**
	 * @brief toast显示时间很短
	 * @param context
	 *            上下文
	 * @param message
	 *            显示内容
	 * */
	public static void ToastMessageShort(Context context, String message) {
		// Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		ToastMessage(context, message, Toast.LENGTH_SHORT);
	}

	/**
	 * @brief toast显示时间稍长
	 * @param context
	 *            上下文
	 * @param message
	 *            显示内容
	 * */
	public static void ToastMessageLong(Context context, String message) {
		// Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		ToastMessage(context, message, Toast.LENGTH_LONG);
	}

	public static void ToastMessage(Context context, String message, int duration) {
//		TextView text = new TextView(context);
//		text.setText(message);
//		text.setBackgroundResource(R.drawable.toast_bg);
//		text.setTextColor(Color.WHITE);
//		Toast toast = new Toast(context);
//		toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 80);
//		toast.setDuration(duration);
//		toast.setView(text);
//		toast.show();
		Toast.makeText(context, message, duration).show();
	}

	/**
	 * @brief 显示AlertDialog（包含确定取消按钮）
	 * 
	 * @param context
	 *            上下文
	 * @param title
	 *            dialog标题
	 * @param msg
	 *            dialog文本
	 * @param callback
	 *            回调方法（确定：Constant.DIALOG_BUTTON_CONFIRM，取消：Constant.
	 *            DIALOG_BUTTON_CANNCEL）
	 * */
	public static void ShowAlertDialog(Context context, String title, String message, boolean iserroricon, View.OnClickListener onclicklistener, View.OnClickListener cancelListener) {
		UserDefinedDialog ud = new UserDefinedDialog(context, title, message, onclicklistener, cancelListener);
		ud.setErroricon(iserroricon);
		ud.show();
		// AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setTitle(title);
		// builder.setIcon(android.R.drawable.ic_dialog_info);
		// builder.setMessage(message);
		// builder.setCancelable(false);
		// builder.setPositiveButton("确定", new OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// callback.onPositive();
		// }
		// });
		// builder.setNegativeButton("取消", new OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// callback.onNegative();
		// }
		// });
		// builder.show();
	}

	/**
	 * @brief 显示AlertDialog（包含确定取消按钮）
	 * 
	 * @param context
	 *            上下文
	 * @param title
	 *            dialog标题
	 * @param msg
	 *            dialog文本
	 * @param callback
	 *            回调方法（确定：Constant.DIALOG_BUTTON_CONFIRM，取消：Constant.
	 *            DIALOG_BUTTON_CANNCEL）
	 * */
	public static void ShowAlertDialog(Context context, String title, String message, String strleft, String strright, boolean iserroricon, View.OnClickListener onclicklistener, View.OnClickListener cancelListener) {
		UserDefinedDialog ud = new UserDefinedDialog(context, title, message, onclicklistener, cancelListener);
		ud.setErroricon(iserroricon);
		ud.setLeft(strleft);
		ud.setRight(strright);
		ud.show();

		// AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setTitle(title);
		// builder.setIcon(android.R.drawable.ic_dialog_info);
		// builder.setMessage(message);
		// builder.setCancelable(false);
		// builder.setPositiveButton(submit, new OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// callback.onPositive();
		// }
		// });
		// builder.setNegativeButton(cancel, new OnClickListener() {
		//
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// callback.onNegative();
		// }
		// });
		// builder.show();
	}

	/**
	 * @brief 显示AlertDialog(只有确定按钮)
	 * */
	public static void ShowHintDialog(Context context, String title, String message, String strcenter, boolean iserroricon, View.OnClickListener onclicklistener) {
		UserDefinedDialog ud = new UserDefinedDialog(context, title, message, onclicklistener, null);
		ud.setErroricon(iserroricon);
		ud.setButtoncenter(strcenter);
		ud.show();
		// AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// builder.setTitle(title);
		// builder.setIcon(android.R.drawable.ic_dialog_info);
		// builder.setMessage(msg);
		// builder.setCancelable(false);
		// builder.setPositiveButton("确定", new OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // TODO Auto-generated method stub
		// callback.onPositive();
		// }
		// });
		// builder.show();
	}
	// /**
	// * @brief alert回调方法
	// * */
	// public interface AlertCallBack{
	// /**
	// * @brief 确定按钮回调
	// * */
	// public void onPositive();
	// /**
	// * @brief 取消按钮回调
	// * */
	// public void onNegative();
	// }
}
