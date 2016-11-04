package com.way.bean;
/**
 * 风的信息
 * @author c
 *
 *   private String deg;//风向(角度)
 *   private String dir;//风向(方向)
 *   private String sc;//风力等级
 *   private String spd;//风速(Kmph)
 */
public  class WindBean {
    private String deg;//风向(角度)
    private String dir;//风向(方向)
    private String sc;//风力等级
    private String spd;//风速(Kmph)

    /**
     * 风向(角度)
     * @return
     */
    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    /**
     * 风向(方向)
     * @return
     */
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * 风力等级
     * @return
     */
    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    /**
     * 风速(Kmph)
     * @return
     */
    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }
}