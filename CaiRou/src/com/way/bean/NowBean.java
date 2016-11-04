package com.way.bean;


/**
 * now 实况天气
 * @author c
 *  private CondBean cond;//天气状况
 *  private String fl;//体感温度
 *  private String hum;//湿度(%)
 *  private String pcpn;//降雨量(mm)
 *  private String pres;
 *  private String tmp;
 *  private String vis;//能见度(km)
 *  private WindBean wind;//风的情况
 */
public  class NowBean {
    /**
     * code : 100
     * txt : 晴
     */

    private CondBean cond;//天气状况
    private String fl;//体感温度
    private String hum;//湿度(%)
    private String pcpn;//降雨量(mm)
    private String pres;
    private String tmp;
    private String vis;//能见度(km)
    /**
     * deg : 10
     * dir : 东北风
     * sc : 5-6
     * spd : 25
     */

    private WindBean wind;//风的情况

    /**
     * 天气状况
     * @return
     */
    public CondBean getCond() {
        return cond;
    }

    public void setCond(CondBean cond) {
        this.cond = cond;
    }

    /**
     * //体感温度
     * @return
     */
    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
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

    /**
     * 降雨量(mm)
     * @return
     */
    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    /**
     * 能见度(km)
     * @return
     */
    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

   

   
}
