package com.cc.cairou.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.Toast;

import com.cc.cairou.R;
import com.cc.cairou.memo.MemoActivity;
import com.cc.cairou.notes.aty.AtyList;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.mxn.soul.flowingdrawer_core.MenuFragment;
import com.squareup.picasso.Picasso;
import com.way.weather.WeatherActivity;


//左侧的列表
public class MyMenuFragment extends MenuFragment {

   

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        
        
        
        //得到左侧的列表
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.vNavigation);
        //设置监听
        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
			
			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem) {
				String title = (String) menuItem.getTitle();
				Toast.makeText(getActivity(), "您点击了 " + title, Toast.LENGTH_SHORT).show();
				switch (title) {
				case "随手记":
					Intent intent1 = new Intent(getActivity(), MemoActivity.class);
					startActivity(intent1);
					MainActivity.handler.sendEmptyMessage(1);//通知关闭左侧栏
					break;
				case "便签":
					Intent intent2 = new Intent(getActivity(), AtyList.class);
					startActivity(intent2);
					MainActivity.handler.sendEmptyMessage(1);//通知关闭左侧栏
					break;
					
				case "天气":
					Intent intent4 = new Intent(getActivity(), WeatherActivity.class);
					startActivity(intent4);
					MainActivity.handler.sendEmptyMessage(1);//通知关闭左侧栏
					break;
				default:
					break;
				}
				return false;
			}
		});
       
        return  setupReveal(view) ;
    }
    
    


    public void onOpenMenu(){
        Toast.makeText(getActivity(),"onOpenMenu",Toast.LENGTH_SHORT).show();
        
    }
    public void onCloseMenu(){
        Toast.makeText(getActivity(),"onCloseMenu",Toast.LENGTH_SHORT).show();
    }
    
    
}
