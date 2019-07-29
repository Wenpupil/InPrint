package com.example.inprint.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.inprint.bean.Doc;
import com.example.inprint.myview.DocAddDialog;
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

    public static int EX_FILE_PICKER_RESULT = 0xfa01;
    private String startDirectory = null;// 记忆上一次访问的文件目录路径
    /* 0表示点击文档
     * 1表示点击添加
     */
    private Handler docHandler=new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 0:
                    Toast.makeText(context,"你点击了文档 "+(String)message.obj
                            ,Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    DocAddAction();
                    break;
            }
        }
    };
    /*
     * 选择文档来源地
     */
    private DocAddDialog.AddClickListener listener=new DocAddDialog.AddClickListener() {
        @Override
        public void onClick(Dialog dialog, int rate) {
            dialog.dismiss();
            SelectDoc(fileDirs[rate]);
        }
    };
    private Context context;
    public DocFragmentPresent(Context context){
        this.context=context;
    }
    private void DocAddAction(){
        new DocAddDialog(context,listener).show();
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
