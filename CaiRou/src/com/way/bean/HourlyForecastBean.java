package com.way.bean;

/**
 * hourly_forecast 每小时天气预报
 * @author c
 *   private String date;//当地日期和时间
 *   private String hum;//湿度(%)
 *   private String pop;
 *   private String pres;
 *   private String tmp;//当前温度(摄氏度)
 *   private WindBean wind;//风力状况
 */
public  class HourlyForecastBean {
    private String date;//当地日期和时间
    private String hum;//湿度(%)
    private String pop;
    private String pres;
    private String tmp;//当前温度(摄氏度)
    /**
     * deg : 35
     * dir : 东北风
     * sc : 3-4
     * spd : 27
     */

    private WindBean wind;//风力状况

    /**
     * 当地日期和时间
     * @return
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 湿度(%)
     * @return
     */
    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    /**
     * 当前温度(摄氏度)
     * @return
     */
    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

   

}