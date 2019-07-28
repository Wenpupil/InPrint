package com.example.inprint.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.adapter.DocAdapter;
import com.example.inprint.bean.Doc;
import com.example.inprint.myview.DocAddDialog;
import com.example.inprint.presenter.DocFragmentPresent;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.TestDataUtil;

import java.util.ArrayList;
import java.util.List;

import me.rosuh.filepicker.config.FilePickerManager;

public class DocFragment extends Fragment {

    /* 0表示点击文档
     * 1表示点击添加
     */
    private Handler docHandler=new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 0:
                    Toast.makeText(getContext(),"你点击了文档 "+(String)message.obj
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
    private List<Doc> docList=new ArrayList<>();
    private RecyclerView recyclerView;
    private DocAdapter adapter;
    private String docUrl;               //存储被点击的文档地址

    //存储文档路径
    private String[] fileDirs={"/tencent/QQfile_recv/","","/tencent/MicroMsg/Download/"};
    private DocFragmentPresent docFragmentPresent;
    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        //用测试数据 初始化
        TestDataUtil.docItem(docList);
        view = inflater.inflate(R.layout.doc_fragment,container,false);
        recyclerView=view.findViewById(R.id.rv_doc);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new DocAdapter(docList);
        setOnItemClickListener();         //列表子项被点击对应的行为
        recyclerView.setAdapter(adapter);
        //初始化中间对象件
        docFragmentPresent=new DocFragmentPresent(getContext());
        return view;
    }
    private void setOnItemClickListener(){
        adapter.setItemListener(new DocAdapter.onItemClickListener() {
            @Override
            public void onClick(View v, String docurl) {
                docUrl=docurl;
                LogUtil.d("ClickList","docUrl="+docUrl);
                if(docUrl.equals("ADD")){
                    notifyMessage(1,"ADD");
                }else{
                    notifyMessage(0,docUrl);
                }
            }
        });
    }
    public void DocAddAction(){
        new DocAddDialog(getContext(),listener).show();
    }
    /*
     * 选择文档
     */
    public void SelectDoc(String file_from){
        FilePickerManager.INSTANCE
                .from(this)
                .forResult(FilePickerManager.REQUEST_CODE);
    }
    //点击列表子项，通过handle更新UI
    public void notifyMessage(int what,String obj){
        Message message=new Message();
        message.what=what;
        message.obj=obj;
        docHandler.sendMessage(message);
    }
}
