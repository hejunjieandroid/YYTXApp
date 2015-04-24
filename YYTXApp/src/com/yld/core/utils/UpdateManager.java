package com.yld.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;

import com.yld.yytxapp.entity.UpDateInfo;
import com.yld.yytxapp.ui.R;
/**
 * 更新客户端
 */

public class UpdateManager {
	/* 下载中 */
	private static final int DOWNLOAD = 1;
	/* 下载结束 */
	private static final int DOWNLOAD_FINISH = 2;
	/* 保存解析的XML信息 */
	HashMap<String, String> mHashMap;
	/* 下载保存路径 */
	private String mSavePath;
	/* 记录进度条数量 */
	private int progress;
	/* 是否取消更新 */
	private boolean cancelUpdate = false;

	private Context mContext;
	/* 更新进度条 */
	private ProgressBar mProgress;
	private Dialog mDownloadDialog;

	public static String SIGNATURE_FLAG="";
	public static String APKMD5_FLAG="";
	
	public UpdateCallBack updateCallBack = null;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				// 设置进度条位置
				mProgress.setProgress(progress);
				break;
			case DOWNLOAD_FINISH:
				// 安装文件
				installApk();
				break;
			default:
				break;
			}
		};
	};

	public UpdateManager(Context context) {
		this.mContext = context;
	}

	/**
	 * 检测软件更新
	 */
	public void checkUpdate(UpDateInfo upDateInfo,UpdateCallBack updateBack) {
		this.updateCallBack = updateBack;
		
		mHashMap=new HashMap<String, String>();
		mHashMap.put("url", upDateInfo.getUpdateUrl());
		mHashMap.put("dec", upDateInfo.getUpdateHint());
		mHashMap.put("appsize", 10*1024*1024+"");
		mHashMap.put("appname", "MobileBack"+upDateInfo.getUpdateVersionName());
		
		if("1".equals(upDateInfo.getUpdateMode())){
			//强制更新
			showMustNoticeDialog();
		}else {
			//有新的版本
			showNoticeDialog();	
		}
	}
	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog() {
		String msg = mHashMap.get("dec");
		if ("".equals(msg)||msg == null) {
			msg = "你好，检测到新版本，是要立即更新吗?";
		}
		// 构造对话框
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setMessage(msg);
		// 更新
		builder.setPositiveButton("现在更新",
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载对话框
						showDownloadDialog();
					}
				});
		// 稍后更新
		builder.setNegativeButton("稍后更新",
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (updateCallBack!=null) {
							updateCallBack.updatePass();
						}
					}
				});
		Dialog mustnoticeDialog = builder.create();
		mustnoticeDialog.setCanceledOnTouchOutside(false);
		mustnoticeDialog.show();
		mustnoticeDialog.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
	private void showMustNoticeDialog() {
		String msg = mHashMap.get("dec");
		if ("".equals(msg)||msg == null) {
			msg = "你好，检测到新版本，是要立即更新吗?";
		}
		// 构造对话框
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setMessage(msg);
		// 更新
		builder.setPositiveButton("现在更新",
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 显示下载对话框
						showDownloadDialog();
					}
				});
		Dialog noticeDialog = builder.create();
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.show();
		noticeDialog.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}

	/**
	 * 显示软件下载对话框
	 */
	private void showDownloadDialog() {
		// 构造软件下载对话框
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("正在下载更新");
		// 给下载对话框增加进度条
		LinearLayout layout = new LinearLayout(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(params);
		mProgress = (ProgressBar) LayoutInflater.from(mContext).inflate(R.layout.progressbar, null);
		mProgress.setLayoutParams(params);
		layout.addView(mProgress);
		builder.setView(layout);
		// 取消更新
		builder.setNegativeButton("取消",
					new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							int pid=android.os.Process.myPid();
							android.os.Process.killProcess(pid);
							// 设置取消状态
						}
					});
		mDownloadDialog = builder.create();
		mDownloadDialog.setCanceledOnTouchOutside(false);
		mDownloadDialog.show();
		mDownloadDialog.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		// 现在文件
		downloadApk();
	}

	/**
	 * 下载apk文件
	 */
	private void downloadApk() {
		// 启动新线程下载软件
		new downloadApkThread().start();
	}

	/**
	 * 下载文件线程
	 * 
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {
			Looper.prepare();
			try {
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					// 获得存储卡的路径
					String sdpath = Environment.getExternalStorageDirectory()
							+ "/";
					mSavePath = sdpath + "download";
					URL url = new URL(mHashMap.get("url"));
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					if(length == -1){
						length = Integer.parseInt(mHashMap.get("appsize"));
					}
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(mSavePath, mHashMap.get("appname"));
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					// 缓存
					byte buf[] = new byte[1024];
					// 写入到文件中
					do {
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0) {
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.close();
					is.close();
				}else{
					mSavePath = "/data/data/" +mContext.getPackageName();
					URL url = new URL(mHashMap.get("url"));
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					if(length == -1){
						length = Integer.parseInt(mHashMap.get("appsize"));
					}
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(mSavePath, mHashMap.get("appname"));
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					// 缓存
					byte buf[] = new byte[1024];
					// 写入到文件中
					do {
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0) {
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.close();
					is.close();
				}
			} catch (MalformedURLException e) {
				updateFailure();
			} catch (IOException e) {
				updateFailure();
			}
			// 取消下载对话框显示
			mDownloadDialog.dismiss();
		}
	};
	public void updateFailure(){
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setMessage("客户端更新失败！");
		//关闭客户端
		builder.setPositiveButton("退出",
				new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						int pid=android.os.Process.myPid();
						android.os.Process.killProcess(pid);
					}
				});
		Dialog mustnoticeDialog = builder.create();
		mustnoticeDialog.setCanceledOnTouchOutside(false);
		mustnoticeDialog.show();
		mustnoticeDialog.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkfile = new File(mSavePath, mHashMap.get("appname"));
		if (!apkfile.exists()) {
			return;
		}
		String cmd = "chmod 777 " +apkfile.toString(); 
        try
        {
                Runtime.getRuntime().exec(cmd);
        }catch (Exception e) {
                e.printStackTrace();
        }   
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
        //关闭当前程序
		int pid=android.os.Process.myPid();
		android.os.Process.killProcess(pid);
	}
	// 判断sdcard是否存在
	private boolean ExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	// 取出sdcard剩余空间
	private long getSDFreeSize() {
		// 取得SD卡文件路径
		File path = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(path.getPath());
		// 获取单个数据块的大小(Byte)
		long blockSize = sf.getBlockSize();
		// 空闲的数据块的数量
		long freeBlocks = sf.getAvailableBlocks();
		// 返回SD卡空闲大小
		// return freeBlocks * blockSize; //单位Byte
		// return (freeBlocks * blockSize)/1024; //单位KB
		return (freeBlocks * blockSize) / 1024 / 1024; // 单位MB
	}
	private long getSDAllSize() {
		// 取得SD卡文件路径
		File path = Environment.getExternalStorageDirectory();
		StatFs sf = new StatFs(path.getPath());
		// 获取单个数据块的大小(Byte)
		long blockSize = sf.getBlockSize();
		// 获取所有数据块数
		long allBlocks = sf.getBlockCount();
		// 返回SD卡大小
		// return allBlocks * blockSize; //单位Byte
		// return (allBlocks * blockSize)/1024; //单位KB
		return (allBlocks * blockSize) / 1024 / 1024; // 单位MB
	}
	public interface UpdateCallBack{
		public void updatePass();
	}
}
