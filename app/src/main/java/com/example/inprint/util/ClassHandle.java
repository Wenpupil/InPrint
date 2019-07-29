package com.example.inprint.util;

import com.example.inprint.bean.Doc;

public class ClassHandle {
    public static boolean docIsEquals(Doc a,Doc b){
        return  a.getDocRate().equals(b.getDocRate())
                &&a.getDocUrl().equals(b.getDocUrl())
                &&a.getDocTitle().equals(b.getDocTitle())
                &&a.getWhere().equals(b.getWhere());
    }
}
