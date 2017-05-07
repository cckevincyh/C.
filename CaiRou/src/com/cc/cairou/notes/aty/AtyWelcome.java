package com.cc.cairou.notes.aty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.aphidmobile.flip.FlipViewController;
import com.cc.cairou.R;
import com.cc.cairou.main.MainActivity;
import com.cc.cairou.notes.customview.FlipView;

public class AtyWelcome extends AtyBase {
	private FlipViewController flipView;
	private int[] imageArray={
			R.drawable.ic_1,
			R.drawable.ic_2,
			R.drawable.ic_3,
			R.drawable.ic_4,
			R.drawable.ic_5};

	private int[] rootArray={
			R.drawable.widget_4x_blue,
			R.drawable.widget_4x_green,
			R.drawable.widget_4x_red,
			R.drawable.widget_4x_white,
			R.drawable.widget_4x_yellow
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences pref=this.getSharedPreferences("mypref",MODE_PRIVATE);
		//引入外部数据库	
		initDB("message.db");
		
		
		SharedPreferences sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
		
		 //根据sp中判断是否生成过快捷方式
        if(!sp.getBoolean("has_shortcut", false)){
        	//生成快捷方式
            initShortCut();
      
        }
		
		
		
		//第一次打开,播放引导界面
		if(pref.getString("my_id", null)==null){
			SharedPreferences.Editor mEditor=pref.edit();
			mEditor.putString("my_id", "id");
			mEditor.commit();
			//开始引导动画
			flipView = new FlipViewController(this);
			flipView.setAnimationBitmapFormat(Bitmap.Config.RGB_565);
			flipView.setAdapter(new TravelAdapter());
			setContentView(flipView);
		}else{
			//不是第一次打开，进入到主界面
			
			openActivity(MainActivity.class);
			this.finish();
		}
	}

	class TravelAdapter extends BaseAdapter{

		public TravelAdapter() {
		}

		@Override
		public int getCount() {
			return imageArray.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			FlipView view;
			if (convertView == null) {
				final Context context = parent.getContext();
				view = new FlipView(context,rootArray[position],imageArray[position]);

			} else {
				view = (FlipView) convertView;
				view.setButtonBackground(AtyWelcome.this);
				view.setRootBackground(rootArray[position]);
				view.setImageBackground(imageArray[position]);
			}

			return view;
		}
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	
	/**
	 * 拷贝数据库值files文件夹下
	 * @param dbName	数据库名称
	 */
	private void initDB(String dbName) {
		//1,在files文件夹下创建同名dbName数据库文件过程
		File files = getFilesDir();
		File file = new File(files, dbName);
		if(file.exists()){
			return;
		}
		InputStream stream = null;
		FileOutputStream fos = null;
		//2,输入流读取第三方资产目录下的文件
		try {
			stream = getAssets().open(dbName);
			//3,将读取的内容写入到指定文件夹的文件中去
			fos = new FileOutputStream(file);
			//4,每次的读取内容大小
			byte[] bs = new byte[1024];
			int temp = -1;
			while( (temp = stream.read(bs))!=-1){
				fos.write(bs, 0, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(stream!=null && fos!=null){
				try {
					stream.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	/**
	 * 生成快捷方式(需要权限)
	 */
	private void initShortCut() {
		//1,给intent维护图标,名称
		Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		//维护图标
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, 
				BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		//名称
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "菜肉");
		//2,点击快捷方式后跳转到的activity
		//2.1维护开启的意图对象
		Intent shortCutIntent = new Intent("android.intent.action.HOME");
		shortCutIntent.addCategory("android.intent.category.DEFAULT");
		
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortCutIntent);
		//3,发送广播
		sendBroadcast(intent);
		//4,告知sp已经生成快捷方式
		SharedPreferences sp = this.getSharedPreferences("config", Context.MODE_PRIVATE);
		sp.edit().putBoolean("has_shortcut", true).commit();
		

	
	}
	
}
