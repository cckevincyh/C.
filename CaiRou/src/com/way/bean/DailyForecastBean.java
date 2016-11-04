package com.way.bean;

/**
 * daily_forecast 天气预报
 * @author c
 *
 *   private AstroBean astro;
 *   private CondBean cond;
 *   private String date;//当地日期
 *   private String hum;//湿度(%)
 *   private String pcpn;
 *   private String pop;//降水概率
 *   private String pres;
 *   private TmpBean tmp;//温度
 *   private String vis;
 *   private WindBean wind;
 *
 */
public  class DailyForecastBean {
    /**
     * sr : 06:30
     * ss : 17:48
     */

    private AstroBean astro;
    /**
     * code_d : 100
     * code_n : 100
     * txt_d : 晴
     * txt_n : 晴
     */

    private CondBean cond;
    private String date;//当地日期
    private String hum;//湿度(%)
    private String pcpn;
    private String pop;//降水概率
    private String pres;
    /**
     * max : 26
     * min : 19
     */

    private TmpBean tmp;//温度
    private String vis;
    /**
     * deg : 31
     * dir : 无持续风向
     * sc : 微风
     * spd : 8
     */

    private WindBean wind;

    public AstroBean getAstro() {
        return astro;
    }

    public void setAstro(AstroBean astro) {
        this.astro = astro;
    }

    public CondBean getCond() {
        return cond;
    }

    public void setCond(CondBean cond) {
        this.cond = cond;
    }

    /**
     * 当地日期
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

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    /**
     * 降水概率
     * @return
     */
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
     * 温度
     * @return
     */
    public TmpBean getTmp() {
        return tmp;
    }

    public void setTmp(TmpBean tmp) {
        this.tmp = tmp;
    }

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
