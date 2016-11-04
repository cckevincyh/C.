package com.way.bean;

/**
 * 生活指数
 * @author c
 *
 */

public  class SuggestionBean {
    /**
     * brf : 较舒适
     * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
     */

    private ComfBean comf;
    /**
     * brf : 较适宜
     * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
     */

    private CwBean cw;
    /**
     * brf : 舒适
     * txt : 建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。
     */

    private DrsgBean drsg;
    /**
     * brf : 少发
     * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
     */

    private FluBean flu;
    /**
     * brf : 较适宜
     * txt : 天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。
     */

    private SportBean sport;
    /**
     * brf : 适宜
     * txt : 天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。
     */

    private TravBean trav;
    /**
     * brf : 强
     * txt : 紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。
     */

    private UvBean uv;

    
    /**
     * brf : 较舒适
     * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
     */
    public ComfBean getComf() {
        return comf;
    }

    public void setComf(ComfBean comf) {
        this.comf = comf;
    }
    
    /**
     * brf : 较适宜
     * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
     */
    public CwBean getCw() {
        return cw;
    }

    public void setCw(CwBean cw) {
        this.cw = cw;
    }

    public DrsgBean getDrsg() {
        return drsg;
    }

    public void setDrsg(DrsgBean drsg) {
        this.drsg = drsg;
    }

    public FluBean getFlu() {
        return flu;
    }

    public void setFlu(FluBean flu) {
        this.flu = flu;
    }

    public SportBean getSport() {
        return sport;
    }

    public void setSport(SportBean sport) {
        this.sport = sport;
    }

    public TravBean getTrav() {
        return trav;
    }

    public void setTrav(TravBean trav) {
        this.trav = trav;
    }
    
    /**
     * brf : 强
     * txt : 紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。
     */
    public UvBean getUv() {
        return uv;
    }

    public void setUv(UvBean uv) {
        this.uv = uv;
    }


    
}
