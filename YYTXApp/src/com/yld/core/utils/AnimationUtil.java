package com.yld.core.utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * @brief android动画工具类
 * @author tyj
 * 
 * 
 * */

public class AnimationUtil {

	/**
	 * @brief 位置移动动画
	 * @param fromx
	 *            原始坐标x点
	 * @param tox
	 *            目标坐标x点
	 * @param fromy
	 *            原始坐标y点
	 * @param toy
	 *            目标坐标y点
	 * @param time
	 *            移动执行时间
	 * @return TranslateAnimation 位移动画对象
	 * */
	public static TranslateAnimation getTranslateAnimation(float fromx, float tox,
			float fromy, float toy, long time) {
		TranslateAnimation translateAnimation = new TranslateAnimation(fromx,
				tox, fromy, toy);
		translateAnimation.setDuration(time);
		return translateAnimation;
	}

	/**
	 * @brief 缩放动画
	 * @param fromx
	 *            x轴缩放开始时大小（1或xf）
	 * @param tox
	 *            x轴缩放完成时大小（0.1f或xf）
	 * @param fromy
	 *            y轴缩放开始时大小（1或xf）
	 * @param toy
	 *            y轴缩放完成时大小（0.1f或xf）
	 * @param time
	 *            缩放执行时间
	 * @return ScaleAnimation 缩放动画对象
	 * */
	public static ScaleAnimation getScaleAnimation(float fromx, float tox,
			float fromy, float toy, long time) {
		ScaleAnimation scaleAnimation = new ScaleAnimation(fromx, tox, fromy,
				toy, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(time);
		return scaleAnimation;
	}

	/**
	 * @brief 旋转动画
	 * @param fromDegrees
	 *            旋转起始角度（0）
	 * @param toDegrees
	 *            旋转角度（180）
	 * @param time
	 *            旋转执行时间
	 * @return RotateAnimation 旋转动画对象
	 * */
	public static RotateAnimation getRotateAnimation(float fromDegrees, float toDegrees,long time) {
		RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setDuration(time);
		return rotateAnimation;
	}

	/**
	 * @brief 渐变动画
	 * @param fromAlpha
	 *            渐变开始时透明度（1或xf）
	 * @param toAlpha
	 *            渐变完成时透明度（0或xf）
	 * @param time
	 *            渐变执行时间
	 * @return AlphaAnimation 渐变动画对象
	 * */
	public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha,
			long time) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
		alphaAnimation.setDuration(time);
		return alphaAnimation;
	}

	/**
	 * @brief 3D旋转动画
	 * @param view
	 *            需要旋转的view
	 * @param start
	 *            旋转开始时角度
	 * @param end
	 *            旋转结束时角度
	 * @param centerx
	 *            旋转中心点x坐标
	 * @param centery
	 *            旋转中心点y坐标
	 * @param time
	 *            旋转时间
	 * */
	public static void applyRotation(View view, float start, float end, float centerx,
			float centery, long time) {
		// 计算中心点
		float centerX = centerx;
		float centerY = centery;
		Rotate3dAnimation rotation = null;
		rotation = new Rotate3dAnimation(start, end, centerX, centerY, 1.0f,
				false);
		rotation.setDuration(time);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new DecelerateInterpolator());
		// 开始动画
		view.startAnimation(rotation);
	}
}
