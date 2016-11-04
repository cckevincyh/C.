package com.way.bean;

import java.util.List;

/**
 * Created by c on 2016/11/2.
 */

public class WeatherInfoBean {


    /**
     * aqi : {"city":{"aqi":"42","co":"1","no2":"18","o3":"104","pm10":"38","pm25":"22","qlty":"优","so2":"8"}}
     * basic : {"city":"珠海","cnty":"中国","id":"CN101280701","lat":"22.248000","lon":"113.565000","update":{"loc":"2016-11-02 17:54","utc":"2016-11-02 09:54"}}
     * daily_forecast : [{"astro":{"sr":"06:30","ss":"17:48"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-11-02","hum":"71","pcpn":"0.0","pop":"0","pres":"1020","tmp":{"max":"26","min":"19"},"vis":"10","wind":{"deg":"31","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"sr":"06:31","ss":"17:47"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-11-03","hum":"68","pcpn":"0.0","pop":"0","pres":"1019","tmp":{"max":"26","min":"20"},"vis":"10","wind":{"deg":"34","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"sr":"06:31","ss":"17:47"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-11-04","hum":"68","pcpn":"0.0","pop":"0","pres":"1015","tmp":{"max":"27","min":"21"},"vis":"10","wind":{"deg":"39","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"06:32","ss":"17:46"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-11-05","hum":"69","pcpn":"0.0","pop":"0","pres":"1013","tmp":{"max":"28","min":"22"},"vis":"10","wind":{"deg":"108","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"sr":"06:32","ss":"17:46"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-11-06","hum":"74","pcpn":"0.1","pop":"0","pres":"1015","tmp":{"max":"28","min":"23"},"vis":"10","wind":{"deg":"99","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"06:33","ss":"17:45"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-11-07","hum":"75","pcpn":"0.0","pop":"0","pres":"1015","tmp":{"max":"29","min":"23"},"vis":"10","wind":{"deg":"103","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"sr":"06:33","ss":"17:45"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-11-08","hum":"71","pcpn":"1.2","pop":"46","pres":"1016","tmp":{"max":"27","min":"19"},"vis":"10","wind":{"deg":"17","dir":"无持续风向","sc":"微风","spd":"10"}}]
     * hourly_forecast : [{"date":"2016-11-02 19:00","hum":"70","pop":"0","pres":"1019","tmp":"24","wind":{"deg":"35","dir":"东北风","sc":"3-4","spd":"27"}},{"date":"2016-11-02 22:00","hum":"72","pop":"0","pres":"1021","tmp":"22","wind":{"deg":"34","dir":"东北风","sc":"4-5","spd":"29"}}]
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"26","hum":"63","pcpn":"0","pres":"1018","tmp":"22","vis":"10","wind":{"deg":"10","dir":"东北风","sc":"5-6","spd":"25"}}
     * status : ok
     * suggestion : {"comf":{"brf":"较舒适","txt":"白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较适宜","txt":"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"},"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"},"uv":{"brf":"强","txt":"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"}}
     */

    private List<HeWeatherdataserviceBean> HeWeatherdataservice;

    public List<HeWeatherdataserviceBean> getHeWeatherdataservice() {
        return HeWeatherdataservice;
    }

    public void setHeWeatherdataservice(List<HeWeatherdataserviceBean> HeWeatherdataservice) {
        this.HeWeatherdataservice = HeWeatherdataservice;
    }
}

   