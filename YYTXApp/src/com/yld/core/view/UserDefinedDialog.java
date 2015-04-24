package com.yld.core.view;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yld.yytxapp.ui.R;


public class UserDefinedDialog extends Dialog implements android.view.View.OnClickListener {
	
	private Context ctx;
	private String msg;
	private String title;
	private String center="确定";
	private String left="确定";
	private String right="取消";
	private Button btnleft, btncenter, btnright;
	private boolean IsTwoButton = false;
	private boolean erroricon=false;
	private View.OnClickListener okListener;
	private View.OnClickListener cancelListener;
	private ImageView dialog_alert_icon_id,dialog_error_icon_id;
	private View divider;
	
	private TextView tvtitle,tvcontent;

	public void setButtoncenter(String strcenter) {
		this.center=strcenter;
	}

	public void setErroricon(boolean iserroricon) {
		this.erroricon = iserroricon;
	}

	public void setLeft(String strleft) {
		this.left = strleft;
	}
	public void setRight(String strright) {
		this.right = strright;
	}

	public UserDefinedDialog(Context context,String title, String message,View.OnClickListener onclicklistener,View.OnClickListener cancelListener) {
		super(context,R.style.Theme_Dialog); 
		this.ctx = context;
		this.msg = message;
		this.title=title;
		if (onclicklistener != null) {
			this.okListener=onclicklistener;
		}
		if(cancelListener != null){
			IsTwoButton=true;
			this.cancelListener=cancelListener;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alertdialog);
		
		tvtitle = (TextView) findViewById(R.id.dialogtitle);
		tvcontent = (TextView) findViewById(R.id.dialogcontent);

		btnleft = (Button) findViewById(R.id.btnleft);
		btnright = (Button) findViewById(R.id.btnright);
		
		dialog_alert_icon_id=(ImageView) findViewById(R.id.dialog_alert_icon_id);
		dialog_error_icon_id=(ImageView) findViewById(R.id.dialog_error_icon_id);
		
		if(erroricon){
			dialog_error_icon_id.setVisibility(View.VISIBLE);
			dialog_alert_icon_id.setVisibility(View.GONE);
		}
		
		
		btnleft.setOnClickListener(this);
		btnleft.setText(left);
		btnright.setOnClickListener(this);
		btnright.setText(right);
		
		btncenter = (Button) findViewById(R.id.btncenter);
		btncenter.setText(center);
		btncenter.setOnClickListener(this);
		
		divider = findViewById(R.id.divder_verticalid);
		
		if(IsTwoButton){
			btnleft.setVisibility(View.VISIBLE);
			btnright.setVisibility(View.VISIBLE);
			divider.setVisibility(View.VISIBLE);
			btncenter.setVisibility(View.GONE);
		}
		tvtitle.setText(title);
		tvcontent.setText(msg);

		WindowManager m = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		Display d = m.getDefaultDisplay();
		LayoutParams p = getWindow().getAttributes();
//		p.height = (int) (d.getHeight() * 0.3);
		p.width = (int) (d.getWidth() * 0.9);
		//p.alpha = 0.8f;
		//p.dimAmount = 0.0f;
		getWindow().setAttributes(p);
		getWindow().setGravity(Gravity.CENTER);
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnleft:
			if(okListener != null){
				okListener.onClick(v);
			}

			break;
		case R.id.btncenter:
			if(okListener != null){
				okListener.onClick(v);
			}
			break;
		case R.id.btnright:
			if(cancelListener != null){
				cancelListener.onClick(v);
			}
			break;
			
		}
		this.cancel();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode){
			case KeyEvent.KEYCODE_HOME:return true;
			case KeyEvent.KEYCODE_BACK:return true;
			case KeyEvent.KEYCODE_CALL:return true;
			case KeyEvent.KEYCODE_SYM: return true;
			case KeyEvent.KEYCODE_VOLUME_DOWN: return true;
			case KeyEvent.KEYCODE_VOLUME_UP: return true;
			case KeyEvent.KEYCODE_STAR: return true;
			}
			return super.onKeyDown(keyCode, event);
	}
@Override
public void show() {
	// TODO Auto-generated method stub
	this.setCanceledOnTouchOutside(false);
	super.show();
}
//	@Override
//	public void onAttachedToWindow() {
//		super.onAttachedToWindow();
//	}
	
}
