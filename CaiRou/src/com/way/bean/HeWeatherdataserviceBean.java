package com.way.bean;

import java.util.List;

public  class HeWeatherdataserviceBean {
    /**
     * city : {"aqi":"42","co":"1","no2":"18","o3":"104","pm10":"38","pm25":"22","qlty":"优","so2":"8"}
     */

    private AqiBean aqi;
    /**
     * city : 珠海
     * cnty : 中国
     * id : CN101280701
     * lat : 22.248000
     * lon : 113.565000
     * update : {"loc":"2016-11-02 17:54","utc":"2016-11-02 09:54"}
     */

    private BasicBean basic;
    /**
     * cond : {"code":"100","txt":"晴"}
     * fl : 26
     * hum : 63
     * pcpn : 0
     * pres : 1018
     * tmp : 22
     * vis : 10
     * wind : {"deg":"10","dir":"东北风","sc":"5-6","spd":"25"}
     */

    private NowBean now;
    private String status;
    /**
     * comf : {"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"}
     * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
     * drsg : {"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"}
     * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
     * sport : {"brf":"较适宜","txt":"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"}
     * trav : {"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"}
     * uv : {"brf":"强","txt":"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"}
     */

    private SuggestionBean suggestion;
    /**
     * astro : {"sr":"06:30","ss":"17:48"}
     * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
     * date : 2016-11-02
     * hum : 71
     * pcpn : 0.0
     * pop : 0
     * pres : 1020
     * tmp : {"max":"26","min":"19"}
     * vis : 10
     * wind : {"deg":"31","dir":"无持续风向","sc":"微风","spd":"8"}
     */

    private List<DailyForecastBean> daily_forecast;
    /**
     * date : 2016-11-02 19:00
     * hum : 70
     * pop : 0
     * pres : 1019
     * tmp : 24
     * wind : {"deg":"35","dir":"东北风","sc":"3-4","spd":"27"}
     */

    private List<HourlyForecastBean> hourly_forecast;

    public AqiBean getAqi() {
        return aqi;
    }

    public void setAqi(AqiBean aqi) {
        this.aqi = aqi;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecastBean> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<HourlyForecastBean> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }


 



 
}
