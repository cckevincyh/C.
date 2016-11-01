package com.cc.cairou.news;


import com.cc.cairou.R;
import com.cc.cairou.memo.MemoActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

	public class NewsActivity extends ActionBarActivity
		implements NavigationView.OnNavigationItemSelectedListener {
		  //声明相关变量
	    private Toolbar toolbar;
		private DrawerLayout mDrawerLayout;
		private NavigationView mNavigationView;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_news);
		
			  toolbar = (Toolbar) findViewById(R.id.tl_custom);
		       toolbar.setTitle("CaiRou");//设置Toolbar标题
		       toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
			
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
			
			mNavigationView.setNavigationItemSelectedListener(this);
		
		
		}
		
	@Override
		public boolean onNavigationItemSelected(MenuItem menuItem) {
		
		
			
			String title = (String) menuItem.getTitle();
			Toast.makeText(this, "您点击了 " + title, Toast.LENGTH_SHORT).show();
			
			
			return super.onContextItemSelected(menuItem);
		
		}
	
	
	}
