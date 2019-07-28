package com.example.inprint.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.adapter.DocAdapter;
import com.example.inprint.bean.Doc;
import com.example.inprint.presenter.DocFragmentPresent;
import com.example.inprint.util.LogUtil;
import com.example.inprint.util.TestDataUtil;

import java.util.ArrayList;
import java.util.List;

public class DocFragment extends Fragment {

    private List<Doc> docList=new ArrayList<>();
    private DocAdapter adapter;
    private String docUrl;               //存储被点击的文档地址

    private DocFragmentPresent docFragmentPresent;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view;
        RecyclerView recyclerView;
        //用测试数据 初始化
        TestDataUtil.docItem(docList);
        view = inflater.inflate(R.layout.doc_fragment,container,false);
        recyclerView=view.findViewById(R.id.rv_doc);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new DocAdapter(docList);
        adapter.setContext(getContext());
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
                    docFragmentPresent.notifyMessage(1,"ADD");
                }else{
                    docFragmentPresent.notifyMessage(0,docUrl);
                }
            }
        });
    }
}
