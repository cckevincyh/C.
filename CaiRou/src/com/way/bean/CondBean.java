package com.way.bean;
/**
 *  private String code_d;
 *   private String code_n;
 *   private String txt_d;//白天天气描述
 *   private String txt_n;//夜间天气描述
 * @author c
 *
 */
public  class CondBean {
    private String code_d;
    private String code_n;
    private String txt_d;//白天天气描述
    private String txt_n;//夜间天气描述

    public String getCode_d() {
        return code_d;
    }

    public void setCode_d(String code_d) {
        this.code_d = code_d;
    }

    public String getCode_n() {
        return code_n;
    }

    public void setCode_n(String code_n) {
        this.code_n = code_n;
    }

    /**
     * 白天天气描述
     * @return
     */
    public String getTxt_d() {
        return txt_d;
    }

    public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
    }

    /**
     * 夜间天气描述
     * @return
     */
    public String getTxt_n() {
        return txt_n;
    }

    public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
    }
}