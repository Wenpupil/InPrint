package com.example.inprint.presenter;

import com.example.inprint.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
/*
 * @Author Wenpupil
 * @Description MainActivity的对象中间件
 */
public class MainPresenter {
    //初始化轮播广告控件
    public static void iniBanner(Banner banner){
        List<Integer> data=new ArrayList<>();
        data.add(R.mipmap.l1);
        data.add(R.mipmap.l2);
        data.add(R.mipmap.l3);
        banner.setImageLoader(new GlideImageLoader());
        //设置轮播图片集合
        banner.setImages(data);
        //设置轮播时间
        banner.setDelayTime(2000);
        banner.start();
    }
}
