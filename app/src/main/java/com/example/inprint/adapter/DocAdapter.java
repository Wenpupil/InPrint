package com.example.inprint.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.inprint.R;
import com.example.inprint.base.Doc;

import java.util.List;
/*
 * @Author Wenpupil
 * @Description 文档列表适配器
 */
public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> {

    private List<Doc> mDocList;
    private onItemClickListener listener;
    public interface onItemClickListener{
        String onClick(View v,String docurl);
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView docImage;
        TextView docTitle;
        TextView docWhere;
        TextView docAdd;
        ViewHolder(View view){
            super(view);
            docImage=view.findViewById(R.id.iv_doc_item_img);
            docTitle=view.findViewById(R.id.tv_doc_item_title);
            docWhere=view.findViewById(R.id.tv_doc_item_where);
            docAdd=view.findViewById(R.id.tv_add_doc);
            docAdd.setVisibility(View.GONE);
        }
    }

    public DocAdapter(List<Doc> docList){
        mDocList=docList;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doc_fragment_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Doc doc=mDocList.get(position);

        holder.docTitle.setText(doc.getDocTitle());
        holder.docWhere.setText(doc.getWhere());
        holder.itemView.setTag(doc.getDocUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(listener!=null) {
                    listener.onClick(v, (String) v.getTag());
                }
            }
        });
        String rate=doc.getDocRate();
        if(rate!=null&&rate.equals("pdf")){
            Glide.with(holder.itemView).
                    load(R.mipmap.pdf).
                    //apply(RequestOptions.bitmapTransform(new CircleCrop())).
                    into(holder.docImage);
        }else if(rate.equals("docx")||rate.equals("doc")){
            Glide.with(holder.itemView).
                    load(R.mipmap.word).
                    //apply(RequestOptions.bitmapTransform(new CircleCrop())).
                    into(holder.docImage);
        }else{
            Glide.with(holder.itemView).
                    load(R.mipmap.tianjia).
                    //apply(RequestOptions.bitmapTransform(new CircleCrop())).
                    into(holder.docImage);
            holder.docAdd.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public int getItemCount(){
        return mDocList.size();
    }
    public void setItemListener(onItemClickListener listener){
        this.listener=listener;
    }
}
