package com.yld.yytxapp.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yld.core.base.BaseFragmentActivity;
import com.yld.yytxapp.ui.R;

public class LoginProtocolActivity extends BaseFragmentActivity {
	private WebView webview;// 加载html的控件
	private String which;// 判断显示哪个页面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginprotocol);

		which = getIntent().getExtras().getString("which");
		if (which.equals("0")) {
			initTitleLayout("隐私政策");
		} else {
			initTitleLayout("服务条款");
		}
		Show_homebtn(false);
		// webView初始化控件
		webview = (WebView) findViewById(R.id.webview);
		webview.setBackgroundColor(Color.parseColor("#00000000"));
		// webview.requestFocus();
		// webview.requestFocusFromTouch();

		WebSettings webviewSettings = webview.getSettings();
		webviewSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		webviewSettings.setJavaScriptEnabled(true);
		webviewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webviewSettings.setDatabaseEnabled(true);
		webviewSettings.setDomStorageEnabled(true);
		webviewSettings.setAppCacheEnabled(false);

		// webview.addJavascriptInterface(new MyJavaScriptInterface(), "CSII");
		// webview.setWebChromeClient(new MyWebChromeClient());
		// webview.setWebViewClient(new MyWebViewClient());
		if (which.equals("0")) {
			String url = "file:///android_asset/html/loginprotocol_ys.html";
			webview.loadUrl(url);
		} else {
			String url = "file:///android_asset/html/loginprotocol_fw.html";
			webview.loadUrl(url);
		}
	}

	@Override
	protected void onTouchListenner(int flag) {
		// TODO Auto-generated method stub

	}

}
