package com.cc.cairou.notes.aty;

import com.cc.cairou.R;
import com.cc.cairou.notes.model.DatabaseHelper;
import com.cc.cairou.notes.model.Notes;
import com.cc.cairou.notes.utils.Utils;
import com.gc.materialdesign.views.ButtonIcon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AtyCreate extends AtyBase {
	private ButtonIcon biBack,biDone;
	private TextView tvTime;
	private EditText etContent;
	private LinearLayout llLayout;
	private String currentTime;
	private int backgroundColor=Color.WHITE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_create);
		initViews();
		//intent传递进来一个资源ID
		backgroundColor=getIntent().getIntExtra("background_color",0);
		setBackgroundColor(backgroundColor);
		//设定当前时间
		setTime();
	}
	
	private void initViews(){
		biBack=(ButtonIcon) findViewById(R.id.aty_create_back);
		biDone=(ButtonIcon) findViewById(R.id.aty_create_done);
		tvTime=(TextView) findViewById(R.id.aty_create_time);
		etContent=(EditText) findViewById(R.id.aty_create_content);
		llLayout=(LinearLayout) findViewById(R.id.aty_create_layout);
		
		biBack.setOnClickListener(this);
		biDone.setOnClickListener(this);
	}
	
	private void setBackgroundColor(int id){
		llLayout.setBackgroundColor(id);
	}
	
	private void setTime(){
		currentTime=Utils.getCurrentTime();
		String[] strs=currentTime.trim().split(" ");                           //分成2016/1/23 和 22:38
		String t1=strs[0].trim();
		String t2=strs[1].trim();
		
		int year=Integer.parseInt(t1.split("/")[0]);				//年
		int month=Integer.parseInt(t1.split("/")[1]);				//月
		int day=Integer.parseInt(t1.split("/")[2]);					//日
		
		int hour=Integer.parseInt(t2.split(":")[0]);			
		int minute=Integer.parseInt(t2.split(":")[1]);
		if(minute<10){
			String min="0"+minute;
			tvTime.setText(year+"年"+month+"月"+day+"日"+" "+hour+":"+min);
		}else{
			tvTime.setText(year+"年"+month+"月"+day+"日"+" "+hour+":"+minute);
		}
		
	}
	
	private void back(){
		AtyCreate.this.finish();
	}
	
	private void save(){
		if(TextUtils.isEmpty(etContent.getText())){
			back();
		}else{
			Notes notes=new Notes();
			notes.setContent(etContent.getText().toString());
			notes.setTime(currentTime);
			//存到数据库
			DatabaseHelper.getInstance(this).insert(notes);
			back();
		}
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.aty_create_back:
			back();
			break;
		case R.id.aty_create_done:
			save();
			break;
		default:
			break;
		}
	}
}
