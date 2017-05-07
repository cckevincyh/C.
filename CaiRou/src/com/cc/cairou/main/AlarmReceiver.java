package com.cc.cairou.main;

import java.util.Calendar;
import java.util.Random;
import com.cc.cairou.R;


import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.widget.Toast;

/**
 * 
 * @ClassName: AlarmReceiver  
 * @Description: 闹铃时间到了会进入这个广播，这个时候可以做一些该做的业务。
 * @author HuHood
 * @date 2013-11-25 下午4:44:30  
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	
	private String[] message = {
			"不好意思，今天没推送,不信你点?。",
			"别看了，今天没推送,不信你点?",
			"戳我看看?",
			"[点击查看]"
			
			
	};
	
	
	
	
	
	@Override
    public void onReceive(Context context, Intent intent) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int length = message.length;
		Random random = new Random();
		int index = random.nextInt(length);

		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification.Builder builder1 = new Notification.Builder(context);
		builder1.setSmallIcon(R.drawable.chat); //设置图标
		builder1.setTicker("C: 你有一则来自cc的未读消息。"); 
		builder1.setContentTitle("给你"); //设置标题
		
	
		builder1.setContentText(message[index]); //消息内容
		
		
		builder1.setWhen(System.currentTimeMillis()); //发送时间
		builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
		builder1.setAutoCancel(true);//打开程序后图标消失
		Intent intent1 =new Intent (context,MessageActivity.class);
	    // 创建一个PendingIntent，和Intent类似，不同的是由于不是马上调用，需要在下拉状态条出发的activity，所以采用的是PendingIntent,即点击Notification跳转启动到哪个Activity  
		PendingIntent pendingIntent =PendingIntent.getActivity(context, 0, intent1, 0);
		builder1.setContentIntent(pendingIntent);
		Notification notification1 = builder1.build();
		manager.notify(124, notification1); // 通过通知管理器发送通知
    }

}
