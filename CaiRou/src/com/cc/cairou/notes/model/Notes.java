package com.cc.cairou.notes.model;

import java.io.Serializable;

public class Notes implements Serializable{
	
	
	private String content;
	private String time;
	private int bgColor;
	
	public int getBgColor() {
		return bgColor;
	}

	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}

	public Notes(String pContent,String pTime){
		this.content=pContent;
		this.time=pTime;
	}
	
	public Notes(){
		
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
