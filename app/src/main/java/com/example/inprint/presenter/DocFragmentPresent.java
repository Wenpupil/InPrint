package com.example.inprint.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.inprint.activity.DocViewActivity;
import com.example.inprint.activity.PrintActivity;
import com.example.inprint.bean.Doc;
import com.example.inprint.myview.DocAddDialog;
import com.example.inprint.myview.DocClickDialog;
import com.example.inprint.util.LogUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import ru.bartwell.exfilepicker.ExFilePicker;

/*
 * @Author Wenpuils
 * @Description 关于文档碎片对象中间件，负责UI，Action，Model处理
 */
public class DocFragmentPresent {

    //存储文档路径
    private String[] fileDirs={"/tencent/QQfile_recv/","","/tencent/MicroMsg/Download/"};

    private String clickDocName;         //存储本次点击文档路径
    public static int EX_FILE_PICKER_RESULT = 0xfa01;
    private String startDirectory = null;// 记忆上一次访问的文件目录路径
    /* 0表示点击文档
     * 1表示点击添加
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
            }
        }
    };
    //选择文档来源地
    private DocAddDialog.AddClickListener listener=new DocAddDialog.AddClickListener() {
        @Override
        public void onClick(Dialog dialog, int rate) {
            dialog.dismiss();
            SelectDoc(fileDirs[rate]);
        }
    };
    //选择文档功能   --查看or打印
    private DocClickDialog.ClickDialog clickDocListener=new DocClickDialog.ClickDialog() {
        @Override
        public void onClick(Dialog dialog, int rate) {
            //Toast.makeText(context,
            //        "选择功能="+rate,Toast.LENGTH_SHORT).show();
            if(rate==0){
                printDoc(); //执行打印文档行为
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
    //打印文档行为--
    private void printDoc(){
        /*Toast.makeText(context,
                "打印文档="+clickDocName,Toast.LENGTH_SHORT).show();*/
        Intent intent=new Intent(context, PrintActivity.class);
        //传递文档在app中的uri到打印文档的活动中
        intent.putExtra("docUri",clickDocName);
        context.startActivity(intent);

    }
    //查看文档行为--待优化
    private void checkDoc(){
        /*Toast.makeText(context,
                "查看文档="+clickDocName,Toast.LENGTH_SHORT).show();*/
        int splitLine=clickDocName.lastIndexOf('/');
        String docUrl=clickDocName.substring(0,splitLine+1);
        String docName=clickDocName.substring(splitLine+1);
        LogUtil.d("DocFragment-文档URL分割",docUrl+"  , "+docName);
        Intent intent=new Intent(context, DocViewActivity.class);
        intent.putExtra("docName",docName);
        intent.putExtra("docUrl",docUrl);
        context.startActivity(intent);
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
    private void SelectDoc(String file_from){
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
        exFilePicker.start((Activity)context, EX_FILE_PICKER_RESULT);
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
}
