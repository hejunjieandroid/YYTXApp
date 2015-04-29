/**
 * 
 */
package com.yld.core.http;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.yld.core.http.volley.AuthFailureError;
import com.yld.core.http.volley.DefaultRetryPolicy;
import com.yld.core.http.volley.NetworkResponse;
import com.yld.core.http.volley.Request;
import com.yld.core.http.volley.RequestQueue;
import com.yld.core.http.volley.Response;
import com.yld.core.http.volley.Response.ErrorListener;
import com.yld.core.http.volley.Response.Listener;
import com.yld.core.http.volley.VolleyError;
import com.yld.core.http.volley.toolbox.ImageLoader;
import com.yld.core.http.volley.toolbox.ImageLoader.ImageCache;
import com.yld.core.http.volley.toolbox.ImageLoader.ImageListener;
import com.yld.core.http.volley.toolbox.ImageRequest;
import com.yld.core.http.volley.toolbox.NetworkImageView;
import com.yld.core.http.volley.toolbox.StringRequest;
import com.yld.core.http.volley.toolbox.Volley;
import com.yld.core.utils.LogUtil;

/**
 * @brief 通讯控制类 通讯模块入口,在退出activity时需要调用stopRequestQueue方法，取消请求队列
 * @author tyj
 * 
 */
public class httpControl {

	/**
	 * @brief 请求服务器cookie
	 * */
	public static volatile String cookies;
	/**
	 * @brief 数据请求方式 get
	 * */
	public static int RequestGet = Request.Method.GET;
	/**
	 * @brief 数据请求方式 post
	 * */
	public static int RequestPost = Request.Method.POST;
	/**
	 * @brief 请求队列
	 * */
	public RequestQueue requestQueue;
	/**
	 * @brief 上下文对象
	 * */
	public Context mContext;

	/**
	 * @brief 图片缓存区分配空间大小 默认4MB
	 * */
	public static int MaxSize = 4 * 1024 * 1024;

	/**
	 * @brief http请求超时时间
	 * */
	public static int TimeOut = 30 * 1000;

	/**
	 * @brief 构造方法 启动请求队列
	 * @param context
	 *            上下文
	 * */
	public httpControl(Context context) {
		super();
		this.mContext = context;
		LogUtil.d(context, "通讯模块请求队列启动！");
		requestQueue = Volley.newRequestQueue(context);
		requestQueue.start();
	}

	/**
	 * @brief 停止请求队列
	 * */
	public void stopRequestQueue() {
		LogUtil.d(mContext, "通讯模块请求队列停止！");
		if (requestQueue != null) {
			requestQueue.stop();
			requestQueue = null;
		}
	}

	/**
	 * @brief 清除cookie
	 * */
	public void ClearCookie() {
		LogUtil.d(mContext, "清除cookie！");
		cookies = "";
	}

	/**
	 * @brief 数据请求方法
	 * @param url
	 *            请求地址
	 * @param requestType
	 *            请求类型（post：HttpControl.RequestPost；get：HttpControl.RequestGet）
	 * @param map
	 *            请求参数
	 * @param resultInterface
	 *            请求结果回调方法
	 * */
	public void HttpExcute(String url, int requestType,
			final Map<String, String> map, final ResultInterface resultInterface) {
		LogUtil.d(mContext, "http数据请求开始！");
		LogUtil.d(mContext, "http数据请求URL="+url);
		StringRequest sRequest = new StringRequest(requestType, url,
				new Listener<String>() {
					@Override
					public void onResponse(String response) {
						LogUtil.d(mContext, "http数据请求结束！");
						LogUtil.d(mContext, "请求结果：" + response);
						resultInterface.onSuccess(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						LogUtil.d(mContext, "http数据请求错误！");
						LogUtil.e(mContext, "错误信息：" + arg0.getMessage());
					
					
						if(arg0!=null && arg0.getMessage()!=null) {
							resultInterface.onError(arg0.getMessage());
						}else {
							resultInterface.onError("服务器通信异常,请稍后重试!");
						}
					}
				}) {
			// 重写StringRequest方法 获取服务器返回cookie
			@Override
			protected Response<String> parseNetworkResponse(
					NetworkResponse response) {
				Response<String> superResponse = super
						.parseNetworkResponse(response);
				Map<String, String> responseHeaders = response.headers;
				String rawCookies = responseHeaders.get("Set-Cookie");
				LogUtil.d(mContext, "服务器返回headers：" + responseHeaders);
				LogUtil.d(mContext, "服务器返回cookie：" + rawCookies);
				LogUtil.d(mContext, "服务器返回response：" + response.statusCode);
				if (rawCookies != null) {
					// 服务端返回是
					// set-cookie:JSESSIONID=D90B58454550B4D37C4B66A76BF27B93;
					// Path=/otn BIGipServerotn=2564030730.64545.0000; path=/
					String part1 = substring(rawCookies, "", ";");
//					String part2 = substring(rawCookies, "\n", ";");
					// 客户端需要的是
					// cookie:JSESSIONID=D90B58454550B4D37C4B66A76BF27B93;
					// BIGipServerotn=2564030730.64545.0000;
					cookies = part1;
					LogUtil.d(mContext, "cookies：" + cookies);
				} else {
					LogUtil.d(mContext, "cookies为空！");
				}
				return superResponse;
			}

			// 请求参数的方法 传递参数
			@Override
			protected Map<String, String> getParams() {
				if (map == null) {
					Map<String, String> params = new HashMap<String, String>();
					LogUtil.d(mContext, "http请求参数：" + params);
					return params;
				} else {
					LogUtil.d(mContext, "http请求参数：" + map);
					return map;
				}
			}

			// 重写处理http headers的方法 设置请求cookie
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (cookies != null && cookies.length() > 0) {
					HashMap<String, String> headers = new HashMap<String, String>();
					headers.put("Cookie", cookies);
					//改变http请求参数格式    map转json
					headers.put("Accept", "text/xml,application/json");
					LogUtil.d(mContext, "http请求headers：" + headers);
					return headers;
				}
				return super.getHeaders();
			}
		};
		sRequest.setShouldCache(false);
		sRequest.setRetryPolicy(new DefaultRetryPolicy(TimeOut, 0, 1.0f));
		requestQueue.add(sRequest);
	}

	/**
	 * @brief 图片异步加载
	 * @param imageUrl
	 *            图片网络地址
	 * @param imageView
	 *            图片显示view对象
	 * @param defaultImageResId
	 *            默认图片id交
	 * @param errorImageResId
	 *            请求错误情况图片id
	 * */
	public void ImageExcute(String imageUrl, ImageView imageview,
			int defaultImageResId, int errorImageResId) {
		ImageCache imageCache = initImageCache();
		ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
		ImageListener listener = ImageLoader.getImageListener(imageview,
				defaultImageResId, errorImageResId);
		imageLoader.get(imageUrl, listener);
	}

	/**
	 * @brief 图片异步加载
	 * @param imageUrl
	 *            图片网络地址
	 * @param networkimageview
	 *            图片显示view对象( volley提供的封装的imageview )
	 * @param defaultImageResId
	 *            默认图片id
	 * @param errorImageResId
	 *            请求错误情况图片id
	 * */
	public void ImageExcute(String imageUrl, NetworkImageView networkimageview,
			int defaultImageResId, int errorImageResId) {
		ImageCache imageCache = initImageCache();
		networkimageview.setDefaultImageResId(defaultImageResId);
		networkimageview.setErrorImageResId(errorImageResId);
		networkimageview.setImageUrl(imageUrl, new ImageLoader(requestQueue,
				imageCache));
	}

	/**
	 * @brief 图片异步加载
	 * @param imageUrl
	 *            图片网络地址
	 * @param map
	 *            请求参数
	 * @param resultInterface
	 *            请求结果回调方法（回调返回类型bitmap）
	 * */
	public void ImageExcute(String imageUrl, final Map<String, String> map,
			final ResultInterface resultInterface) {
		ImageRequest imageRequest = new ImageRequest(imageUrl,
				new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						// TODO Auto-generated method stub
						resultInterface.onSuccess(response);
					}

				}, 0, 0, null, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						resultInterface.onError(error.getMessage());
					}

				}) {
			// 重写StringRequest方法 获取服务器返回cookie
			@Override
			protected Response<Bitmap> parseNetworkResponse(
					NetworkResponse response) {
				Response<Bitmap> superResponse = super
						.parseNetworkResponse(response);
				Map<String, String> responseHeaders = response.headers;
				String rawCookies = responseHeaders.get("Set-Cookie");
				LogUtil.d(mContext, "服务器返回headers：" + responseHeaders);
				LogUtil.d(mContext, "服务器返回cookie：" + rawCookies);
				if (rawCookies != null) {
					// 服务端返回是
					// set-cookie:JSESSIONID=D90B58454550B4D37C4B66A76BF27B93;
					// Path=/otn BIGipServerotn=2564030730.64545.0000; path=/
					String part1 = substring(rawCookies, "", ";");
//					String part2 = substring(rawCookies, "\n", ";");
					// 客户端需要的是
					// cookie:JSESSIONID=D90B58454550B4D37C4B66A76BF27B93;
					// BIGipServerotn=2564030730.64545.0000;
					cookies = part1;
					LogUtil.d(mContext, "cookies：" + cookies);
				} else {
					LogUtil.d(mContext, "cookies为空！");
				}
				return superResponse;
			}

			// 请求参数的方法 传递参数
			@Override
			protected Map<String, String> getParams() {
				if (map == null) {
					Map<String, String> params = new HashMap<String, String>();
					LogUtil.d(mContext, "http请求参数：" + map);
					return params;
				} else {
					LogUtil.d(mContext, "http请求参数：" + map);
					return map;
				}
			}

			// 重写处理http headers的方法 设置请求cookie
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (cookies != null && cookies.length() > 0) {
					HashMap<String, String> headers = new HashMap<String, String>();
					headers.put("Cookie", cookies);
					//改变http请求参数格式    map转json
					headers.put("Accept", "text/xml,application/json");
					LogUtil.d(mContext, "http请求headers：" + headers);
					return headers;
				}
				return super.getHeaders();
			}
		};
		imageRequest.setShouldCache(false);
		imageRequest.setRetryPolicy(new DefaultRetryPolicy(TimeOut, 0, 1.0f));
		requestQueue.add(imageRequest);
	}

	private ImageCache initImageCache() {
		final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(
				MaxSize);
		ImageCache imageCache = new ImageCache() {
			@Override
			public void putBitmap(String key, Bitmap value) {
				lruCache.put(key, value);
			}

			@Override
			public Bitmap getBitmap(String key) {
				return lruCache.get(key);
			}
		};
		return imageCache;
	}

	private String substring(String src, String fromString, String toString) {
		int fromPos = 0;
		if (fromString != null && fromString.length() > 0) {
			fromPos = src.indexOf(fromString);
			fromPos += fromString.length();
		}
		int toPos = src.indexOf(toString, fromPos);
		return src.substring(fromPos, toPos);
	}
}
