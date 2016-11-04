package com.cc.cairou.notes.aty;

import java.util.ArrayList;
import java.util.List;

import com.cc.cairou.R;
import com.cc.cairou.notes.model.DatabaseHelper;
import com.cc.cairou.notes.model.Notes;
import com.cc.cairou.notes.utils.Utils;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ButtonIcon;
import com.gc.materialdesign.widgets.ColorSelector;
import com.gc.materialdesign.widgets.ColorSelector.OnColorSelectedListener;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenu.SateliteClickedListener;
import android.view.ext.SatelliteMenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class AtyDetail extends AtyBase implements OnColorSelectedListener {
	private ViewFlipper vf;
	private GestureDetector mGestureDetector;
	private String currentTime;
	private ButtonIcon biBack,biDone;
	private ButtonFloat bfAdd;
	private List<Notes> notesList;
	private SatelliteMenu menu;
	private int position;
	private int backgroundColor;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_detail);


		Intent intent=getIntent();
		Bundle bundle=intent.getBundleExtra("bundle");
		notesList=(List<Notes>) bundle.getSerializable("notesList");
		position=intent.getIntExtra("position", 0);
		backgroundColor=notesList.get(position).getBgColor();
		
		initViews();
		mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener(){

			// 手指滑动屏幕的时候 调用的方法 
			// e1 是第一触摸屏幕时候的事件
			// e2 手指离开时候 的事件
			// velocityX x方向的速度
			// velocityY y方向的速度 
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				//如果垂直方向移动的距离过大 就是无效的手势 
				if(Math.abs(e1.getY()-e2.getY()) >100 ){
					return false;
				}
				//判断向右滑动屏幕的事件 
				if(e2.getX()-e1.getX()>100&& Math.abs(velocityX)>100){

					// 指定下一个view对象进来时候的动画效果
					AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
					aa.setDuration(2000);
					vf.setInAnimation(AtyDetail.this,R.anim.in_from_left);
					//设置当前view对象出去时候的动画效果 
					AlphaAnimation outaa = new AlphaAnimation(1.0f, 0.0f);
					aa.setDuration(2000);
					vf.setOutAnimation(AtyDetail.this,R.anim.out_from_right);

					//显示下一个内容 
					showPrevious();
				}
				//判断向左滑动屏幕的事件 
				if(e1.getX()-e2.getX()>100&& Math.abs(velocityX)>100){
					// 指定下一个view对象进来时候的动画效果
					AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
					aa.setDuration(2000);
					vf.setInAnimation(AtyDetail.this,R.anim.in_from_right);
					//设置当前view对象出去时候的动画效果 
					AlphaAnimation outaa = new AlphaAnimation(1.0f, 0.0f);
					aa.setDuration(2000);
					vf.setOutAnimation(AtyDetail.this,R.anim.out_from_left);
					//显示上一个内容 
					showNext();
				}
				return super.onFling(e1, e2, velocityX, velocityY);
			}
		});
		Log.i("tag", "assert");

	}

	private void initViews() {
		biBack=(ButtonIcon) findViewById(R.id.aty_detail_back);
		biDone=(ButtonIcon) findViewById(R.id.aty_detail_done);
		bfAdd=(ButtonFloat) findViewById(R.id.aty_detail_buttonFloat);
		vf=(ViewFlipper) findViewById(R.id.aty_detail_viewFlipper);
		LayoutInflater lf=getLayoutInflater();
		View bodyNote=lf.inflate(R.layout.detail_item, null);
		TextView tvTime=(TextView) bodyNote.findViewById(R.id.aty_detail_time);
		EditText etContent=(EditText)bodyNote.findViewById(R.id.aty_detail_content);
		
		
		//初始化menu
		menu=(SatelliteMenu) findViewById(R.id.menu);
		float distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
		menu.setSatelliteDistance((int) distance);
		menu.setExpandDuration(500);
		menu.setCloseItemsOnClick(true);
		menu.setTotalSpacingDegree(100);
		List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
		items.add(new SatelliteMenuItem(0, R.drawable.ic_palette_black_36dp));
		items.add(new SatelliteMenuItem(1, R.drawable.ic_toys_black_36dp));
		items.add(new SatelliteMenuItem(2, R.drawable.ic_remove_circle_outline_black_36dp));
		menu.addItems(items);        
		menu.setOnItemClickedListener(new SateliteClickedListener() {
			public void eventOccured(int id) {
				switch (id) {
				case 0:
					View view0=vf.getCurrentView();
					//显示调色板
					ColorDrawable dr=(ColorDrawable) view0.getBackground();
					int bgColor=dr.getColor();
					new ColorSelector(AtyDetail.this, bgColor, AtyDetail.this).show();;
					
					
					break;
				case 1:
					View view1=vf.getCurrentView();
					EditText etContent1=(EditText) view1.findViewById(R.id.aty_detail_content);
					share(etContent1.getText().toString());
					break;
				case 2:
					delete();
					break;
				default:
					break;
				}
			}
		});

		tvTime.setText(getStandarTime(notesList.get(position).getTime()));
		etContent.setText(notesList.get(position).getContent());
		bodyNote.setBackgroundColor(notesList.get(position).getBgColor());
		vf.addView(bodyNote);

		biBack.setOnClickListener(this);
		biDone.setOnClickListener(this);
		bfAdd.setOnClickListener(this);
		showButtonDone();
	}

	protected void changeColor(View view,int id) {
		view.setBackgroundColor(id);
	}

	protected void share(String content) {
		Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
	}

	protected void delete() {
		View view=vf.getCurrentView();
		notesList.get(position);
		EditText etContent=(EditText) view.findViewById(R.id.aty_detail_content);
		Notes tempNotes=new Notes();
		tempNotes.setContent(etContent.getText().toString());
		tempNotes.setTime(currentTime);
		tempNotes.setBgColor(backgroundColor);
		DatabaseHelper.getInstance(AtyDetail.this).delete(notesList.get(position));
		
		Animation animation=AnimationUtils.loadAnimation(AtyDetail.this, R.anim.delete_anim);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				back();
			}
		});
		
	}

	private void showButtonDone(){
		View view=vf.getCurrentView();
		EditText etContent=(EditText) view.findViewById(R.id.aty_detail_content);
		etContent.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean arg1) {
				if(arg1){
					biDone.setVisibility(View.VISIBLE);
				}else{
					biDone.setVisibility(View.GONE);
				}
			}
		});
	}
	private void showNext(){
		position=position+1;
		if(position==notesList.size()){
			position=notesList.size()-1;
			Toast.makeText(AtyDetail.this, "最后一页", Toast.LENGTH_SHORT).show();
		}else{
			LayoutInflater lf=getLayoutInflater();
			View bodyNote=lf.inflate(R.layout.detail_item, null);
			TextView tvTime=(TextView) bodyNote.findViewById(R.id.aty_detail_time);
			tvTime.setText(getStandarTime(notesList.get(position).getTime()));
			EditText etContent=(EditText) bodyNote.findViewById(R.id.aty_detail_content);
			etContent.setText(notesList.get(position).getContent());
			bodyNote.setBackgroundColor(notesList.get(position).getBgColor());
			vf.addView(bodyNote);
			vf.showNext();
		}
		

		showButtonDone();

	}

	private void showPrevious(){
		position=position-1;
		if(position==-1){
			position=0;
			Toast.makeText(AtyDetail.this, "第一页", Toast.LENGTH_SHORT).show();
		}else{
			LayoutInflater lf=getLayoutInflater();
			View bodyNote=lf.inflate(R.layout.detail_item, null);
			TextView tvTime=(TextView) bodyNote.findViewById(R.id.aty_detail_time);
			tvTime.setText(getStandarTime(notesList.get(position).getTime()));
			EditText etContent=(EditText) bodyNote.findViewById(R.id.aty_detail_content);
			etContent.setText(notesList.get(position).getContent());
			bodyNote.setBackgroundColor(notesList.get(position).getBgColor());
			vf.addView(bodyNote);
			vf.showPrevious();
		}
		showButtonDone();
	}




	private String getStandarTime(String time){
		String[] strs=time.trim().split(" ");                           //分成2016/1/23 和 22:38
		String t1=strs[0].trim();
		String t2=strs[1].trim();

		int year=Integer.parseInt(t1.split("/")[0]);				//年
		int month=Integer.parseInt(t1.split("/")[1]);				//月
		int day=Integer.parseInt(t1.split("/")[2]);					//日

		int hour=Integer.parseInt(t2.split(":")[0]);			
		int minute=Integer.parseInt(t2.split(":")[1]);
		if(minute<10){
			String min="0"+minute;
			return (year+"年"+month+"月"+day+"日"+" "+hour+":"+min);
		}else{
			return (year+"年"+month+"月"+day+"日"+" "+hour+":"+minute);
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean flag = mGestureDetector.onTouchEvent(ev);
		if(!flag){
			flag = super.dispatchTouchEvent(ev);
		}
		return flag;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 需要通过手势识别器 去识别触摸的动作 
		mGestureDetector.onTouchEvent(event);
		return true;
	}

	private void back(){
		AtyDetail.this.finish();
	}

	private void save(){
		View view=vf.getCurrentView();
		EditText etContent=(EditText) view.findViewById(R.id.aty_detail_content);
		if(TextUtils.isEmpty(etContent.getText())){
			back();
		}else{
			currentTime=Utils.getCurrentTime();
			Notes tempNotes=new Notes();
			tempNotes.setContent(etContent.getText().toString());
			tempNotes.setTime(currentTime);
			tempNotes.setBgColor(backgroundColor);
			//更新数据库
			DatabaseHelper.getInstance(AtyDetail.this).update(notesList.get(position), tempNotes);
			back();
		}

	}
	
	private void saveBgColor(int color){
		View view=vf.getCurrentView();
		EditText etContent=(EditText) view.findViewById(R.id.aty_detail_content);
		currentTime=Utils.getCurrentTime();
		Notes tempNotes=new Notes();
		tempNotes.setContent(etContent.getText().toString());
		tempNotes.setTime(currentTime);
		tempNotes.setBgColor(color);
		//更新数据库
		DatabaseHelper.getInstance(AtyDetail.this).update(notesList.get(position), tempNotes);
	}
	
	private  void addNewNote(){
		Intent intent=new Intent();
		intent.setClass(AtyDetail.this, AtyCreate.class);
		startActivity(intent);
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.aty_detail_back:
			back();
			break;
		case R.id.aty_detail_done:
			save();
			break;
		case R.id.aty_detail_buttonFloat:
			addNewNote();
			break;
		default:
			break;
		}
	}

	@Override
	public void onColorSelected(int color) {
		backgroundColor = color;
		View view0=vf.getCurrentView();
		
		changeColor(view0, color);
		
		saveBgColor(backgroundColor);
	}

	


}
