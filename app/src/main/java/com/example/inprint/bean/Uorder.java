package com.example.inprint.bean;
import org.litepal.crud.LitePalSupport;

/*
 * @Author Wenpupil
 * @Time 2019.09.07
 * @Description 用于初始化订单列表的类
 */
public class Uorder extends LitePalSupport {
    private String aid;         //用户标识号
    private String docUrl;      //文档在服务器中的url作为标识
    private String docName;     //文档名字
    private String status;      //文档状态
    private String number;      //文档份数
    private String cost;        //用户付款
    private String time;        //下单时间
    private String where;       //文档位置

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public String getWhere() {
        return where;
    }

    public String getCost() {
        return cost;
    }

    public String getDocName() {
        return docName;
    }

    public String getNumber() {
        return number;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getAid() {
        return aid;
    }

    public String viewInfo(){
        return "aid = " + aid + ", docName = " + docName + ", status = " + status;
    }
}
