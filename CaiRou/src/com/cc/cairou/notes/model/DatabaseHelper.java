package com.cc.cairou.notes.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String CREATE_NOTES = "create table note ("
			+ "id integer primary key autoincrement, "
			+ "content text, "
			+ "time text,"
			+ "bgcolor interger)";
	
	private static final String TABLE_NAME="note";
	
	public  static DatabaseHelper instance=null;
	private Context mContext;
	private SQLiteDatabase db=null;
	
	private void openDatabase(){
		if(db==null){
			db=instance.getWritableDatabase();
		}
	}
	
	public static DatabaseHelper getInstance(Context context){
		if(instance==null){
			instance=new DatabaseHelper(context, "NOTES.db", null, 1);
		}
		return instance;
	}
	
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}
	
	/*
	 * 增删改查
	 * */
	
	//插入数据
	public void insert(Notes notes){
		openDatabase();
		ContentValues values=new ContentValues();
		values.put("content", notes.getContent());
		values.put("time", notes.getTime());
		values.put("bgcolor", notes.getBgColor());
		db.insert(TABLE_NAME, null, values);
	} 
	
	//删除数据
	public void delete(Notes notes){
		openDatabase();
		db.delete(TABLE_NAME, "content=? and time=?", new String[]{notes.getContent(),notes.getTime()});
	}
	
	//查询数据
	public Notes querySingle(Notes notes){
		Notes tempNotes=new Notes();
		openDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, "content=? and time=?", new String[]{notes.getContent(),notes.getContent()}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				tempNotes.setContent(cursor.getString(cursor.getColumnIndex("content")));
				tempNotes.setTime(cursor.getString(cursor.getColumnIndex("time")));
				tempNotes.setBgColor(cursor.getInt(cursor.getColumnIndex("bgcolor")));
			}while(cursor.moveToNext());
		}
		cursor.close();
		return tempNotes;
	}
	
	//查询所有数据
	public List<Notes> queryAll(){
		List<Notes> lists=new ArrayList<Notes>();
		openDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Notes tempNotes=new Notes();
				tempNotes.setContent(cursor.getString(cursor.getColumnIndex("content")));
				tempNotes.setTime(cursor.getString(cursor.getColumnIndex("time")));
				tempNotes.setBgColor(cursor.getInt(cursor.getColumnIndex("bgcolor")));
				lists.add(tempNotes);
				
			}while(cursor.moveToNext());
		}
		cursor.close();
		return lists;
		
	}
	
	//更新数据
	public void update(Notes keyNotes,Notes notes){
		openDatabase();
		ContentValues values=new ContentValues();
		values.put("content", notes.getContent());
		values.put("time", notes.getTime());
		values.put("bgcolor", notes.getBgColor());
		db.update(TABLE_NAME, values, "content=?", new String[]{keyNotes.getContent()});
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_NOTES);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	
}
