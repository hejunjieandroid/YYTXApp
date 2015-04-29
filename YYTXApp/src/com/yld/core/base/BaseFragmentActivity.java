/**
 * 
 */
package com.yld.core.base;

import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yld.core.http.ResultInterface;
import com.yld.core.http.volley.toolbox.NetworkImageView;
import com.yld.core.httphelper.HttpMiddleWare;
import com.yld.core.utils.AlertUtil;
import com.yld.core.utils.Constant;
import com.yld.core.utils.DeviceUtil;
import com.yld.core.view.AlertDialog;
import com.yld.core.view.AlertDialog.OnTimeOutListener;
import com.yld.yytxapp.ui.HomeActivity;
import com.yld.yytxapp.ui.R;

/**
 * @brief activity基类，封装了http模块接口，手势监听等
 * @author tyj
 * 
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

	/**
	 * @brief 与服务器交互通讯中间件
	 * */
	public HttpMiddleWare httpMiddleWare;
	/**
	 * @brief 上下文对象
	 * */
	public Context context;
	/**
	 * @brief 当前类对象
	 * */
	public BaseFragmentActivity activity;
	/**
	 * @brief 手势检测器对象
	 * */
	private GestureDetector gestureDetector;

	/**
	 * @brief 滑动监听回调方法 监听屏幕手势滑动结果
	 * @param flag
	 *            :滑动标示（0：向左；1：向右）
	 * */
	protected abstract void onTouchListenner(int flag);

	/**
	 * @brief 手势滑动监听，调用view的setOnTouchListener方法设置
	 * */
	public baseTouchListener onTouchListener = new baseTouchListener();
	public boolean isKeyBoardShow = false;// 密码键盘状态
	public ImageView head_back, head_home;
	public TextView head_title;
	public ForResultCallBack forResultCallBack;
	private PopupWindow login_welcome;
	public Constant constant;
	public AlertDialog dialog;
	private Handler mHandler;
	public View view;

	public boolean isShow_backbtn = true;// 是否显示返回按钮
	public boolean isShow_homebtn = true;// 是否显示回到主页按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = this;
		context = getApplicationContext();
		constant = (Constant) context;
		httpMiddleWare = new HttpMiddleWare(this);
		mHandler = new Handler();
		dialog = AlertDialog.createAlertDialog(activity, Constant.ProgressHint, Constant.timeout, new OnTimeOutListener() {

			@Override
			public void onTimeOut(Dialog dialog) {
				// TODO Auto-generated method stub
				AlertUtil.ToastMessageShort(activity.getApplicationContext(), Constant.ProgressTimeOutHint);
			}
		});
		// 得到activity中的根元素
		// view = getWindow().getDecorView().findViewById(android.R.id.content);
	}

	/**
	 * 初始化标题栏
	 * */
	public void initTitleLayout(String title) {
		head_back = (ImageView) findViewById(R.id.head_back);
		head_home = (ImageView) findViewById(R.id.head_home);
		head_title = (TextView) findViewById(R.id.head_title);
		if (head_back != null) {
			head_back.setOnClickListener(new onclickListener());
		}
		if (head_home != null) {
			head_home.setOnClickListener(new onclickListener());
		}
		if (head_title != null) {
			head_title.setText(title);
		}
	}

	/**
	 * 是否显示返回按钮
	 * 
	 * @param isShow_backbtn
	 */
	public void Show_backbtn(boolean isShow_backbtn) {
		this.isShow_backbtn = isShow_backbtn;
		if (isShow_backbtn) {
			head_back.setVisibility(View.VISIBLE);
		} else {
			head_back.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * 是否显示回到主页按钮
	 * 
	 * @param isShow_homebtn
	 */
	public void Show_homebtn(boolean isShow_homebtn) {
		this.isShow_homebtn = isShow_homebtn;
		if (isShow_homebtn) {
			head_home.setVisibility(View.VISIBLE);
		} else {
			head_home.setVisibility(View.INVISIBLE);
		}
	}

	class onclickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.head_back:
				// if (!(context instanceof WebViewActivity)) {
				FinishActivity();
				// }
				break;
			case R.id.head_home:
				StartActivity(HomeActivity.class, null);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @brief http请求方法（请求方式get）
	 * @param trade
	 *            请求交易名
	 * @param map
	 *            请求参数
	 * @param resultInterface
	 *            请求回调方法
	 * */
	public void requestGet(String trade, Map<String, String> map, boolean isAlowHint, ResultInterface resultInterface) {
		httpMiddleWare.get(trade, map, isAlowHint, resultInterface);
	}

	/**
	 * @brief http请求方法（请求方式post）
	 * @param trade
	 *            请求交易名
	 * @param map
	 *            请求参数
	 * @param resultInterface
	 *            请求回调方法
	 * */
	public void requestPost(String trade, Map<String, String> map, boolean isAlowHint, ResultInterface resultInterface) {
		httpMiddleWare.post(trade, map, isAlowHint, resultInterface);
	}

	/**
	 * @brief 图片异步请求
	 * @param trade
	 *            图片交易名
	 * @param imageview
	 *            图片view对象
	 * @param defaultImageResId
	 *            默认图片id
	 * @param errorImageResId
	 *            请求出错时图片id
	 * */
	public void requestImage(String trade, ImageView imageview, int defaultImageResId, int errorImageResId) {
		httpMiddleWare.ImageExcute(trade, imageview, defaultImageResId, errorImageResId);
	}

	/**
	 * @brief 图片异步请求
	 * @param trade
	 *            图片交易名
	 * @param networkImageView
	 *            图片view对象(volley提供的自定义控件)
	 * @param defaultImageResId
	 *            默认图片id
	 * @param errorImageResId
	 *            请求出错时图片id
	 * */
	public void requestImage(String trade, NetworkImageView networkImageView, int defaultImageResId, int errorImageResId) {
		httpMiddleWare.ImageExcute(trade, networkImageView, defaultImageResId, errorImageResId);
	}

	/**
	 * @brief 图片异步请求
	 * @param trade
	 *            图片交易名
	 * @param map
	 *            请求参数
	 * @param resultInterface
	 *            请求结果回调方法（回调返回类型bitmap）
	 * */
	public void requestImage(String trade, Map<String, String> map, boolean isAlowHint, ResultInterface resultInterface) {
		httpMiddleWare.ImageExcute(trade, map, isAlowHint, resultInterface);
	}

	/**
	 * @brief 滑动监听
	 * */
	public class baseTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return gestureDetector.onTouchEvent(event);
		}
	}

	/**
	 * @brief 手势监听
	 * */
	class GestureListener implements OnGestureListener {
		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			int width = DeviceUtil.getMetricsWidth(context) / 3;
			if (e1 != null && e2 != null) {
				if (e1.getX() - e2.getX() > width && Math.abs(velocityX) > 0) {
					// 向左手势
					if (e1.getY() - e2.getY() > 100) {

					} else if (e2.getY() - e1.getY() > 100) {

					} else {
						onTouchListenner(1);
					}
				} else if (e2.getX() - e1.getX() > width && Math.abs(velocityX) > 0) {
					// 向左手势
					if (e1.getY() - e2.getY() > 100) {

					} else if (e2.getY() - e1.getY() > 100) {

					} else {
						onTouchListenner(0);
					}
				}
			}
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
		super.onDestroy();
		httpMiddleWare.stopRequestQueue();
	}

	/**
	 * activity跳转动画 统一跳转方法
	 * */
	public void StartActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		// overridePendingTransition(R.anim.push_right_in,
		// R.anim.push_left_out);
	}

	/**
	 * activity关闭动画 统一关闭方法
	 * */
	public void FinishActivity() {
		activity.finish();
		// overridePendingTransition(R.anim.push_left_in,
		// R.anim.push_right_out);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			FinishActivity();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	/**
	 * startActivityForResult
	 * */
	public void startActivityForResult(Class<?> cls, Bundle bundle, ForResultCallBack forResultCallBack) {
		this.forResultCallBack = forResultCallBack;
		Intent intent = new Intent(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, Constant.REQUESTCODE);
	}

	/**
	 * forresult 回调接口
	 * */
	public interface ForResultCallBack {
		public void forResult();
	}

	/**
	 * 替换当前fragment
	 * 
	 * */
	public void replaceFragment(Fragment fragment, int contentId, int stackTag) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		// fragmentTransaction.setCustomAnimations(R.anim.push_right_in,
		// R.anim.push_left_out, R.anim.push_left_in,
		// R.anim.push_right_out);
		fragmentTransaction.addToBackStack(stackTag + "");
		fragmentTransaction.replace(contentId, fragment);
		fragmentTransaction.commit();
	}

	/**
	 * 添加fragment
	 * */
	public void addFragment(Fragment fragment, int contentId, int stackTag) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		// fragmentTransaction.setCustomAnimations(R.anim.push_right_in,
		// R.anim.push_left_out, R.anim.push_left_in,
		// R.anim.push_right_out);
		fragmentTransaction.addToBackStack(stackTag + "");
		fragmentTransaction.add(contentId, fragment);
		fragmentTransaction.commit();
	}

	/**
	 * activity回调
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (resultCode) {
		case Constant.RESULTCODE:
			forResultCallBack.forResult();
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
