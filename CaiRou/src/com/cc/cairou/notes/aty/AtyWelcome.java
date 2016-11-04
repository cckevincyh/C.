package com.cc.cairou.notes.aty;

import com.aphidmobile.flip.FlipViewController;
import com.cc.cairou.R;
import com.cc.cairou.main.MainActivity;
import com.cc.cairou.notes.customview.FlipView;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
}
