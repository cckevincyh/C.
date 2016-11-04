package com.way.bean;
/**
 * 
 * @author c
 *   private String aqi;//空气质量指数
 *   private String co;
 *   private String no2;
 *   private String o3;
 *   private String pm10;
 *   private String pm25;//pm2.5平均值
 *   private String qlty;//空气质量类别
 *   private String so2;
 */
public  class CityBean {
	  /**
     * aqi : 42
     * co : 1
     * no2 : 18
     * o3 : 104
     * pm10 : 38
     * pm25 : 22
     * qlty : 优
     * so2 : 8
     */
    private String aqi;//空气质量指数
    private String co;
    private String no2;
    private String o3;
    private String pm10;
    private String pm25;//pm2.5平均值
    private String qlty;//空气质量类别
    private String so2;

    /**
     * //空气质量指数
     * @return
     */
    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    /**
     * pm2.5平均值
     * @return
     */
    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getQlty() {
        return qlty;
    }

    /**
     * 空气质量类别
     * @param qlty
     */
    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }
}