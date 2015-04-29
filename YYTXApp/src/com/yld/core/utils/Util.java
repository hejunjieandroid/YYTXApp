package com.yld.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @brief 工具类
 * */
public class Util {

	/**
	 * @brief 获取本地assets文件夹下的数据
	 * 
	 * @param name
	 *            :本地文件全名
	 * @return String：返回String数据
	 * */
	public static String getAssetsData(String name, Context context) {
		InputStream in = null;
		String res = "";
		int length;
		byte[] buffer;
		try {
			in = context.getResources().getAssets().open(name);
			// 得到数据的大小
			length = in.available();
			buffer = new byte[length];
			// 读取数据
			in.read(buffer);
			// 依test.txt的编码类型选择合适的编码，如果不调整会乱码
			res = EncodingUtils.getString(buffer, "UTF-8");
			// 关闭
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

//	public static Bundle getBundle(String kye, MenuItem value) {
//		Bundle bundle = new Bundle();
//		bundle.putSerializable(kye, value);
//		return bundle;
//	}

//	public static SecurityEditTextAttrset getLoginAttrset() {
//		// 密码键盘配置
//		SecurityEditTextAttrset attrset = new SecurityEditTextAttrset();
//		attrset.ENCRYTTYPE = 1;// 加密类型
//		attrset.KBD_TYPE = 1;// 1是英文 2是数字
//		attrset.RandomKey = 0;// 键盘是否乱序 0：不乱序 1：单行乱序 2：全乱序
//		attrset.KBDRANDOM = true;// 是否随机
//		attrset.EDITTEXT_MAXLENGTH = 20;// 输入的最大长度
//		attrset.EDITTEXT_MINLENGTH = 8;// 输入的最小长度
//		attrset.ISVIBRATOR = true;// 是否震动
//		attrset.isTouchAlert = true;// 是否显示方法效果
//		attrset.MATCHES = "^[A-Za-z]+[0-9]*[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$";
//		return attrset;
//	}

//	public static SecurityEditTextAttrset getTradeAttrset() {
//		// 密码键盘配置
//		SecurityEditTextAttrset attrset = new SecurityEditTextAttrset();
//		attrset.ENCRYTTYPE = 1;// 加密类型
//		attrset.KBD_TYPE = 2;// 1是英文 2是数字
//		attrset.RandomKey = 0;// 键盘是否乱序 0：不乱序 1：单行乱序 2：全乱序
//		attrset.KBDRANDOM = true;// 是否随机
//		attrset.EDITTEXT_MAXLENGTH = 6;// 输入的最大长度
//		attrset.EDITTEXT_MINLENGTH = 6;// 输入的最小长度
//		attrset.ISVIBRATOR = true;// 是否震动
//		attrset.isTouchAlert = true;// 是否显示方法效果
//		attrset.MATCHES = "[0-9]{6,6}";
//		return attrset;
//	}

	// 保留4位小数
	public static DecimalFormat getDecimalFormat() {
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("##0.0000");
		return myformat;
	}
	// 保留2位小数
	public static DecimalFormat getDecimalFormatTwo() {
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("##0.00");
		return myformat;
	}

	public static SpannableString getAnnual(String str) {
		SpannableString msp = new SpannableString(str);
		msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
				str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 粗体
		return msp;
	}

	public static SpannableString getThroughTxt(String str) {
		SpannableString msp = new SpannableString(str);
		msp.setSpan(new StrikethroughSpan(), 0, str.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return msp;
	}
	public static SpannableString getBigTxt(String str) {
		SpannableString msp = new SpannableString(str);
		msp.setSpan(new AbsoluteSizeSpan(30), 0, str.length(),
				 Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return msp;
	}

	public static SpannableString getSpannableString(String text) {
		// 创建一个 SpannableString对象
		SpannableString msp = new SpannableString(text);
		// 设置字体(default,default-bold,monospace,serif,sans-serif)
		// 等宽字体
		msp.setSpan(new TypefaceSpan("monospace"), 0, text.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 衬线字体
		// msp.setSpan(new TypefaceSpan("serif"), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 设置字体大小（绝对值,单位：像素）
		// msp.setSpan(new AbsoluteSizeSpan(20), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// msp.setSpan(new AbsoluteSizeSpan(20, true), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 第二个参数boolean
		// dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。

		// 设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
		// msp.setSpan(new RelativeSizeSpan(0.5f), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 0.5f表示默认字体大小的一半
		// msp.setSpan(new RelativeSizeSpan(2.0f), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 2.0f表示默认字体大小的两倍

		// 设置字体前景色
		// msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置前景色为洋红色

		// 设置字体背景色
		// msp.setSpan(new BackgroundColorSpan(Color.CYAN), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 设置背景色为青色

		// 设置字体样式正常，粗体，斜体，粗斜体
		// msp.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 0,
		// text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 正常
		// msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
		// text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 粗体
		msp.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 0,
				text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 斜体
		// msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 0,
		// text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 粗斜体

		// 设置下划线
		// msp.setSpan(new UnderlineSpan(), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 设置删除线
		// msp.setSpan(new StrikethroughSpan(), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// 设置上下标
		// msp.setSpan(new SubscriptSpan(), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 下标
		// msp.setSpan(new SuperscriptSpan(), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 上标

		// 超级链接（需要添加setMovementMethod方法附加响应）
		// msp.setSpan(new URLSpan("tel:4155551212"), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 电话
		// msp.setSpan(new URLSpan("mailto:webmaster@google.com"), 0,
		// text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 邮件
		// msp.setSpan(new URLSpan("http://www.baidu.com"), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 网络
		// msp.setSpan(new URLSpan("sms:4155551212"), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 短信 使用sms:或者smsto:
		// msp.setSpan(new URLSpan("mms:4155551212"), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 彩信 使用mms:或者mmsto:
		// msp.setSpan(new URLSpan("geo:38.899533,-77.036476"), 0,
		// text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // 地图

		// 设置字体大小（相对值,单位：像素） 参数表示为默认字体宽度的多少倍
		// msp.setSpan(new ScaleXSpan(2.0f), 0, text.length(),
		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //
		// 2.0f表示默认字体宽度的两倍，即X轴方向放大为默认字体的两倍，而高度不变

		// 设置项目符号
		// msp.setSpan(new BulletSpan(
		// android.text.style.BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN),
		// 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //
		// 第一个参数表示项目符号占用的宽度，第二个参数为项目符号的颜色
		return msp;
	}

	/**
	 * 银行卡四位加空格
	 * 
	 * @param mEditText
	 */
	public static void bankCardNumAddSpace(final EditText mEditText,final TextView carSub_tv) {
		mEditText.addTextChangedListener(new TextWatcher() {
			int beforeTextLength = 0;
			int onTextLength = 0;
			boolean isChanged = false;

			int location = 0;// 记录光标的位置
			private char[] tempChar;
			private StringBuffer buffer = new StringBuffer();
			int konggeNumberB = 0;

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				beforeTextLength = s.length();
				if (buffer.length() > 0) {
					buffer.delete(0, buffer.length());
				}
				konggeNumberB = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == ' ') {
						konggeNumberB++;
					}
				}
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				onTextLength = s.length();
				buffer.append(s.toString());
				if (onTextLength == beforeTextLength || onTextLength <= 3
						|| isChanged) {
					isChanged = false;
					return;
				}
				isChanged = true;
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (carSub_tv!=null) {
					carSub_tv.setText(mEditText.getText());
				}
				if (isChanged) {
					location = mEditText.getSelectionEnd();
					int index = 0;
					while (index < buffer.length()) {
						if (buffer.charAt(index) == ' ') {
							buffer.deleteCharAt(index);
						} else {
							index++;
						}
					}

					index = 0;
					int konggeNumberC = 0;
					while (index < buffer.length()) {
						if ((index == 4 || index == 9 || index == 14 || index == 19|| index == 24|| index == 29)) {
							buffer.insert(index, ' ');
							konggeNumberC++;
						}
						index++;
					}

					if (konggeNumberC > konggeNumberB) {
						location += (konggeNumberC - konggeNumberB);
					}

					tempChar = new char[buffer.length()];
					buffer.getChars(0, buffer.length(), tempChar, 0);
					String str = buffer.toString();
					if (location > str.length()) {
						location = str.length();
					} else if (location < 0) {
						location = 0;
					}

					mEditText.setText(str);
					Editable etable = mEditText.getText();
					Selection.setSelection(etable, location);
					isChanged = false;
				}
			}
		});
	}
	/**
	 * 时间格式化（Date转String）
	 * @param date
	 * @param timeFormat
	 * @return
	 */
	public static String timeFormattimeFormatDate2String(Date date,String timeFormat) {
		SimpleDateFormat sdf=new SimpleDateFormat(timeFormat);
		String t=sdf.format(date);
		return t;
	}
	
	/**
	 * 时间格式化（String转Date）
	 * @param dateStr
	 * @param timeFormat
	 * @return
	 */
	public static Date timeFormattimeFormatString2Date(String dateStr,String timeFormat) {
		SimpleDateFormat sdf=new SimpleDateFormat(timeFormat);
		Date t = null;
		try {
			 t = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
	
	
}
