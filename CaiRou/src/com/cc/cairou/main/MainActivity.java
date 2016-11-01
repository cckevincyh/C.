package com.cc.cairou.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.cc.cairou.R;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFeed;
    private static LeftDrawerLayout mLeftDrawerLayout;
    
    
    public static Handler handler = new Handler(){
    	@Override
    	public void handleMessage(Message msg) {
    		if(msg.what==1){
    			//关闭左侧栏
    			mLeftDrawerLayout.closeDrawer();
    		}
    		super.handleMessage(msg);
    	}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     
        
        
        //设置ToolBar
        setupToolbar();

        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);

        FragmentManager fm = getSupportFragmentManager();
        MyMenuFragment mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
      
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);
       
        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment()).commit();
        }
        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);
       
      
        
        //设置中间的内容
        setupFeed();
        
      
    }


    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.toggle();
            }
        });
    }


    //设置中间的内容。
    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        FeedAdapter feedAdapter = new FeedAdapter(this);
        rvFeed.setAdapter(feedAdapter);
        feedAdapter.updateItems();
    }

    @Override
    public void onBackPressed() {
        if (mLeftDrawerLayout.isShownMenu()) {
            mLeftDrawerLayout.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }


	
}
