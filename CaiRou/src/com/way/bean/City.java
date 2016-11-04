package com.way.bean;

import java.io.Serializable;

public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String city_province;
	private String city_area;
	private String city_id;
	private String city_spell_zh;
	public String getCity_province() {
		return city_province;
	}
	public void setCity_province(String city_province) {
		this.city_province = city_province;
	}
	public String getCity_area() {
		return city_area;
	}
	public void setCity_area(String city_area) {
		this.city_area = city_area;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_spell_zh() {
		return city_spell_zh;
	}
	public void setCity_spell_zh(String city_spell_zh) {
		this.city_spell_zh = city_spell_zh;
	}
	
	public City(){
		
	}
	
	public City(String city_province, String city_area, String city_id,
			String city_spell_zh) {
		super();
		this.city_province = city_province;
		this.city_area = city_area;
		this.city_id = city_id;
		this.city_spell_zh = city_spell_zh;
	}
	
	
	

	
}
