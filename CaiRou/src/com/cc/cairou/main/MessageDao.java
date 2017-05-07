package com.cc.cairou.main;

import java.util.Random;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessageDao {

	//1,指定访问数据库的路径
	public static String path = "data/data/com.cc.cairou/files/message.db";
	
	 
	
	 /** 
	  * 查询数据库中的总条数. 
	  * @return 
	  */  
	 public static int allCaseNum( ){  
		//2,开启数据库连接(只读的形式打开)
		SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
	     String sql = "select count(*) from MESSAGE";  
	     Cursor cursor = db.rawQuery(sql, null);  
	     cursor.moveToFirst();  
	     int count = cursor.getInt(0);  
	     cursor.close();  
	     return count;  
	 }  
	 
	 
	 /**
	  * 随机得到一条消息
	  * @return
	  */
	 public static String getMessage(){
		Random random = new Random();
		int id =  random.nextInt(allCaseNum());
		String sql = "select message from MESSAGE where id="+id;
		//2,开启数据库连接(只读的形式打开)
		SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
		Cursor cursor = db.rawQuery(sql, null); 
		cursor.moveToFirst();  
		String message = cursor.getString(0);
		return message;
	 }
	 
}
