package com.cc.cairou.notes.utils;

import java.util.Calendar;

import com.cc.cairou.notes.model.Notes;



public class Utils {
	
	public static String getCurrentTime(){
		Calendar calendar=Calendar.getInstance();
		return String.format("%d/%d/%d %d:%d", calendar.get(Calendar.YEAR),
											   calendar.get(Calendar.MONTH)+1,
											   calendar.get(Calendar.DAY_OF_MONTH),
											   calendar.get(Calendar.HOUR_OF_DAY),
											   calendar.get(Calendar.MINUTE));
		
	}
	
	public static String getDeltaTime(Notes notes){
		String deltaTime=null;
		Calendar calendar=Calendar.getInstance();
		float currentTimeMillions=calendar.get(Calendar.MINUTE)*60*1000+
						  		  calendar.get(Calendar.HOUR_OF_DAY)*60*60*1000+
						  		  calendar.get(Calendar.DAY_OF_MONTH)*24*60*60*1000;
		
		int curYear=calendar.get(Calendar.YEAR);
		int curMonth=calendar.get(Calendar.MONTH)+1;
		int curDay=calendar.get(Calendar.DAY_OF_MONTH);
		
		String lastTime=notes.getTime().trim();                      //形如 2016/1/23 22:38
		String[] strs=lastTime.split(" ");                           //分成2016/1/23 和 22:38
		String t1=strs[0].trim();
		String t2=strs[1].trim();
		
		int year=Integer.parseInt(t1.split("/")[0]);				//年
		int month=Integer.parseInt(t1.split("/")[1]);				//月
		int day=Integer.parseInt(t1.split("/")[2]);					//日
		
		int hour=Integer.parseInt(t2.split(":")[0]);
		int minute=Integer.parseInt(t2.split(":")[1]);
		
		float lastTimeMillions=minute*60*1000+
							   hour*60*60*1000+
							   day*24*60*60*1000;
		
		float timeDelta=currentTimeMillions-lastTimeMillions;
		if(timeDelta<24*60*60*1000&&curDay==day){
			String min=null;
			if(minute<10){
				min="0"+minute;
			}else{
				min=String.valueOf(minute);
			}
			deltaTime=hour+":"+min;
		}
		//昨天
		else if((timeDelta>24*60*60*1000&&timeDelta<48*60*60*1000)||
				(year==curYear&&month==curMonth&&day+1==curDay)||//同一年同一个月
				(year==curYear&&month+1==curMonth&&day>=28&&curDay==1&&timeDelta<48*60*60*1000)||//同一年不同月
				(year+1==curYear&&month==12&&curMonth==1&&timeDelta<48*60*60*1000))//前一年最后一天和这一年第一天
		{
			deltaTime= "昨天";
		}else if(timeDelta>48*60*60*1000){
			deltaTime= year+"/"+month+"/"+day;
		}
		return deltaTime;
	}
	
	
	
}
