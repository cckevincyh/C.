package com.way.bean;
/**
 * 
 * @author c
 *  private String sr;//日出时间
 *   private String ss;//日落时间
 */
public  class AstroBean {
    private String sr;//日出时间
    private String ss;//日落时间

    /**
     * //日出时间
     * @return
     */
    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    /**
     * //日落时间
     * @return
     */
    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }
}