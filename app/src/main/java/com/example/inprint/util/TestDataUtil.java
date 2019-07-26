package com.example.inprint.util;

import com.example.inprint.base.Doc;

import java.util.List;

public class TestDataUtil {
    public static void docItem(List<Doc> docList){
        Doc doc=new Doc();
        doc.setDocTitle("学费通知");
        doc.setDocRate("pdf");
        doc.setWhere("来自 QQ");
        docList.add(doc);

        Doc a=new Doc();
        a.setDocRate("docx");
        a.setWhere("来自 微信");
        a.setDocTitle("上海海洋大学校规");
        docList.add(a);

        Doc b=new Doc();
        b.setDocRate("docx");
        b.setDocTitle("连接wifi手册");
        b.setWhere("来自 QQ");
        docList.add(b);
    }
}
