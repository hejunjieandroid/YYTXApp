package com.yld.yytxapp.ui;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSONObject;
import com.yld.core.base.BaseFragmentActivity;
import com.yld.core.http.ResultInterface;
import com.yld.core.httphelper.HttpMiddleWare;
import com.yld.core.utils.AlertUtil;
import com.yld.core.utils.AnimationUtil;
import com.yld.core.utils.DeviceUtil;
import com.yld.core.utils.FormatTools;
import com.yld.core.utils.JsonUtil;
import com.yld.core.utils.UpdateManager;
import com.yld.core.utils.UpdateManager.UpdateCallBack;
import com.yld.yytxapp.entity.UpDateInfo;

public class SplashActivity extends BaseFragmentActivity {

	private boolean isAnimationFinish = false;
	private boolean isUpDateCheck = false;
	private boolean isNetWork = true;
	private LinearLayout splash_ll, splash_marketing;
	private UpDateInfo upDateInfo = new UpDateInfo();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		splash_ll = (LinearLayout) findViewById(R.id.layout_splash);
		splash_marketing = (LinearLayout) findViewById(R.id.layout_marketing);
		showSplashAnimation();
		if (!DeviceUtil.IsNetWork(context)) {
			isNetWork = false;
			AlertUtil.ShowHintDialog(activity, "提示", "网络异常，请检查手机网络！", "确定", true, new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
					android.os.Process.killProcess(android.os.Process.myPid());
				}
			});
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (constant.isDebug) {
			isUpDateCheck = true;
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("DeviceType", DeviceUtil.getDeviceType());
			requestPost(HttpMiddleWare.TRADE_UPDATE, map, true, new ResultInterface() {

				@Override
				public void onSuccess(Object response) {
					// TODO Auto-generated method stub
					if ("".equals(response)) {
						if (isAnimationFinish) {
							toMainMenuUI();
						}
						isUpDateCheck = true;
					}
					int versionCode = 0;
					JSONObject json = JsonUtil.parseJSONObject((String) response);
					if (!"".equals(JsonUtil.getJSONString(json, "VersionCode"))) {
						versionCode = Integer.parseInt(JsonUtil.getJSONString(json, "VersionCode"));
					}

					upDateInfo.setUpdateVersionName(JsonUtil.getJSONString(json, "VersionName"));
					upDateInfo.setUpdateUrl(JsonUtil.getJSONString(json, "ApkURL"));
					upDateInfo.setUpdateMode(JsonUtil.getJSONString(json, "IsForcedUpdate"));
					upDateInfo.setUpdateHint(JsonUtil.getJSONString(json, "UpdateHint"));
					upDateInfo.setJumpFlag(JsonUtil.getJSONString(json, "JumpFlag"));
					upDateInfo.setImageURL(JsonUtil.getJSONString(json, "ImageURL"));
					upDateInfo.setJumpURL(JsonUtil.getJSONString(json, "JumpURL"));
					if (versionCode > DeviceUtil.getVersionCode(context)) {
						UpdateManager manager = new UpdateManager(activity);
						manager.checkUpdate(upDateInfo, new UpdateCallBack() {

							@Override
							public void updatePass() {
								// TODO Auto-generated
								// method
								// stub
								if (isAnimationFinish) {
									toMainMenuUI();
								}
								isUpDateCheck = true;
							}
						});
					} else {
						if (isAnimationFinish) {
							toMainMenuUI();
						}
						isUpDateCheck = true;
					}
				}

				@Override
				public void onError(Object errorMsg) {
					// TODO Auto-generated method stub
					AlertUtil.ShowHintDialog(activity, "提示", "网络异常，请检查手机网络！", "确定", true, new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							finish();
							android.os.Process.killProcess(android.os.Process.myPid());
						}
					});
				}
			});
		}
	}

	private void showSplashAnimation() {
		Animation mAlphaAnim = AnimationUtil.getAlphaAnimation(0.0f, 1.0f, 2000);
		mAlphaAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				if (isUpDateCheck) {
					toMainMenuUI();
				}
				isAnimationFinish = true;
			}
		});
		splash_ll.startAnimation(mAlphaAnim);
	}

	private void toMainMenuUI() {
		if (isNetWork) {
			if ("1".equals(upDateInfo.getJumpFlag())) {
				// 跳转到营销页面
				requestImage(upDateInfo.getImageURL(), null, false, new ResultInterface() {

					@Override
					public void onSuccess(Object response) {
						// TODO Auto-generated method stub
						Bitmap bitmap = (Bitmap) response;
						splash_marketing.setBackgroundDrawable(FormatTools.getInstance().Bitmap2Drawable(bitmap));
						splash_marketing.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
								intent.putExtra("JumpUrl", upDateInfo.getJumpURL());
								startActivity(intent);
								finish();
							}
						});
						Animation mAlphaAnimon = AnimationUtil.getAlphaAnimation(0.0f, 1.0f, 500);
						Animation mAlphaAnimoff = AnimationUtil.getAlphaAnimation(0.0f, 1.0f, 500);
						mAlphaAnimon.setAnimationListener(new AnimationListener() {

							@Override
							public void onAnimationStart(Animation animation) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationRepeat(Animation animation) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationEnd(Animation animation) {
								// TODO Auto-generated method stub
								splash_marketing.setVisibility(View.VISIBLE);
								splash_ll.setVisibility(View.GONE);
							}
						});
						splash_ll.startAnimation(mAlphaAnimoff);
						splash_marketing.startAnimation(mAlphaAnimon);
					}

					@Override
					public void onError(Object errorMsg) {
						// TODO Auto-generated method stub
						AlertUtil.ShowHintDialog(activity, "提示", "网络异常，请检查手机网络！", "确定", true, new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								finish();
								android.os.Process.killProcess(android.os.Process.myPid());
							}
						});
					}
				});
			} else {
				Intent intentTMainMenu = new Intent(this, HomeActivity.class);
				startActivity(intentTMainMenu);
				finish();
			}
		}
	}

	@Override
	protected void onTouchListenner(int flag) {

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

}
