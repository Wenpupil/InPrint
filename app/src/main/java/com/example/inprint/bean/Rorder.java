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
    private String acost;
    private String aurl;
    private String atime;

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

    public String getAtime() {
        return atime;
    }

    public String getAnumber() {
        return anumber;
    }

    public String getAcost() {
        return acost;
    }

    public void setAcost(String acost) {
        this.acost = acost;
    }

    public void setAwhere(String awhere) {
        this.awhere = awhere;
    }

    public void setAnumber(String anumber) {
        this.anumber = anumber;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }
    public String viewInfo(){
        return "awhere = " + awhere + ", acost = " + acost + ", anumber = " + anumber
                + ", astatus = " + astatus;
    }
}
