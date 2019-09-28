package com.example.inprint.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;

import com.example.inprint.R;
import com.example.inprint.activity.PrintActivity;
import com.example.inprint.bean.Doc;
import com.example.inprint.bean.PDoc;
import com.example.inprint.bean.RDoc;
import com.example.inprint.bean.User;
import com.example.inprint.myview.DocAddDialog;
import com.example.inprint.myview.DocClickDialog;
import com.example.inprint.myview.LoadingDialog;
import com.example.inprint.util.ActivityUtil;
import com.example.inprint.util.HttpUtil;
import com.example.inprint.util.InfoUtil;
import com.example.inprint.util.LogUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import ru.bartwell.exfilepicker.ExFilePicker;

/*
 * @Author Wenpuils
 * @Description 关于文档碎片对象中间件，负责UI，Action，Model处理
 */
public class DocFragmentPresent {

    //存储文档路径
    private String[] fileDirs={"/tencent/QQfile_recv/","","/tencent/MicroMsg/Download/"};
    private Window window;
    private String clickDocName;         //存储本次点击文档路径
    public static int EX_FILE_PICKER_RESULT = 0xfa01; //文档来自本机
    public static int FILE_FROM_QQ = 0xfa02;          //文档来自QQ
    public static int FILE_FROM_WX = 0xfa03;          //文档来自微信
    private String startDirectory = null;// 记忆上一次访问的文件目录路径
    /* 0表示点击文档
     * 1表示点击添加
     * 2上传框消失
     * 列表点击检测监听：Handle机制
     */
    private Handler docHandler=new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 0:
                    String docName=(String)message.obj;
                    LogUtil.d("DocFragment",
                            "你点击了文档 "+docName);
                    DocClickAction(docName);
                    break;
                case 1:
                    DocAddAction();
                    break;
                case 2:
                    myLoadDialog.dismiss();
                    printDoc(message.obj);
                    break;
            }
        }
    };
    public void setWindow(Window window){
        this.window = window;
    }
    //等待对话框
    private LoadingDialog myLoadDialog;

    //选择文档来源地
    private DocAddDialog.AddClickListener listener=new DocAddDialog.AddClickListener() {
        @Override
        public void onClick(Dialog dialog, int rate) {
            dialog.dismiss();
            SelectDoc(fileDirs[rate],rate);
        }
    };
    //选择文档功能   --查看or打印
    private DocClickDialog.ClickDialog clickDocListener=new DocClickDialog.ClickDialog() {
        @Override
        public void onClick(Dialog dialog, int rate) {
            //Toast.makeText(context,
            //        "选择功能="+rate,Toast.LENGTH_SHORT).show();
            if(rate==0){
                uploadFile(); //执行打印文档行为先进行上传
            }else if(rate==1){
                checkDoc(); //执行查看文档行为
            }
            dialog.dismiss();
        }
    };

    private Context context;
    public DocFragmentPresent(Context context){
        this.context=context;
    }
    /* 打印文档行为步骤
     * 1.上传文档至服务器，获取页数及文档url
     * 2.跳转至打印活动
     */
    private void printDoc(Object obj){
        /*Toast.makeText(context,
                "打印文档="+clickDocName,Toast.LENGTH_SHORT).show();*/
        RDoc rDoc=(RDoc)obj;
        Intent intent=new Intent(context, PrintActivity.class);
        //传递文档在app中的uri到打印文档的活动中
        intent.putExtra("docUrl",rDoc.getFileUrl());
        intent.putExtra("docPage",rDoc.getFilePage());
        intent.putExtra("fileName",getFilename());
        intent.putExtra("docUri",clickDocName);
        context.startActivity(intent);
    }
    //查看文档行为--待优化
    private void checkDoc(){
        /*Toast.makeText(context,
                "查看文档="+clickDocName,Toast.LENGTH_SHORT).show();*/
        ActivityUtil.checkDoc(context,clickDocName,"MainActivity");
    }
    //添加文档行为
    private void DocAddAction(){
        new DocAddDialog(context,listener).show();
    }
    //文档点击后相应行为函数
    private void DocClickAction(String docName){
        clickDocName=docName;
        new DocClickDialog(context,clickDocListener).show();
    }
    /*
     * 选择文档
     */
    private void SelectDoc(String file_from,int rate){
        ExFilePicker exFilePicker = new ExFilePicker();
        exFilePicker.setCanChooseOnlyOneItem(true);// 单选
        exFilePicker.setShowOnlyExtensions("docx","doc","pdf");
        exFilePicker.setQuitButtonEnabled(true);
        LogUtil.d("上传测试","文件路径："+file_from);
        if (TextUtils.isEmpty(startDirectory)) {
            exFilePicker.setStartDirectory(Environment.getExternalStorageDirectory().getPath()+file_from);  //修改打开文档位置
        } else {
            exFilePicker.setStartDirectory(startDirectory);
        }

        exFilePicker.setChoiceType(ExFilePicker.ChoiceType.FILES);

        switch(rate){
            case 0:
                exFilePicker.start((Activity)context, FILE_FROM_QQ);
                break;
            case 1:
                exFilePicker.start((Activity)context, EX_FILE_PICKER_RESULT);
                break;
            case 2:
                exFilePicker.start((Activity)context, FILE_FROM_WX);
                break;
        }
    }
    //点击列表子项，通过handle更新UI
    public void notifyMessage(int what,String obj){
        Message message=new Message();
        message.what=what;
        message.obj=obj;
        docHandler.sendMessage(message);
    }
    public List<Doc> iniDocList(){
        List<Doc> docList= LitePal.findAll(Doc.class);
        docList=reserve(docList);
        Doc c=new Doc();
        c.setDocRate("tianjia");
        c.setDocUrl("ADD");
        docList.add(c);
        return docList;
    }
    //将数据反向
    private List<Doc> reserve(List<Doc> docList){
        List<Doc> result=new ArrayList<>();
        for(int i=docList.size()-1;i>=0;i--){
            result.add(docList.get(i));
        }
        return result;
    }
    //上传文档至服务器
    private void uploadFile() {
        //生产等待对话框对象
        myLoadDialog=new LoadingDialog(context);
        myLoadDialog.show();
        //构建上传信息
        int splitDiot = clickDocName.lastIndexOf(".");
        String rate = clickDocName.substring(splitDiot + 1);
        //虚拟用户--测试
        User user = InfoUtil.testUserInfo();
        PDoc pDoc = new PDoc(user.getAid(), user.getAtoken(), rate, clickDocName);
        LogUtil.d("上传文档", pDoc.viewInfo());
        //开始进行上传
        HttpUtil.postDoc(pDoc,uCallback);
    }
    //上传文档okhttp的接口实现
    private Callback uCallback = new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            LogUtil.e("上传出错","位置:DocFragmentPresent的callback接口");
        }
        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            String result = response.body().string();
            Gson gson=new Gson();
            RDoc rDoc=gson.fromJson(result,RDoc.class);
            if(rDoc.getSuccess().equals("true")) {
                LogUtil.d("上传成功", "回执信息:" + rDoc.viewInfo());
                notifyDialog(rDoc);
            }else{
                LogUtil.d("上传失败","用户信息认证失败");
            }
        }
    };
    //通过handle机制更新UI
    private void notifyDialog(RDoc rDoc){
        Message message=new Message();
        message.what=2;
        message.obj=rDoc;
        docHandler.sendMessage(message);
    }
    private String getFilename(){
        int splitLine=clickDocName.lastIndexOf('/');
        return clickDocName.substring(splitLine+1);
    }

    /*public void iniBanner(View view){
        Banner banner=view.findViewById(R.id.banner);
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
    }*/
}
