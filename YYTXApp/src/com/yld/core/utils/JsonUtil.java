package com.yld.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.view.MenuItem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @brief json解析工具类（fastjson）
 * @author tyj
 * 
 * 
 */
public class JsonUtil {

	/**
	 * @brief 把JSON文本parse为JSONArray或JSONObject
	 * @param text
	 *            json文本
	 * @return JSONArray
	 * */
	public static Object parse(String text) {
		Object object = new Object();
		try {
			object = JSON.parse(text);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return object;
	}
	public static JSONObject parseJSONObject(String text) {
		JSONObject object = new JSONObject();
		try {
			object = (JSONObject) JSON.parse(text);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return object;
	}
	public static JSONArray parseJSONArray(String text) {
		JSONArray object = new JSONArray();
		try {
			object = (JSONArray) JSON.parse(text);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return object;
	}

	/**
	 * @brief 把JSON文本parse成JavaBean集合
	 * @param text
	 *            json文本
	 * @param clazz
	 *            javaBean类
	 * @return list列表
	 * */
//	public static List<?> parseArray(String text, Class<?> clazz) {
//		return parseArray(text, clazz);
//	}

	/**
	 * @brief 将JavaBean序列化为JSON文本
	 * @param object
	 *            javaBean对象
	 * @return String
	 * */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}
	/**
	 * @brief 获取json中value
	 * @param json
	 *            JSONObject对象
	 * @return String
	 * */
	public static String getJSONString(JSONObject json,String key){
		if (json==null) {
			return "";
		}
		if (json.getString(key)==null) {
			return "";
		}else {
			return json.getString(key);
		}
	}
	
	/**
	 * @brief 获取json中value
	 * @param json
	 *            JSONObject对象
	 * @return int
	 * */
	public static int getJSONInt(JSONObject json,String key){
		if (json==null) {
			return 0;
		}
		if (json.getString(key)==null) {
			return 0;
		}else {
			return json.getIntValue(key);
		}
	}
	
	/**
	 * @brief 获取json中jsonobject
	 * @param json
	 *            JSONObject对象
	 * @return JSONObject
	 * */
	public static JSONObject getJSONObject(JSONObject json,String key){
		if (json==null) {
			return new JSONObject();
		}
		if (json.getString(key)==null) {
			return new JSONObject();
		}else {
			return json.getJSONObject(key);
		}
	}
	/**
	 * @brief 获取json中jsonobject
	 * @param json
	 *            JSONObject对象
	 * @return JSONArray
	 * */
	public static JSONArray getJSONArray(JSONObject json,String key){
		if (json==null) {
			return new JSONArray();
		}
		if (json.getJSONArray(key)==null) {
			return new JSONArray();
		}else {
			return json.getJSONArray(key);
		}
	}
	/**
	 * @brief 将JavaBean序列化为带格式的JSON文本
	 * @param object
	 *            javaBean对象
	 * @param prettyFormat
	 *            是否带格式
	 * @return String
	 * */
	public static String toJSONString(Object object, boolean prettyFormat) {
		return JSON.toJSONString(object, prettyFormat);
	}

	/**
	 * @brief 将JavaBean转换为JSONObject或者JSONArray。
	 * @param object
	 *            javaBean对象
	 * @return Object
	 * */
	public static Object toJSON(Object object) {
		return JSON.toJSON(object);
	}

	/**
	 * @brief 把JSON文本parse为JavaBean
	 * @param text
	 *            json文本
	 * @param clazz
	 *            javaBean类
	 * @return T
	 * */
	public static <T> T parseObject(String text, Class clazz) {
		return (T) JSON.parseObject(text, clazz);
	}

	/**
	 * @brief JSONArray转List<Map<String, Object>>
	 * @param jsonArr
	 *            JSONArray数据
	 * @return List<Map<String, Object>>
	 * */
	public static List<Map<String, Object>> parseJSON2List(JSONArray jsonArr) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<Object> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = (JSONObject) it.next();
			list.add(parseJSON2MapO(json2));
		}
		return list;
	}

	/**
	 * @brief JSONObject转Map<String, String>
	 * @param json
	 *            JSONObject数据
	 * @return Map<String, String>
	 * */
	public static Map<String, String> parseJSON2Map(JSONObject json) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		for (String k : json.keySet()) {
			String v = json.getString(k);
			map.put(k.toString(), v);
		}
		return map;
	}
	/**
	 * @brief JSONObject转Map<String, Object>
	 * @param json
	 *            JSONObject数据
	 * @return Map<String, Object>
	 * */
	public static Map<String, Object> parseJSON2MapO(JSONObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<Object> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = (JSONObject) it.next();
					list.add(parseJSON2MapO(json));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
//	/**
//	 * @brief JSONObject转javaBean(MenuItem)
//	 * @param json
//	 *            JSONObject数据
//	 * @return MenuItem 
//	 * */
//	public static MenuItem jsonToMenuItem(JSONObject json){
//		MenuItem menuItem = new MenuItem();
//		menuItem.setActionId(json.getString("ActionId"));
//		menuItem.setActionImage(json.getString("ActionImage"));
//		menuItem.setActionModule(json.getString("ActionModule"));
//		menuItem.setActionName(json.getString("ActionName"));
//		menuItem.setActionParams(json.getString("ActionParams"));
//		menuItem.setActionUrl(json.getString("ActionUrl"));
//		menuItem.setClickable(json.getString("Clickable"));
//		menuItem.setCreateTime(json.getString("CreateTime"));
//		menuItem.setDisplayType(json.getString("DisplayType"));
//		menuItem.setEntryType(json.getString("EntryType"));
//		menuItem.setImageGetType(json.getString("ImageGetType"));
//		menuItem.setImageGetUrl(json.getString("ImageGetURl"));
//		menuItem.setParams(json.getString("Params")); 
//		menuItem.setUsable(json.getString("Usable"));
//		menuItem.setVersionCode(json.getString("VersionCode"));
//		JSONArray jsonArray = new JSONArray();
//		if (json.containsKey("MenuList")) {
//			jsonArray = json.getJSONArray("MenuList");
//		}
//		menuItem.setMenuList(jsonToMenuList(jsonArray));
//		return menuItem;
//	}
//	/**
//	 * @brief JSONObject转javaBean list(List<MenuItem>)
//	 * @param jsonArray
//	 *            JSONArray数据
//	 * @return List<MenuItem> 
//	 * */
//	public static List<MenuItem> jsonToMenuList(JSONArray jsonArray){
//		List<MenuItem> list = new ArrayList<MenuItem>();
//		if (jsonArray==null) {
//			return list;
//		}
//		if (jsonArray.size()==0) {
//			return list;
//		}
//		for (int i = 0; i < jsonArray.size(); i++) {
//			list.add(jsonToMenuItem(jsonArray.getJSONObject(i)));
//		}
//		return list;
//	}
}
