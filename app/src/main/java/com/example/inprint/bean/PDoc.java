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
    //提交信息到某个地址
    private String posturl;         //提交的地址

    public PDoc(String aid,String atoken,String rate,String uri,String posturl){
        this.aid=aid;
        this.atoken=atoken;
        this.rate=rate;
        this.uri=uri;
        this.posturl=posturl;
    }

    public void setAtoken(String atoken) {
        this.atoken = atoken;
    }

    public void setPosturl(String posturl) {
        this.posturl = posturl;
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

    public String getPosturl() {
        return posturl;
    }

    public String getAtoken() {
        return atoken;
    }

    public String viewInfo(){
        return "aid = "+aid+", atoken = "+atoken
                +", uri = "+uri+"\n, rate = "+rate+", posturl = "+posturl;
    }
}
