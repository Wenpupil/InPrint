package com.example.inprint.base;

public class Doc {
    private String docTitle;   //文档名字
    private String docRate;    //文档种类....such as: Doc,docx,pdf
    private String where;      //文档来自:qq,wx,phone
    private String docUrl;     //文档在手机中具体存储位置

    public String getDocUrl() {
        return docUrl;
    }
    public String getDocRate() {
        return docRate;
    }
    public String getDocTitle() {
        return docTitle;
    }
    public String getWhere() {
        return where;
    }

    public void setDocRate(String docRate) {
        this.docRate = docRate;
    }
    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }
    public void setWhere(String where) {
        this.where = where;
    }
    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
}
