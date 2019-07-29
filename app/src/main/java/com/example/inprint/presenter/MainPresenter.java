package com.example.inprint.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.inprint.R;
import com.example.inprint.bean.Doc;
import com.example.inprint.fragment.DocFragment;
import com.example.inprint.util.LogUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.bartwell.exfilepicker.data.ExFilePickerResult;

/*
 * @Author Wenpupil
 * @Description MainActivity的对象中间件
 */
public class MainPresenter {
    private DocFragment docFragment;               //主活动中docFragment的引用
    private Activity activity;
    private ImageView[] tabIcons=new ImageView[3];  //tab1的图标
    private TextView[] tabTexts=new TextView[3];   //tab1的文本
    private Gson gson=new Gson();

    private int[] pastIcon={R.mipmap.wendang1,R.mipmap.order1,R.mipmap.wode1};
    private int[] nowIcon={R.mipmap.wendang,R.mipmap.order,R.mipmap.wode};
    public MainPresenter(Activity activity) {
        this.activity=activity;
        tabIcons[0]=activity.findViewById(R.id.iv_tab1);
        tabIcons[1]=activity.findViewById(R.id.iv_tab2);
        tabIcons[2]=activity.findViewById(R.id.iv_tab3);

        tabTexts[0]=activity.findViewById(R.id.tv_tab1);
        tabTexts[1]=activity.findViewById(R.id.tv_tab2);
        tabTexts[2]=activity.findViewById(R.id.tv_tab3);
    }

    //改变tab的图标和文字颜色
    public int changeTab(int past,int now){
        tabIcons[past].setImageResource(pastIcon[past]);
        tabIcons[now].setImageResource(nowIcon[now]);
        tabTexts[past].setTextColor(activity.
                getResources().getColor(R.color.app_grey));
        tabTexts[now].setTextColor(activity.
                getResources().getColor(R.color.app_icon_color));
        return now;
    }
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
    /*
     * 添加文档到recycler中，并且更新UI，保存数据入库
     */
    private void addDocInList(String where,Uri uri){
        Doc doc=docCreate(where,uri);
        //更新docFragment列表,并保存入库
        docFragment.updataDocList(doc);
    }
    //接收URI对象和where数据，产生DOC
    private Doc docCreate(String where,Uri uri){
        String filepath=uri.getPath();
        String filename=uri.getLastPathSegment();
        String[] fileAttr=filename.split("[.]");
        LogUtil.d("fileInfo","filepath = " + filepath);
        LogUtil.d("fileInfo","filename = " + filename);
        Doc doc=Doc.createDoc(fileAttr[0],fileAttr[1],where,filepath); //创建doc对象
        LogUtil.d("fileAttr",gson.toJson(doc));
        return doc;
    }
    //通过请求码，定位文件产生位置
    public void docFromWhere(int requestCode, Intent data){
        if(requestCode==0xfa01) requestCode=1;
        String[] where={"QQ","设备","微信"};
        ExFilePickerResult result = ExFilePickerResult.getFromIntent(data);
        if (result != null && result.getCount() > 0) {
            String path =result.getPath();
            List<String> names = result.getNames();
            for (int i = 0; i < names.size(); i++) {
                File f = new File(path, names.get(i));
                try {
                    Uri uri = Uri.fromFile(f); //这里获取了真实可用的文件资源
                    LogUtil.d("fileAbsolute",uri.toString());
                    addDocInList(where[requestCode],uri);//将路径定位传入
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setDocFragment(DocFragment docFragment){
        this.docFragment=docFragment;
    }
}
