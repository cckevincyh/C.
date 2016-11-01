package com.cc.cairou.memo;


import java.util.ArrayList;

import com.cc.cairou.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MemoActivity extends ActionBarActivity {
	
	private ArrayList<String> typeArray = new ArrayList<String>();
    private ArrayList<String> memoArray = new ArrayList<String>();//默认的第一个的左侧列表的第一个的右列表的项目集合
    private ArrayList<TypeItem> typeItemArrayList = new ArrayList<TypeItem>();//左侧的所有的列表集合
    public static int selectedTypeItemNum = 0;      //默认第一个被选中，左侧，当前选中的左侧项目
    private TypeItemAdapter left_adapter;
    private MemoItemAdapter right_adapter;
	
    //声明相关变量
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        findViews(); //获取控件
        toolbar.setTitle("随手记");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
               
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
             
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
       
        lvLeftMenu.setAdapter(arrayAdapter);
        //左侧列表的项目点击监听
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MemoActivity.this, "您点击了 " + position, Toast.LENGTH_SHORT).show();
			}
		
		});
    }
    private void findViews() {
      
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
    }
}