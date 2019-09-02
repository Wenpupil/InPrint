package com.example.inprint.bean;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private String aid;
    private String atoken;

    public void setAtoken(String atoken) {
        this.atoken = atoken;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAtoken() {
        return atoken;
    }

    public String getAid() {
        return aid;
    }
}
