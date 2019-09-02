package com.example.inprint.bean;
/*
 * @Author Wenpupil
 * @Time 2019.09.02
 * @Description 提交用户文档到数据库
 */
public class PDoc {
    private String aid;
    private String atoken;
    private String rate;
    private String uri;
    //提交信息到某个地址
    private String posturl;

    public PDoc(String aid,String atoken,String rate,String uri,String posturl){
        this.aid=aid;
        this.atoken=atoken;
        this.rate=rate;
        this.uri=uri;
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
}
