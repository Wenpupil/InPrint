package com.example.inprint.bean;

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
}
