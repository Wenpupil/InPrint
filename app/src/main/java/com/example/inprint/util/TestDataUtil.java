package com.example.inprint.util;

import com.example.inprint.bean.Doc;
import com.example.inprint.bean.Order;

import java.util.List;

public class TestDataUtil {
    public static void docItem(List<Doc> docList){
        Doc doc=new Doc();
        doc.setDocTitle("学费通知");
        doc.setDocRate("pdf");
        doc.setWhere("来自 QQ");
        doc.setDocUrl("hello/test/pdf");
        docList.add(doc);

        Doc a=new Doc();
        a.setDocRate("docx");
        a.setWhere("来自 微信");
        a.setDocTitle("上海海洋大学校规");
        a.setDocUrl("www/test/docx");
        docList.add(a);

        Doc b=new Doc();
        b.setDocRate("docx");
        b.setDocTitle("连接wifi手册");
        b.setWhere("来自 QQ");
        b.setDocUrl("www/test/2");
        docList.add(b);

        Doc c=new Doc();
        c.setDocRate("tianjia");
        c.setDocUrl("ADD");
        docList.add(c);
    }
    public static void orderItem(List<Order> orderList){
        Order order=new Order();
        order.setDocUrl("http://www.ssdfs.com/1.doc");
        order.setDocName("上海海洋大学校规.doc");
        order.setTime("2019-09-01 10:20");
        order.setStatus("0");
        order.setNumber("2");
        order.setCost("1.2");
        order.setWhere("");
        orderList.add(order);

        Order a=new Order();
        a.setDocUrl("http://www.ssds.com/2.docx");
        a.setDocName("公司项目书.docx");
        a.setTime("2019-09-03 09:12");
        a.setStatus("1");
        a.setNumber("1");
        a.setCost("0.2");
        a.setWhere("00121");
        orderList.add(a);

        Order b=new Order();
        b.setDocUrl("http://www.123.com/3.doc");
        b.setDocName("测试.doc");
        b.setTime("2019-09-02 23:12");
        b.setStatus("0");
        b.setNumber("5");
        b.setCost("12.4");
        b.setWhere("");
        orderList.add(b);

    }
}
