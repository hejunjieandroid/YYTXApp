package com.yld.yytxapp.entity;

import java.io.Serializable;

public class SlidemenuSetup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Title;// 文字
	private int Img;// 图片
	
	

	public String getTitle() {
		return Title;
	}



	public void setTitle(String title) {
		Title = title;
	}



	public int getImg() {
		return Img;
	}



	public void setImg(int img) {
		Img = img;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {

		return Title;
	}

}
