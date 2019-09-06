package com.example.inprint.bean;

import com.example.inprint.util.LogUtil;

/*
 * @Author Wenpupil
 * @Time 2019.09.05
 * @Description 发送订单到服务器上
 */
public class POrder {
    private String aid;         //用户识别号
    private String atoken;      //登录令牌
    private String anumber;     //文档份数
    private String astatus;     //文档状态
    private String awhere;      //地点
    private String acost;       //花费
    private String aurl;        //文档地址
    private String atime;        //订单创立时间

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setAtoken(String atoken) {
        this.atoken = atoken;
    }

    public void setAnumber(String anumber) {
        this.anumber = anumber;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }

    public void setAwhere(String awhere) {
        this.awhere = awhere;
    }

    public void setAcost(String acost) {
        this.acost = acost;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }
    public void setAtime(String atime){
        this.atime = atime;
    }

    public String getAid() {
        return aid;
    }

    public String getAtoken() {
        return atoken;
    }

    public String getAcost() {
        return acost;
    }

    public String getAnumber() {
        return anumber;
    }

    public String getAstatus() {
        return astatus;
    }


    public String getAurl() {
        return aurl;
    }

    public String getAtime() {
        return atime;
    }

    public String getAwhere() {
        return awhere;
    }

    public void viewInfo(){
        LogUtil.d("订单信息","aid = " + getAid() + ", where = " + getAwhere() +
                ", cost = " + getAcost() + ", url = " + getAurl() + ", status = " + getAstatus() +
                "\ntime = " + getAtime() + ", number = " + getAnumber());
    }
}
