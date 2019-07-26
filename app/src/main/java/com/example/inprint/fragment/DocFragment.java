package com.example.inprint.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inprint.R;
import com.example.inprint.adapter.DocAdapter;
import com.example.inprint.base.Doc;
import com.example.inprint.util.TestDataUtil;

import java.util.ArrayList;
import java.util.List;

public class DocFragment extends Fragment {
    private List<Doc> docList=new ArrayList<>();
    private RecyclerView recyclerView;
    private DocAdapter adapter;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        //用测试数据 初始化
        TestDataUtil.docItem(docList);
        view = inflater.inflate(R.layout.doc_fragment,container,false);
        recyclerView=view.findViewById(R.id.rv_doc);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new DocAdapter(docList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
