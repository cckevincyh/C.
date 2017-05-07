package com.cc.cairou.main;

import java.util.Calendar;
import java.util.TimeZone;

import tyrantgit.explosionfield.ExplosionField;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.cc.cairou.R;

import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;
import com.way.app.Application;
import com.way.util.IphoneDialog;
import com.way.weather.WeatherActivity;


public class MainActivity extends AppCompatActivity {
	 private ExplosionField mExplosionField;
    private RecyclerView rvFeed;
    private static LeftDrawerLayout mLeftDrawerLayout;
    private int mHour = 7;
	private int mMinute = 0;
	private int type = 1;	//选择类型
	public static final long DAY = 1000L * 60 * 60 * 24;
	
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
        everyDayMessage();
        mExplosionField = ExplosionField.attach2Window(this);
    
        
        
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
       
      
        setupFeed(type);
        
      
    }
    
    
    private void setupFeed(int type) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        FeedAdapter feedAdapter = new FeedAdapter(this,mExplosionField,type);
        rvFeed.setAdapter(feedAdapter);
        feedAdapter.updateItems();
    }
    
    

    BDLocationListener mLocationListener = new BDLocationListener() {

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// do nothing
		}

		@Override
		public void onReceiveLocation(BDLocation location) {
		
		}
	};
    

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


 

    @Override
    public void onBackPressed() {
        if (mLeftDrawerLayout.isShownMenu()) {
            mLeftDrawerLayout.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_reset) {
            reset(type);
   
            mExplosionField.clear();
            return true;
        }
        if (item.getItemId() == R.id.action_type1) {
        	type = 1;
            reset(type);
   
            mExplosionField.clear();
            return true;
        }
        if (item.getItemId() == R.id.action_type2) {
        	type = 2;
            reset(type);
   
            mExplosionField.clear();
            return true;
        }
        if (item.getItemId() == R.id.action_type3) {
        	type = 3;
            reset(type);
   
            mExplosionField.clear();
            return true;
        }
        if (item.getItemId() == R.id.action_type4) {
        	type = 4;
            reset(type);
   
            mExplosionField.clear();
            return true;
        }
        if (item.getItemId() == R.id.action_type5) {
        	type = 5;
            reset(type);
   
            mExplosionField.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset(int type) {
    	setupFeed(type);
    }
	
    
    
    
    private void everyDayMessage(){
	
		Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        long firstTime = SystemClock.elapsedRealtime();	// 开机之后到现在的运行时间(包括睡眠时间)
        long systemTime = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
	 	calendar.setTimeInMillis(System.currentTimeMillis());
	 	calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 这里时区需要设置一下，不然会有8个小时的时间差
	 	calendar.set(Calendar.MINUTE, mMinute);
	 	calendar.set(Calendar.HOUR_OF_DAY, mHour);
	 	calendar.set(Calendar.SECOND, 0);
	 	calendar.set(Calendar.MILLISECOND, 0);
	 	
	 	// 选择的每天定时时间
	 	long selectTime = calendar.getTimeInMillis();	

	 	// 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
	 	if(systemTime > selectTime) {
	 	//	Toast.makeText(MainActivity.this, "设置的时间小于当前时间", Toast.LENGTH_SHORT).show();
	 		calendar.add(Calendar.DAY_OF_MONTH, 1);
	 		selectTime = calendar.getTimeInMillis();
	 	}

	 	// 计算现在时间到设定时间的时间差
	 	long time = selectTime - systemTime;
 		firstTime += time;

        // 进行闹铃注册
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        firstTime, DAY, sender);

        //Log.i(TAG, "time ==== " + time + ", selectTime ===== "
    //			+ selectTime + ", systemTime ==== " + systemTime + ", firstTime === " + firstTime);

      //  Toast.makeText(MainActivity.this, "设置重复闹铃成功! ", Toast.LENGTH_LONG).show();

    }
   

}
