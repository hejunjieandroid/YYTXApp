package com.yld.yytxapp.biz;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.yld.core.utils.JsonUtil;
import com.yld.yytxapp.entity.SlidemenuSetup;
import com.yld.yytxapp.ui.R;

/**
 * 账号
 * */
public class SlidemenuSetupBiz {
	// public ArrayList<SlidemenuSetup> getSlidemenuSetups(JSONArray jsonArray)
	// {
	// ArrayList<SlidemenuSetup> slidemenuSetups = new
	// ArrayList<SlidemenuSetup>();
	// SlidemenuSetup slidemenuSetup;
	//
	// for (int i = 0; i < jsonArray.size(); i++) {
	// try {
	// JSONObject jsonObj = jsonArray.getJSONObject(i);
	// slidemenuSetup = new SlidemenuSetup();
	// slidemenuSetup.setTitle(JsonUtil.getJSONString(jsonObj, "title"));
	// slidemenuSetup.setImg(JsonUtil.getJSONInt(jsonObj, "img"));
	// slidemenuSetups.add(slidemenuSetup);
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// return slidemenuSetups;
	//
	// }
	public ArrayList<SlidemenuSetup> getSlidemenuSetups() {
		ArrayList<SlidemenuSetup> slidemenuSetups = new ArrayList<SlidemenuSetup>();
		
		SlidemenuSetup slidemenuSetup0 = new SlidemenuSetup();
		slidemenuSetup0.setTitle("个人信息");
		slidemenuSetup0.setImg(R.drawable.slidemenu_setup_listimg0);
		slidemenuSetups.add(slidemenuSetup0);

		SlidemenuSetup slidemenuSetup1 = new SlidemenuSetup();
		slidemenuSetup1.setTitle("变更注册号码");
		slidemenuSetup1.setImg(R.drawable.slidemenu_setup_listimg1);
		slidemenuSetups.add(slidemenuSetup1);

		SlidemenuSetup slidemenuSetup2 = new SlidemenuSetup();
		slidemenuSetup2.setTitle("修改密码");
		slidemenuSetup2.setImg(R.drawable.slidemenu_setup_listimg2);
		slidemenuSetups.add(slidemenuSetup2);

		SlidemenuSetup slidemenuSetup3 = new SlidemenuSetup();
		slidemenuSetup3.setTitle("注销");
		slidemenuSetup3.setImg(R.drawable.slidemenu_setup_listimg3);
		slidemenuSetups.add(slidemenuSetup3);

		return slidemenuSetups;

	}
}
