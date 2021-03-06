package com.example.inprint.bean;

/*
 * @Author Wenpupil
 * @Time 2019.09.05
 * @Description 存储位置信息
 *
 * tip:
 *     0--表示有空闲
 *     1--表示柜门已满
 */
public class Location {
    private String locationId;  //打印柜唯一标识号
    private String loc;         //存储位置地点
    private int status;         //存储打印点状态

    public Location(String locationId,String loc,int status){
        this.locationId=locationId;
        this.loc=loc;
        this.status=status;
    }
    public void setLocationId(String locationId){
        this.locationId=locationId;
    }
    public void setLoc(String loc){
        this.loc=loc;
    }
    public void setStatus(int status){
        this.status=status;
    }

    public String getLocationId(){
        return locationId;
    }
    public String getLoc() {
        return loc;
    }

    public int getStatus() {
        return status;
    }
}
