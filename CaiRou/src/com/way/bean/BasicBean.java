package com.way.bean;

/**
 * basic 城市基本信息
 * @author c
 *
 *	private String city;//城市
 *  private String cnty;//国家名称
 *  private String id;//城市id
 *  private String lat;//纬度
 *  private String lon;//经度
 */
public  class BasicBean {
    private String city;//城市
    private String cnty;//国家名称
    private String id;//城市id
    private String lat;//纬度
    private String lon;//经度
    /**
     * loc : 2016-11-02 17:54
     * utc : 2016-11-02 09:54
     */

    private UpdateBean update;//数据更新时间，24小时制

   /**
    * 获得城市名称
    * @return
    */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市名称
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获得国家名称
     * @return
     */
    public String getCnty() {
        return cnty;
    }

    /**
     * 设置国家名称
     * @param cnty
     */
    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    /**
     * 获得城市id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置城市id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得纬度
     * @return
     */
    public String getLat() {
        return lat;
    }

    /**
     * 设置纬度
     * @param lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * 获得经度
     * @return
     */
    public String getLon() {
        return lon;
    }

    /**
     * 设置经度
     * @param lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * 获得更新时间，24小时制度
     * @return
     */
    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }

   
}
