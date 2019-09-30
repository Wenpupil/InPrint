package com.example.inprint.bean;

public class Talk {

    private String name;
    private String id;
    private String img;
    private String content;
    private String time;
    private int where;

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setImg(String img){
        this.img = img;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setWhere(int where){
        this.where = where;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getImg(){
        return img;
    }
    public String getContent(){
        return content;
    }
    public String getTime(){
        return time;
    }
    public int getWhere(){
        return where;
    }

}
