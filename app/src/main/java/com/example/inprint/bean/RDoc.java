package com.example.inprint.bean;
/*
 * @Author Wenpupil
 * @Time 2019.09.02
 * @Description 接收提交文档动作的回执信息
 */
public class RDoc {
    private String success;
    private String fileUrl;
    private String filePage;

    public void setFilePage(String filePage) {
        this.filePage = filePage;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFilePage() {
        return filePage;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getSuccess() {
        return success;
    }

    public String viewInfo(){
        return "success = "+success+", fileUrl = "+fileUrl+", filePage = "+filePage;
    }
}
