package com.example.inprint.bean;
/*
 * @author wenpupil
 * @description 从服务器中查询的订单数据
 */
public class Rorder {
    private String aid;
    private String anumber;
    private String astatus;
    private String awhere;
    private String aurl;
    private String arate;

    public String getArate() {
        return arate;
    }

    public void setArate(String arate) {
        this.arate = arate;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAstatus() {
        return astatus;
    }

    public String getAurl() {
        return aurl;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }

    public String getAwhere() {
        return awhere;
    }


    public String getAnumber() {
        return anumber;
    }

    public void setAwhere(String awhere) {
        this.awhere = awhere;
    }

    public void setAnumber(String anumber) {
        this.anumber = anumber;
    }

    public String viewInfo(){
        return "awhere = " + awhere + ", arate = " + arate + ", anumber = " + anumber
                + ", astatus = " + astatus;
    }
}
