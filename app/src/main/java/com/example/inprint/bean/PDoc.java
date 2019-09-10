package com.example.inprint.bean;
/*
 * @Author Wenpupil
 * @Time 2019.09.02
 * @Description 提交用户文档到数据库
 */
public class PDoc {
    private String aid;             //用户识别id
    private String atoken;          //随机令牌
    private String rate;            //文档种类
    private String uri;             //文档在手机中的资源定位

    public PDoc(String aid,String atoken,String rate,String uri){
        this.aid=aid;
        this.atoken=atoken;
        this.rate=rate;
        this.uri=uri;
    }

    public void setAtoken(String atoken) {
        this.atoken = atoken;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAid() {
        return aid;
    }

    public String getUri() {
        return uri;
    }

    public String getRate() {
        return rate;
    }

    public String getAtoken() {
        return atoken;
    }

    public String viewInfo(){
        return "aid = "+aid+", atoken = "+atoken
                +", uri = "+uri+"\n, rate = "+rate;
    }
}
